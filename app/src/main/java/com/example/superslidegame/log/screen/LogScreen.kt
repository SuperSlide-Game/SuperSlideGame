package com.example.superslidegame.log.screen

import android.content.Intent
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

        binding.logTextView.text = logger.getLog()

        binding.sendEmailButton.setOnClickListener {
            if (binding.emailEditText.text.toString().isNotEmpty()) {
                val intent = Intent(Intent.ACTION_SEND)
                intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(binding.emailEditText.text.toString()))
                intent.putExtra(Intent.EXTRA_SUBJECT, "SuperSlideGame log")
                intent.putExtra(Intent.EXTRA_TEXT, logger.getLog())
                intent.type = "message/rfc822"
                startActivity(intent)
            }
        }

        binding.newGameButton.setOnClickListener {
            finish()
        }

        binding.exitButton.setOnClickListener {
            finishAffinity()
        }
    }
}