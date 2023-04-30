package com.example.superslidegame.game.screen

import android.os.Bundle
import android.os.CountDownTimer
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.superslidegame.R
import com.example.superslidegame.databinding.GameScreenBinding
import com.example.superslidegame.game.animations.AnimationHelper
import com.example.superslidegame.game.elements.GameState
import com.example.superslidegame.game.elements.ImageAdapter
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
    private lateinit var timerTextView: TextView
    private lateinit var timer: CountDownTimer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = GameScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState != null) {
            gameState = GameState.fromBundle(savedInstanceState.getBundle("gameState")!!)
            level = GameLevel(gameState.level)
            gameState.board?.let { level.setPieces(it) }
        } else {
            gameState = GameState.fromBundle(intent.extras!!)
            level = GameLevel(gameState.level)
        }

        val animationHelper = AnimationHelper(this)

        adapter = ImageAdapter(this, level, animationHelper)
        binding.gridTiles.adapter = adapter
        timerTextView = findViewById(R.id.timerTextView)

        timer = object : CountDownTimer(60000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val seconds = millisUntilFinished / 1000
                timerTextView.text = "Time left: $seconds seconds"
            }

            override fun onFinish() {
                timerTextView.text = "Time's up!"
                // do something when the timer finishes
            }
        }

        timer.start()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        gameState.board = adapter.getPiecesState()
        outState.putBundle("gameState", gameState.toBundle())
    }
}