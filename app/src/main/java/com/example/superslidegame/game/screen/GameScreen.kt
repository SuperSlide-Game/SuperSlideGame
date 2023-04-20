package com.example.superslidegame.game.screen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.superslidegame.GameDraw
import com.example.superslidegame.databinding.GameScreenBinding
import com.example.superslidegame.game.elements.ImageAdapter

class GameScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = GameScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Run GameDraw
        //GameDraw(this, null)
        binding.gridTiles.adapter = ImageAdapter(applicationContext)
    }

}