package com.example.findhim.persistency

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.superslidegame.database.GameDao
import com.example.superslidegame.game.entities.Game

@Database(
    entities = [Game::class],
    version = 1
)

abstract class GameRoomDatabase: RoomDatabase() {
    abstract fun gameDao(): GameDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: GameRoomDatabase? = null

        fun getDatabase(context: Context): GameRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GameRoomDatabase::class.java,
                    "game_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }

    }
}