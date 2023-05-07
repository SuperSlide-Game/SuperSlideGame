package com.example.superslidegame.game.levels

import com.example.superslidegame.game.elements.GamePiece
import com.example.superslidegame.game.elements.PieceGroup

/**
 * Interface for a level
 */
interface Level {
    fun getNumber(): Int
    fun getPieces(): MutableList<GamePiece>
    fun setPieces(pieces: MutableList<GamePiece>)
    fun setGroups(groups: MutableList<PieceGroup>)
    fun getGroups(): MutableList<PieceGroup>

}