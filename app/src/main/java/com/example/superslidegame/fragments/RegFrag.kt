package com.example.superslidegame.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.superslidegame.databinding.RegFragmentBinding
import com.example.superslidegame.game.entities.Game

class RegFrag : Fragment() {

    private val binding by lazy { RegFragmentBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    fun update(game: Game?) {
        // Update UI with game details
    }
}