package com.example.superslidegame.game.screen

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.superslidegame.databinding.SelLevelBinding
import com.example.superslidegame.game.elements.GameState
import com.example.superslidegame.game.elements.LevelListAdapter
import com.example.superslidegame.log.Logger

/**
 * SelectLevel is the level selector screen of the game
 * It is the first screen the user sees when they click on the play button
 * It contains a list of levels, a nickname field and a difficulty spinner
 * The user can select a level, enter a nickname and select a difficulty
 * When the user clicks on the play button, the game screen is launched
 */

class SelectLevel : AppCompatActivity() {

    var selectedLevel : Int? = null
    private val binding by lazy { SelLevelBinding.inflate(layoutInflater) }

    companion object {
        var instance : SelectLevel? = null
    }

    init {
        instance = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.levelList.adapter = LevelListAdapter(this)

        val intent = Intent(this, GameScreen::class.java)

        binding.playButtonLevelSelector.setOnClickListener {

            if (allNecessaryInfoFilled()) {

                val gameStateBundle : Bundle = GameState(
                    binding.nicknameEditText.text.toString(),
                    binding.difficultySpinner.selectedItem.toString(),
                    selectedLevel!!
                ).toBundle()
                Logger(GameState.fromBundle(gameStateBundle))
                Logger.moves = 0
                intent.putExtras(gameStateBundle)
                startActivity(intent)

            } else {
                Toast.makeText(this, "Please fill in all the fields", Toast.LENGTH_SHORT).show()
                if (binding.nicknameEditText.text.isBlank())
                    binding.nicknameEditText.error = "Please enter a nickname"
            }
        }
    }

    private fun allNecessaryInfoFilled(): Boolean {
        return selectedLevel != null && binding.nicknameEditText.text.isBlank().not()
    }

    fun setLevel(level: Int) {
        selectedLevel = level
    }

    fun nextLevel() {
        val intent = Intent(this, GameScreen::class.java)

        selectedLevel = selectedLevel?.plus(1)

        val gameStateBundle : Bundle = GameState(
            binding.nicknameEditText.text.toString(),
            binding.difficultySpinner.selectedItem.toString(),
            selectedLevel!!
        ).toBundle()

        intent.putExtras(gameStateBundle)
        startActivity(intent)
    }

}