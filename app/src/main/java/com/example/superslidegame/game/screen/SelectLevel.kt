package com.example.superslidegame.game.screen

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.superslidegame.databinding.ActivityMainBinding
import com.example.superslidegame.databinding.GameScreenBinding
import com.example.superslidegame.databinding.SelLevelBinding
import com.example.superslidegame.game.animations.AnimationHelper
import com.example.superslidegame.game.elements.ImageAdapter
import com.example.superslidegame.game.levels.GameLevel

/**
 * GameScreen is the level selector screen of the game
 */

class SelectLevel : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = SelLevelBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val intent = Intent(this, GameScreen::class.java)
        binding.button3.setOnClickListener {
            intent.putExtra("sel_level", "1")
            startActivity(intent)
        }
        binding.button4.setOnClickListener {
            intent.putExtra("sel_level", "2")
            startActivity(intent)
        }
    }
}