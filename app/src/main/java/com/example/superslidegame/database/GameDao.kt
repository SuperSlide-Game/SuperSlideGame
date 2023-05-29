package com.example.superslidegame.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.superslidegame.game.entities.Game

@Dao
interface GameDao {

    @Query("SELECT * FROM game_table ORDER BY id DESC")
    fun getGameHistory(): List<Game>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(game: Game)

    @Query("DELETE FROM game_table")
    suspend fun deleteAll()
}