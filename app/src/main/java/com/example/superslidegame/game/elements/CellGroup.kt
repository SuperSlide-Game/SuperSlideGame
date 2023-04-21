package com.example.superslidegame.game.elements

class CellGroup(val id: Int, val pieces: List<GamePiece>) {
    fun getSize() : Int {
        return pieces.size
    }
    fun getCell(pos:Int) : GamePiece {
        return pieces[pos]
    }
}