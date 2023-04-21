package com.example.superslidegame

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.superslidegame.databinding.SettingsScreenBinding

/**
 * Settings activity for the game.
 * This activity is started when the settings button is clicked on the main activity.
 * It contains a switch to toggle between light and dark mode.
 * The switch is set to the current mode when the activity is created.
 * More settings will be added in the future...
 * @constructor Creates a new settings activity.
 * @see AppCompatActivity
 */

class SettingsScreen: AppCompatActivity() {
    /**
     * Creates the settings activity.
     * This function is called when the activity is first created.
     * It sets the content view to the settings activity layout.
     * It also sets the on click listener for the switch.
     * @param savedInstanceState The saved instance state.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = SettingsScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        when (applicationContext.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
            Configuration.UI_MODE_NIGHT_YES -> binding.lightModeSwitch.isChecked = false
            Configuration.UI_MODE_NIGHT_NO -> binding.lightModeSwitch.isChecked = true
            Configuration.UI_MODE_NIGHT_UNDEFINED -> binding.lightModeSwitch.isChecked = true
        }

        binding.lightModeSwitch.setOnClickListener {
            if (binding.lightModeSwitch.isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
        }
    }
}