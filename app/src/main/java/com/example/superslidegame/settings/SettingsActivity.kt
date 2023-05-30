package com.example.superslidegame.settings

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.superslidegame.fragments.SettingsFragment

/**
 * Settings activity for the game.
 * This activity is started when the settings button is clicked on the main activity.
 * It contains a switch to toggle between light and dark mode.
 * The switch is set to the current mode when the activity is created.
 * More settings will be added in the future...
 * @constructor Creates a new settings activity.
 * @see AppCompatActivity
 */

class SettingsActivity: AppCompatActivity() {
    /**
     * Creates the settings activity.
     * This function is called when the activity is first created.
     * It sets the content view to the settings activity layout.
     * It also sets the on click listener for the switch.
     * @param savedInstanceState The saved instance state.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager
            .beginTransaction()
            .replace(android.R.id.content, SettingsFragment())
            .commit()
    }
}