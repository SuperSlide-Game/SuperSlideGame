package com.example.superslidegame

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.superslidegame.databinding.SettingsScreenBinding

class SettingsScreen: AppCompatActivity() {
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