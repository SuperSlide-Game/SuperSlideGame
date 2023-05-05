package com.example.superslidegame.game.levels

import com.example.superslidegame.game.elements.GamePiece
import com.example.superslidegame.game.elements.PieceGroup
import com.example.superslidegame.game.levels.data.Level1
import com.example.superslidegame.game.levels.data.Level2
import com.example.superslidegame.game.levels.data.Level3

class GameLevel(levelNumber: Int?) : Level {

    companion object {
        const val MAX_LEVEL = 10
    }

    private var activeLevel : Level

    init {
        activeLevel = when (levelNumber) {
            1 -> Level1()
            2 -> Level2()
            3 -> Level3()
            //4 -> Level4()
            //5 -> Level5()
            //6 -> Level6()
            //7 -> Level7()
            //8 -> Level8()
            //9 -> Level9()
            //10 -> Level10()
            else -> throw NumberFormatException("Maximum level implemented is 10")
        }
    }

    fun nextLevel() {
        when (activeLevel.getNumber() + 1) {
            2 -> activeLevel = Level2()
            3 -> activeLevel = Level3()
            //4 -> activeLevel = Level4()
            //5 -> activeLevel = Level5()
            //6 -> activeLevel = Level6()
            //7 -> activeLevel = Level7()
            //8 -> activeLevel = Level8()
            //9 -> activeLevel = Level9()
            //10 -> activeLevel = Level10()
        }
    }

    override fun getNumber(): Int {
        return activeLevel.getNumber()
    }

    override fun setPieces(pieces: MutableList<GamePiece>) {
        activeLevel.setPieces(pieces)
    }

    override fun getPieces(): MutableList<GamePiece> {
        return activeLevel.getPieces()
    }

    override fun getGroups(): MutableList<PieceGroup> {
        return activeLevel.getGroups()
    }
}