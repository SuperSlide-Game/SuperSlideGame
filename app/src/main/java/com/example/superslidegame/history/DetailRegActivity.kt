package com.example.superslidegame.history

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.superslidegame.databinding.ActivityDetailRegBinding

class DetailRegActivity : AppCompatActivity() {

    private val binding by lazy { ActivityDetailRegBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}