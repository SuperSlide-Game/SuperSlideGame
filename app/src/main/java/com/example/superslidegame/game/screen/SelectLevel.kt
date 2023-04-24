package com.example.superslidegame.game.screen

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.superslidegame.databinding.SelLevelBinding
import com.example.superslidegame.game.elements.LevelListAdapter

/**
 * GameScreen is the level selector screen of the game
 */

class SelectLevel : AppCompatActivity() {

    var selectedLevel : Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = SelLevelBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.levelList.adapter = LevelListAdapter(this)

        val intent = Intent(this, GameScreen::class.java)

        binding.playButtonLevelSelector.setOnClickListener {
            if (selectedLevel != null) {
                intent.putExtra("nickname", binding.nicknameEditText.text.toString())
                intent.putExtra("difficulty", binding.difficultySpinner.selectedItem.toString())
                intent.putExtra("level", selectedLevel)
                startActivity(intent)
            }
        }
    }

    fun setLevel(level: Int) {
        selectedLevel = level
    }

}