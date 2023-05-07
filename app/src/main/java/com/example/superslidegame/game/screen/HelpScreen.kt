package com.example.superslidegame.game.screen

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.superslidegame.databinding.HelpScreenBinding

/**
 * This class is the help screen of the game.
 * It displays the rules of the game and the controls.
 * It also has a button to go back to the main menu.
 */

class HelpScreen : AppCompatActivity() {
    private val binding by lazy { HelpScreenBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val goBackButton: Button = binding.goBackButton
        goBackButton.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

}