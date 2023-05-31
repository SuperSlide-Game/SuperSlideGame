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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (!savedInstanceState?.getString("register").isNullOrEmpty()) {
            binding.register.text = savedInstanceState?.getString("register")
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("register", binding.register.text.toString())
    }

    fun update(game: Game?) {
        val nickname = game?.nickname
        val level = game?.level
        val won = game?.won
        val time = game?.time
        val moves = game?.moves
        binding.register.text = String.format(
            "Nickname: %s\nLevel: %s\nWon: %s\nTime: %s\nMoves: %s",
            nickname,
            level,
            won,
            time,
            moves)
    }
}