package com.example.superslidegame.history

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.superslidegame.databinding.ActivityAccessDbBinding

class AccessDBActivity : AppCompatActivity() {

    private val binding by lazy { ActivityAccessDbBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}