package com.example.superslidegame

import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SwitchCompat

class SettingsScreen: AppCompatActivity() {
    private val screenWidth = Resources.getSystem().displayMetrics.widthPixels
    private val screenHeight = Resources.getSystem().displayMetrics.heightPixels

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_screen)

        val light_mode_switch = findViewById<SwitchCompat>(R.id.light_mode_switch)

        when (applicationContext.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
            Configuration.UI_MODE_NIGHT_YES -> light_mode_switch.isChecked = false
            Configuration.UI_MODE_NIGHT_NO -> light_mode_switch.isChecked = true
            Configuration.UI_MODE_NIGHT_UNDEFINED -> light_mode_switch.isChecked = true
        }

        light_mode_switch.setOnClickListener {
            if (light_mode_switch.isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
        }
    }
}