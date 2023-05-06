package com.example.superslidegame.game.screen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.superslidegame.R
import com.example.superslidegame.databinding.GameScreenBinding
import com.example.superslidegame.fragments.TimeUpFragment
import com.example.superslidegame.game.GameLogic
import com.example.superslidegame.game.animations.AnimationHelper
import com.example.superslidegame.game.elements.GameState
import com.example.superslidegame.game.elements.ImageAdapter
import com.example.superslidegame.game.elements.StoppableCountDownTimer
import com.example.superslidegame.game.levels.GameLevel

/**
 * GameScreen is the main screen of the game.
 * It contains the grid of tiles and the buttons to control the game.
 */

class GameScreen : AppCompatActivity() {
    /**
     * onCreate is called when the activity is starting.
     * It inflates the layout and sets the adapter for the grid of tiles.
     */

    private lateinit var gameState : GameState
    private lateinit var level : GameLevel
    private lateinit var adapter: ImageAdapter
    private lateinit var timer: StoppableCountDownTimer
    private val binding by lazy { GameScreenBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        var timerTime : Long = 0

        if (savedInstanceState != null) {
            gameState = GameState.fromBundle(savedInstanceState.getBundle("gameState")!!)
            level = GameLevel(gameState.level)
            gameState.board?.let { level.setPieces(it) }
            timerTime = gameState.timeLeft!!
        } else {
            gameState = GameState.fromBundle(intent.extras!!)
            level = GameLevel(gameState.level)

            when (gameState.difficulty) {
                getString(R.string.Easy) -> timerTime = 60000
                getString(R.string.Hard) -> timerTime = 30000
                getString(R.string.Extreme) -> timerTime = 15000
            }
        }

        val animationHelper = AnimationHelper(this)

        adapter = ImageAdapter(this, level, animationHelper)
        binding.gridTiles.adapter = adapter

        timer = StoppableCountDownTimer(timerTime, 1000, this, binding.timerTextView)

        timer.start()

        GameLogic.GAME_STATE = GameState.Type.IN_PROGRESS
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        gameState.board = adapter.getPiecesState()
        gameState.timeLeft = timer.cancelAndGetTimeLeft() * 1000
        outState.putBundle("gameState", gameState.toBundle())
    }

    fun onGameFinished(seconds : Long) {
        binding.timerTextView.text = getString(R.string.time_up)
        val dialogFragment = TimeUpFragment()
        dialogFragment.show(supportFragmentManager, "My  Fragment")
        GameLogic.onLose(seconds)
    }

    fun getGameTimer() : StoppableCountDownTimer {
        return timer
    }

    fun getPlayingLevel() : Int {
        return gameState.level
    }
}