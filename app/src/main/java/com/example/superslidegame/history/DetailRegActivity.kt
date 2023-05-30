package com.example.superslidegame.history

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.superslidegame.R
import com.example.superslidegame.databinding.ActivityDetailRegBinding
import com.example.superslidegame.fragments.RegFrag
import com.example.superslidegame.game.entities.Game

class DetailRegActivity : AppCompatActivity() {

    private val binding by lazy { ActivityDetailRegBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val game = intent.getParcelableExtra("game") as Game?
        val regFragment = supportFragmentManager.findFragmentById(R.id.register_fragment) as RegFrag
        regFragment.update(game)
    }
}