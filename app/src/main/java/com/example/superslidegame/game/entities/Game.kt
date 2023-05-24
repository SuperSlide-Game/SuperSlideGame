package com.example.superslidegame.game.entities

import androidx.room.Entity

@Entity(tableName = "game_table")
data class Game(val level: Int, val score: Int, val time: Int) {

}