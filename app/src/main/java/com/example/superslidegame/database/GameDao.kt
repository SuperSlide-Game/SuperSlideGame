package com.example.superslidegame.database

import androidx.room.*
import com.example.superslidegame.game.entities.Game
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {

    @Query("SELECT * FROM game_table ORDER BY id DESC")
    fun getGameHistory(): Flow<List<Game>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(game: Game)

    @Query("DELETE FROM game_table")
    suspend fun deleteAll(): Int
}