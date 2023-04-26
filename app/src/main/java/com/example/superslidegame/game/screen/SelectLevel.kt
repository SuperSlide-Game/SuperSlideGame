package com.example.superslidegame.game.screen

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.superslidegame.databinding.SelLevelBinding
import com.example.superslidegame.game.elements.GameState
import com.example.superslidegame.game.elements.LevelListAdapter

/**
 * GameScreen is the level selector screen of the game
 */

class SelectLevel : AppCompatActivity() {

    var selectedLevel : Int? = null
    private val binding by lazy { SelLevelBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.levelList.adapter = LevelListAdapter(this)

        val intent = Intent(this, GameScreen::class.java)

        binding.playButtonLevelSelector.setOnClickListener {

            if (alNecessaryInfoFilled()) {

                val gameStateBundle : Bundle = GameState(
                    binding.nicknameEditText.text.toString(),
                    binding.difficultySpinner.selectedItem.toString(),
                    selectedLevel!!
                ).toBundle()
                intent.putExtras(gameStateBundle)
                startActivity(intent)

            } else {
                Toast.makeText(this, "Please fill in all the fields", Toast.LENGTH_SHORT).show()
                if (binding.nicknameEditText.text.isBlank())
                    binding.nicknameEditText.error = "Please enter a nickname"
            }
        }
    }

    private fun alNecessaryInfoFilled(): Boolean {
        return selectedLevel != null && binding.nicknameEditText.text.isBlank().not()
    }

    fun setLevel(level: Int) {
        selectedLevel = level
    }

}