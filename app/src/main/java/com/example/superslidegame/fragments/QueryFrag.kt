package com.example.superslidegame.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.superslidegame.database.GameListAdapter
import com.example.superslidegame.database.GameViewModel
import com.example.superslidegame.database.GameViewModelFactory
import com.example.superslidegame.database.GamesApplication
import com.example.superslidegame.databinding.QueryFragmentBinding

class QueryFrag : Fragment() {

    private val binding by lazy { QueryFragmentBinding.inflate(layoutInflater) }

    private val gameViewModel: GameViewModel by viewModels {
        GameViewModelFactory((activity?.application as GamesApplication).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerView = binding.recyclerview
        val adapter = GameListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(activity)

        gameViewModel.allGames.observe(viewLifecycleOwner) { games ->
            // Update the cached copy of the games in the adapter.
            games.let { adapter.submitList(it) }
        }
    }
}