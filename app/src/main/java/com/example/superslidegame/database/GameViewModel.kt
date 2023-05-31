package com.example.superslidegame.database

import GameRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.superslidegame.game.entities.Game
import kotlinx.coroutines.launch

class GameViewModel(private val repository: GameRepository) : ViewModel() {

    val allGames: LiveData<List<Game>> = repository.allGames.asLiveData()

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(game: Game) = viewModelScope.launch {
        repository.insert(game)
    }

    fun deleteAll() = viewModelScope.launch {
        repository.deleteAll()
    }
}

class GameViewModelFactory(private val repository: GameRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return GameViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}