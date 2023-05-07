package com.example.superslidegame.log

import com.example.superslidegame.game.elements.GameState

class Logger(initialState: GameState) {
    private val nickname : String = initialState.nickname
    private val difficulty : String = initialState.difficulty
    private val level : Int = initialState.level
    private val wonLevels : MutableList<Int> = mutableListOf()
    private var time : Long = 0
    private var result : Boolean = false

    init {
        instance = this
    }

    companion object {
        var moves : Int = 0
        var lastLevelMoves : Int = 0
        lateinit var instance : Logger

        fun getLogger() : Logger {
            return instance
        }
    }

    fun setTime(time: Long) {
        this.time = time
    }

    fun setResult(result: Boolean) {
        this.result = result
    }

    fun addWonLevel(level: Int) {
        wonLevels.add(level)
    }

    fun getLog() : String {
        return if (result) {
            "Nickname: $nickname\nDifficulty: $difficulty\nStarting level: $level\nLevels won: $wonLevels\nTotal moves: $moves\nMoves on last level: $lastLevelMoves\nTime left on last level: $time seconds\nResult: Win !!"
        } else {
            "Nickname: $nickname\nDifficulty: $difficulty\nStarting level: $level\nLevels won: $wonLevels\nTotal moves: $moves\nMoves on last level: $lastLevelMoves\nTime left on last level: $time seconds \nResult: Lose :("
        }
    }
}