package com.example.superslidegame.database

import GameRepository
import android.app.Application

class GamesApplication : Application() {
    val database by lazy { GameRoomDatabase.getDatabase(this) }
    val repository by lazy { GameRepository(database.gameDao()) }
}