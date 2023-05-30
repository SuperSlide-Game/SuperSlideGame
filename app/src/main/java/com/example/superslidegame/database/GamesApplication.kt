package com.example.superslidegame.database

import GameRepository
import GameRoomDatabase
import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class GamesApplication : Application() {
    private val applicationScope = CoroutineScope(SupervisorJob())
    val database by lazy { GameRoomDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { GameRepository(database.gameDao()) }
}