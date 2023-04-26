package com.example.superslidegame.game.elements

import android.os.Bundle

data class GameState(val nickname : String, val difficulty : String, val level : Int) {
    companion object {
        fun fromBundle(bundle: Bundle) : GameState {
            return GameState(
                bundle.getString("nickname")!!,
                bundle.getString("difficulty")!!,
                bundle.getInt("level"),
            )
        }
    }
    fun toBundle() : Bundle {
        val bundle = Bundle()
        bundle.putString("nickname", nickname)
        bundle.putString("difficulty", difficulty)
        bundle.putInt("level", level)
        return bundle
    }
}
