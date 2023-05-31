package com.example.superslidegame.game.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "game_table")
@Parcelize
data class Game(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val nickname: String,
    val level: Int,
    val won: Boolean,
    val time: Int,
    val moves: Int) : Parcelable