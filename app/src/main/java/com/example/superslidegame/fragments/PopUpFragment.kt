package com.example.superslidegame.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import com.example.superslidegame.databinding.FragmentPopUpBinding
import com.example.superslidegame.game.screen.GameScreen
import com.example.superslidegame.game.screen.SelectLevel
import com.example.superslidegame.log.screen.LogScreen

/**
 * Fragment for the pop up menu, which appears when the player wins the game
 */
class PopUpFragment : DialogFragment() {

    private lateinit var binding : FragmentPopUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable = false
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentPopUpBinding.inflate(inflater, container, false)
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

        return binding.root
    }
}