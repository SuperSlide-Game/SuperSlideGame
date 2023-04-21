package com.example.superslidegame.game.screen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.superslidegame.databinding.GameScreenBinding
import com.example.superslidegame.game.elements.ImageAdapter

/**
 * GameScreen is the main screen of the game.
 * It contains the grid of tiles and the buttons to control the game.
 */

class GameScreen : AppCompatActivity() {
    /**
     * onCreate is called when the activity is starting.
     * It inflates the layout and sets the adapter for the grid of tiles.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = GameScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.gridTiles.adapter = ImageAdapter(applicationContext)
    }
}