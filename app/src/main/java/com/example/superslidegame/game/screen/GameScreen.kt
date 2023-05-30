package com.example.superslidegame.game.screen

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.superslidegame.R
import com.example.superslidegame.databinding.GameScreenBinding
import com.example.superslidegame.fragments.GridFrag
import com.example.superslidegame.fragments.LogFrag
import com.example.superslidegame.fragments.TimeUpFragment
import com.example.superslidegame.game.GameLogic
import com.example.superslidegame.game.elements.GamePiece
import com.example.superslidegame.game.elements.GameState
import com.example.superslidegame.game.elements.ImageAdapter
import com.example.superslidegame.game.elements.StoppableCountDownTimer
import com.example.superslidegame.game.levels.GameLevel
import com.example.superslidegame.log.Logger
import kotlin.properties.Delegates

/**
 * GameScreen is the main screen of the game.
 * It contains the grid of tiles and the buttons to control the game.
 * It also contains the timer and the level text.
 * It is responsible for saving the state of the game and restoring it.
 * It is also responsible for showing the dialog when the game is finished.
 */

class GameScreen : AppCompatActivity() {

    private lateinit var gameState : GameState
    private lateinit var level : GameLevel
    private lateinit var adapter: ImageAdapter
    private lateinit var timer: StoppableCountDownTimer
    private val binding by lazy { GameScreenBinding.inflate(layoutInflater) }
    private lateinit var timerTextView: TextView
    private lateinit var gridFragment : GridFrag
    private var infiniteTime by Delegates.notNull<Boolean>()

    /**
     * onCreate is called when the activity is starting.
     * It inflates the layout and sets the adapter for the grid of tiles.
     * It also sets the timer and the level text.
     * If the activity is recreated, it restores the state of the game.
     * @param savedInstanceState Bundle?
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        gridFragment = supportFragmentManager.findFragmentById(R.id.gridFrag) as GridFrag

        timerTextView = gridFragment.getTimerTextView()

        var timerTime : Long = 0

        if (savedInstanceState != null) {
            gameState = GameState.fromBundle(savedInstanceState.getBundle("gameState")!!)
            level = GameLevel(gameState.level)
            gameState.board?.let { level.setPieces(it) }
            gameState.pieceGroups?.let { level.setGroups(it) }
            timerTime = gameState.timeLeft!!
        } else {
            gameState = GameState.fromBundle(intent.extras!!)
            level = GameLevel(gameState.level)

            when (gameState.difficulty) {
                getString(R.string.Easy) -> timerTime = 80000
                getString(R.string.Hard) -> timerTime = 40000
                getString(R.string.Extreme) -> timerTime = 30000
            }
        }

        adapter = ImageAdapter(this, level)
        gridFragment.setGridViewAdapter(adapter)
        infiniteTime = intent.getBooleanExtra("infinite_time", false)
        if (!infiniteTime) {
            timer = StoppableCountDownTimer(timerTime, 1000, this, timerTextView)
            timer.start()
        } else {
            timerTextView.text = getString(R.string.infinite_time)
        }

        gridFragment.setLevelTextText(String.format("Level: %d", gameState.level))
        updateMoves(Logger.lastLevelMoves)

        GameLogic.GAME_STATE = GameState.Type.IN_PROGRESS
    }

    /**
     * onSaveInstanceState is called before the activity is destroyed.
     * It saves the state of the game.
     */
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        gameState.board = adapter.getPiecesState()
        gameState.pieceGroups = level.getGroups()
        gameState.timeLeft = timer.cancelAndGetTimeLeft() * 1000
        outState.putBundle("gameState", gameState.toBundle())
    }

    /**
     * onDestroy is called before the activity is destroyed.
     * It cancels the timer.
     */
    override fun onDestroy() {
        super.onDestroy()
        timer.cancel()
    }

    /**
     * onGameFinished is called when the game is finished.
     * It shows a dialog to the user.
     */
    fun onGameFinished(seconds : Long) {
        gridFragment.setTimerTextText(getString(R.string.time_up))
        val dialogFragment = TimeUpFragment()
        dialogFragment.show(supportFragmentManager, "My  Fragment")
        GameLogic.onLose(seconds)
    }

    /**
     * getGameTimer returns the timer of the game.
     */
    fun getGameTimer() : StoppableCountDownTimer? {
        if (infiniteTime)
            return null
        return timer
    }

    /**
     * getPlayingLevel returns the level number of the game.
     */
    fun getPlayingLevel() : Int {
        return gameState.level
    }

    /**
     * isLastLevel returns true if the game is in the last level.
     */
    fun isLastLevel() : Boolean {
        return gameState.level == GameLevel.MAX_LEVEL
    }

    /**
     * updateMoves updates the number of moves in the grid fragment.
     */
    fun updateMoves(moves: Int) {
        gridFragment.updateMoves(moves)
    }

    /**
     * isExtremeModeGame returns true if the game is in extreme mode.
     */
    fun isExtremeModeGame(): Boolean {
        return gameState.difficulty == getString(R.string.Extreme)
    }

    fun updateLogFragment(positionClicked: Int, pieceClicked: GamePiece, positionToMove: Any, ) {
        val logFragment = supportFragmentManager.findFragmentById(R.id.logFrag) as LogFrag?
        val text : String = if (infiniteTime) {
            "You clicked to position " + positionClicked + " which corresponds to a " + pieceClicked.type + " piece, and it has move to "+ positionToMove +". Move number " + Logger.moves + ", level: " + gameState.level + "\n"

        } else {
            "You clicked to position " + positionClicked + " which corresponds to a " + pieceClicked.type + " piece, and it has move to "+ positionToMove +". Move number " + Logger.moves + ", time remaining: " + timer.getTimeLeft() + "s, level: " + gameState.level + "\n"
        }
        logFragment?.updateLog(text)
    }
}