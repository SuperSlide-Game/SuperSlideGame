package com.example.superslidegame.game.elements

import android.os.Bundle

data class GameState(val nickname : String, val difficulty : String, val level : Int, var board : MutableList<GamePiece>? = null) {
    enum class Type {
        WIN, LOSE, IN_PROGRESS
    }
    companion object {
        fun fromBundle(bundle: Bundle) : GameState {
            return GameState(
                bundle.getString("nickname")!!,
                bundle.getString("difficulty")!!,
                bundle.getInt("level"),
                bundle.getParcelableArray("board")?.map { it as GamePiece }?.toMutableList()
            )
        }
    }
    fun toBundle() : Bundle {
        val bundle = Bundle()
        bundle.putString("nickname", nickname)
        bundle.putString("difficulty", difficulty)
        bundle.putInt("level", level)
        bundle.putParcelableArray("board", board?.toTypedArray())
        return bundle
    }
}
