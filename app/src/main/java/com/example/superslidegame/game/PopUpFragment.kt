package com.example.superslidegame.game

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import com.example.superslidegame.R
import com.example.superslidegame.log.screen.LogScreen

class PopUpFragment : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_pop_up, container, false)
        val menuButton : Button = view.findViewById(R.id.menu_button_win)
        val nextLevelButton : Button = view.findViewById(R.id.next_level_button_win)
        val logButton : Button = view.findViewById(R.id.log_button_win)

        menuButton.setOnClickListener {
            activity?.finish()
        }

        nextLevelButton.setOnClickListener {
            //
        }

        logButton.setOnClickListener {
            val intent = Intent(activity, LogScreen::class.java)
            startActivity(intent)
            activity?.finish()
        }

        return view
    }
}