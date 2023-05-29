package com.example.superslidegame.main

import NewGameActivity
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.superslidegame.R
import com.example.superslidegame.database.GameListAdapter
import com.example.superslidegame.database.GameViewModel
import com.example.superslidegame.database.GameViewModelFactory
import com.example.superslidegame.database.GamesApplication
import com.example.superslidegame.databinding.ActivityMainBinding
import com.example.superslidegame.game.entities.Game
import com.example.superslidegame.game.screen.GameScreen
import com.example.superslidegame.game.screen.HelpScreen
import com.example.superslidegame.game.screen.SelectLevel
import com.example.superslidegame.settings.SettingsActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

/**
 * Main activity of the application.
 * This activity is the first activity that is launched when the application is started.
 * It contains three buttons, one to start the game, one to go to the help screen, and one to go to the settings screen.
 * Every screen is started when the corresponding button is clicked.
 * @constructor Creates a new main activity.
 * @see AppCompatActivity
 * @see GameScreen
 * @see HelpScreen
 * @see SettingsActivity
 */
class MainActivity : AppCompatActivity() {
    /**
     * Creates the main activity.
     * This function is called when the activity is first created.
     * It sets the content view to the main activity layout.
     * It also sets the on click listeners for the buttons.
     * @param savedInstanceState The saved instance state.
     */
    private val gameViewModel: GameViewModel by viewModels {
        GameViewModelFactory((application as GamesApplication).repository)
    }
    private val newGameActivityRequestCode = 1
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = GameListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        gameViewModel.allGames.observe(this, Observer { games ->
            // Update the cached copy of the words in the adapter.
            games?.let { adapter.submitList(it) }
        })

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, NewGameActivity::class.java)
            startActivityForResult(intent, newGameActivityRequestCode)
        }

        binding.playButton.setOnClickListener {
            val intent = Intent(this, SelectLevel::class.java)
            startActivity(intent)
        }

        binding.settingsButton.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }
        binding.helpButton.setOnClickListener {
            val intent = Intent(this, HelpScreen::class.java)
            startActivity(intent)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)

        if (requestCode == newGameActivityRequestCode && resultCode == Activity.RESULT_OK) {
            intentData?.getStringExtra(NewGameActivity.EXTRA_REPLY)?.let { reply ->
                val word = Game(4,"repy",2,true,3,4)
                gameViewModel.insert(word)
            }
        } else {
            Toast.makeText(
                applicationContext,
                R.string.empty_not_saved,
                Toast.LENGTH_LONG
            ).show()
        }
    }

}
