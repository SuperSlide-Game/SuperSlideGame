package com.example.superslidegame.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.superslidegame.databinding.GridFragmentBinding
import com.example.superslidegame.game.elements.ImageAdapter

class GridFrag : Fragment() {

    private val binding by lazy { GridFragmentBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    fun updateMoves(moves: Int) {
        binding.moveCounterTextView.text = String.format("Moves: %d", moves)
    }

    fun setGridViewAdapter(adapter: ImageAdapter) {
        binding.gridTiles.adapter = adapter
    }

    fun getTimerTextView() : TextView {
        return binding.timerTextView
    }

    fun setTimerTextText(text: String) {
        binding.timerTextView.text = text
    }

    fun setLevelTextText(text: String) {
        binding.levelTextView.text = text
    }
}