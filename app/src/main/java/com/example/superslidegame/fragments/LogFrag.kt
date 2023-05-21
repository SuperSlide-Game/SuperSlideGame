package com.example.superslidegame.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.superslidegame.databinding.LogFragmentBinding
import com.example.superslidegame.log.Logger

class LogFrag : Fragment() {

    private val binding by lazy { LogFragmentBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            binding.log.text = savedInstanceState.getString("log")
        } else {
            binding.log.text = Logger.getLogger().getBasicInformation()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString("log", binding.log.text.toString())
    }

    fun updateLog(text : String) {
        binding.log.text = String.format("%s\n%s", binding.log.text, text)
    }
}