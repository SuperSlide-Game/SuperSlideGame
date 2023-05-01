package com.example.superslidegame.log.screen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.superslidegame.databinding.ActivityLogScreenBinding
import com.example.superslidegame.log.Logger

class LogScreen : AppCompatActivity() {

    private val binding by lazy { ActivityLogScreenBinding.inflate(layoutInflater) }
    private val logger = Logger.getLogger()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}