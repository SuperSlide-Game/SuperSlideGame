package com.example.superslidegame.game.levels

import com.example.superslidegame.game.elements.GamePiece
import com.example.superslidegame.game.elements.PieceGroup
import com.example.superslidegame.game.levels.data.Level1

class GameLevel : Level {
    private var activeLevel : Level = Level1()

    fun nextLevel() {
        when (activeLevel.getNumber() + 1) {
            //2 -> activeLevel = Level2()
            //3 -> activeLevel = Level3()
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

    override fun getPieces() : Array<GamePiece> {
        return activeLevel.getPieces()
    }

    override fun getGroups() : Array<PieceGroup> {
        return activeLevel.getGroups()
    }
}