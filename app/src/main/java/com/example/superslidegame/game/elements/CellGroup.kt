package com.example.superslidegame.game.elements

class CellGroup(val position: Int, val cells: List<GamePiece>) {
    fun getSize() : Int {
        return cells.size
    }
}