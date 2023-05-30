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
import com.example.superslidegame.databinding.FragmentTimeUpBinding
import com.example.superslidegame.game.entities.Game
import com.example.superslidegame.log.Logger
import com.example.superslidegame.log.screen.LogScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Fragment for the time up pop up, which appears when the player runs out of time or moves
 */
class TimeUpFragment : DialogFragment() {

    private val binding by lazy { FragmentTimeUpBinding.inflate(layoutInflater) }

    private val gameViewModel: GameViewModel by viewModels {
        GameViewModelFactory((requireActivity().application as GamesApplication).repository)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable = false
        CoroutineScope(Dispatchers.IO).launch {
            val logger = Logger.getLogger()
            val game = Game(0, logger.getNickname(), 0, false, logger.getTimeLeft().toInt(), Logger.lastLevelMoves)
            gameViewModel.insert(game)
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val menuButton : Button = binding.menuButtonLost
        val logButton : Button = binding.logButtonLost

        menuButton.setOnClickListener {
            activity?.finish()
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