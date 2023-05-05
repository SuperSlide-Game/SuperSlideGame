package com.example.superslidegame.game.screen

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.superslidegame.databinding.HelpScreenBinding
import com.example.superslidegame.game.elements.GameState
import com.example.superslidegame.game.elements.LevelListAdapter
import com.example.superslidegame.log.Logger

/**
 * GameScreen is the level selector screen of the game
 */

class HelpScreen : AppCompatActivity() {
    private val binding by lazy { HelpScreenBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

}