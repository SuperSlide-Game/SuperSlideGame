package com.example.superslidegame.log

import com.example.superslidegame.game.elements.GameState

class Logger(initialState: GameState) {
    private val nickname : String = initialState.nickname
    private val difficulty : String = initialState.difficulty
    private val level : Int = initialState.level
    private var moves : Int = 0
    private var time : Long = 0
    private var result : Boolean = false

    init {
        instance = this
    }

    companion object {
        lateinit var instance : Logger

        fun getLogger() : Logger {
            return instance
        }
    }

    fun setMoves(moves: Int) {
        this.moves = moves
    }

    fun setTime(time: Long) {
        this.time = time
    }

    fun setResult(result: Boolean) {
        this.result = result
    }

    fun getLog() : String {
        return if (result) {
            "Nickname: $nickname\nDifficulty: $difficulty\nLevel: $level\nMoves: $moves\nTime: $time\nResult: Win !!"
        } else {
            "Nickname: $nickname\nDifficulty: $difficulty\nLevel: $level\nMoves: $moves\nTime: $time\nResult: Lose :("
        }
    }
}