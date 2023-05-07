package com.example.superslidegame.game.elements

import android.os.Bundle

/**
 * GameState is a data class that holds all the information about the current state of the game.
 * It is used to save and restore the game state.
 * @param nickname The nickname of the player
 * @param difficulty The difficulty of the game
 * @param level The current level of the game
 * @param board The current board of the game
 * @param timeLeft The time left in the game
 * @param pieceGroups The piece groups of the game
 * @constructor Creates a GameState object
 * @see GamePiece
 * @see PieceGroup
 */
data class GameState(val nickname : String, val difficulty : String, val level : Int, var board : MutableList<GamePiece>? = null, var timeLeft : Long? = null, var pieceGroups : MutableList<PieceGroup>? = null) {

    enum class Type {
        WIN, LOSE, IN_PROGRESS
    }
    companion object {
        /**
         * Creates a GameState object from a Bundle
         */
        fun fromBundle(bundle: Bundle) : GameState {
            return GameState(
                bundle.getString("nickname")!!,
                bundle.getString("difficulty")!!,
                bundle.getInt("level"),
                bundle.getParcelableArray("board")?.map { it as GamePiece }?.toMutableList(),
                bundle.getLong("timeLeft"),
                bundle.getParcelableArray("pieceGroups")?.map { it as PieceGroup }?.toMutableList()
            )
        }
    }

    /**
     * Returns a Bundle representation of the GameState object
     */
    fun toBundle() : Bundle {
        val bundle = Bundle()
        bundle.putString("nickname", nickname)
        bundle.putString("difficulty", difficulty)
        bundle.putInt("level", level)
        bundle.putParcelableArray("board", board?.toTypedArray())
        timeLeft?.let { bundle.putLong("timeLeft", it) }
        bundle.putParcelableArray("pieceGroups", pieceGroups?.toTypedArray())
        return bundle
    }
}
