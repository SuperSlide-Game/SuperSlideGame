package com.example.superslidegame.game.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "game_table")
data class Game(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val nickname: String,
    val level: Int,
    val won: Boolean,
    val time: Int,
    val moves: Int)