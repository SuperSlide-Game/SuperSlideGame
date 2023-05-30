package com.example.superslidegame.history

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.superslidegame.R
import com.example.superslidegame.databinding.ActivityAccessDbBinding
import com.example.superslidegame.fragments.RegFrag
import com.example.superslidegame.game.entities.Game

class AccessDBActivity : AppCompatActivity() {

    private val binding by lazy { ActivityAccessDbBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    fun launchDetailsActivity(game: Game?) {
        val regFragment = supportFragmentManager.findFragmentById(R.id.register_fragment) as RegFrag?
        if (regFragment == null) {
            val intent = Intent(this, DetailRegActivity::class.java)
            intent.putExtra("game", game)
            startActivity(intent)
        } else {
            regFragment.update(game)
        }
    }

    companion object {
        var INSTANCE: AccessDBActivity? = null
    }

    init {
        INSTANCE = this
    }
}