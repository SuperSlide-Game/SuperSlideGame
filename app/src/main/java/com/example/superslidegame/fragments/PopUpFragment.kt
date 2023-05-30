package com.example.superslidegame.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.example.superslidegame.database.GameViewModel
import com.example.superslidegame.database.GameViewModelFactory
import com.example.superslidegame.database.GamesApplication
import com.example.superslidegame.databinding.FragmentPopUpBinding
import com.example.superslidegame.game.entities.Game
import com.example.superslidegame.game.screen.GameScreen
import com.example.superslidegame.game.screen.SelectLevel
import com.example.superslidegame.log.Logger
import com.example.superslidegame.log.Logger.Companion.getLogger
import com.example.superslidegame.log.screen.LogScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Fragment for the pop up menu, which appears when the player wins the game
 */
class PopUpFragment : DialogFragment() {

    private val binding by lazy { FragmentPopUpBinding.inflate(layoutInflater) }

    private val gameViewModel: GameViewModel by viewModels {
        GameViewModelFactory((requireActivity().application as GamesApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable = false
        CoroutineScope(Dispatchers.IO).launch {
            val logger : Logger = getLogger()
            val game = Game(0, logger.getNickname(), logger.getWonLevels()[logger.getWonLevels().size], true, logger.getTimeLeft().toInt(), Logger.lastLevelMoves)
            gameViewModel.insert(game)
        }

    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val menuButton : Button = binding.menuButtonWin
        val nextLevelButton : Button = binding.nextLevelButtonWin
        val logButton : Button = binding.logButtonWin

        if ((activity as GameScreen).isLastLevel()) {
            nextLevelButton.visibility = View.GONE
        } else {
            nextLevelButton.visibility = View.VISIBLE
        }

        menuButton.setOnClickListener {
            activity?.finish()
            dismiss()
        }

        nextLevelButton.setOnClickListener {
            activity?.finish()
            SelectLevel.instance?.nextLevel()
            dismiss()
        }

        logButton.setOnClickListener {
            val intent = Intent(activity, LogScreen::class.java)
            startActivity(intent)
            activity?.finish()
            dismiss()
        }
    }
}