package com.example.superslidegame.game.levels

import com.example.superslidegame.game.elements.GamePiece
import com.example.superslidegame.game.elements.PieceGroup

interface Level {
    fun getNumber(): Int
    fun getPieces(): MutableList<GamePiece>
    fun getGroups(): List<PieceGroup>

}