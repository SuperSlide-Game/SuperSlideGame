package com.example.superslidegame.game.screen

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import com.example.superslidegame.R
import com.example.superslidegame.log.screen.LogScreen


class TimeUpFragment : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_time_up, container, false)
        val menuButton : Button = view.findViewById(R.id.menu_button_lost)
        val logButton : Button = view.findViewById(R.id.log_button_lost)

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

        return view
    }

}