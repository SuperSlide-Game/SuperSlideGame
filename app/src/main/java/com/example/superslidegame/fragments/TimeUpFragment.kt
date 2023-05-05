package com.example.superslidegame.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import com.example.superslidegame.databinding.FragmentTimeUpBinding
import com.example.superslidegame.log.screen.LogScreen


class TimeUpFragment : DialogFragment() {

    private lateinit var binding : FragmentTimeUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setStyle(STYLE_NO_TITLE, android.R.style.Theme_DeviceDefault_Light_Dialog_NoActionBar_MinWidth)
        isCancelable = false
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentTimeUpBinding.inflate(inflater, container, false)
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

        return binding.root
    }

}