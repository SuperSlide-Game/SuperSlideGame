package com.example.superslidegame.game.levels

import com.example.superslidegame.game.elements.GamePiece
import com.example.superslidegame.game.elements.PieceGroup
import com.example.superslidegame.game.levels.data.Level1
import com.example.superslidegame.game.levels.data.Level2
import com.example.superslidegame.game.levels.data.Level3
import com.example.superslidegame.game.levels.data.Level4
import com.example.superslidegame.game.levels.data.Level5

/**
 * GameLevel is a class that represents a level in the game.
 * It contains the pieces and groups of pieces that are used in the level.
 */
class GameLevel(levelNumber: Int?) : Level {

    companion object {
        const val MAX_LEVEL = 5
    }

    private var activeLevel : Level

    init {
        activeLevel = when (levelNumber) {
            1 -> Level1()
            2 -> Level2()
            3 -> Level3()
            4 -> Level4()
            5 -> Level5()
            else -> throw NumberFormatException("Maximum level implemented is $MAX_LEVEL")
        }
    }

    override fun getNumber(): Int {
        return activeLevel.getNumber()
    }

    override fun setPieces(pieces: MutableList<GamePiece>) {
        activeLevel.setPieces(pieces)
    }

    override fun setGroups(groups: MutableList<PieceGroup>) {
        activeLevel.setGroups(groups)
    }

    override fun getPieces(): MutableList<GamePiece> {
        return activeLevel.getPieces()
    }

    override fun getGroups(): MutableList<PieceGroup> {
        return activeLevel.getGroups()
    }
}