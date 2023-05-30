package com.example.superslidegame.database

import GameRepository
import android.app.Application
import com.example.findhim.persistency.GameRoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class GamesApplication : Application() {
    val applicationScope = CoroutineScope(SupervisorJob())
    val database by lazy { GameRoomDatabase.getDatabase(this) }
    val repository by lazy { GameRepository(database.gameDao()) }
}