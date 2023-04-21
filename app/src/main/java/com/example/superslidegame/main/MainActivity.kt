package com.example.superslidegame.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.superslidegame.databinding.ActivityMainBinding
import com.example.superslidegame.game.screen.GameScreen
import com.example.superslidegame.settings.SettingsScreen

/**
 * Main activity of the application.
 * This activity is the first activity that is launched when the application is started.
 * It contains three buttons, one to start the game, one to go to the help screen, and one to go to the settings screen.
 * Every screen is started when the corresponding button is clicked.
 * @constructor Creates a new main activity.
 * @see AppCompatActivity
 * @see GameScreen
 * @see HelpScreen
 * @see SettingsScreen
 */
class MainActivity : AppCompatActivity() {
    /**
     * Creates the main activity.
     * This function is called when the activity is first created.
     * It sets the content view to the main activity layout.
     * It also sets the on click listeners for the buttons.
     * @param savedInstanceState The saved instance state.
     */
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.playButton.setOnClickListener {
            val intent = Intent(this, GameScreen::class.java)
            startActivity(intent)
        }

        binding.settingsButton.setOnClickListener {
            val intent = Intent(this, SettingsScreen::class.java)
            startActivity(intent)
        }
    }
}
