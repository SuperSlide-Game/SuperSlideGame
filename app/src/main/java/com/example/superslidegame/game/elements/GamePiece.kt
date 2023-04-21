package com.example.superslidegame.game.elements

class GamePiece(val type: PieceType, val orientation: Orientation?, val groupId: Int) {
    val imgSrc = type.imgSrc

    constructor(type: PieceType) : this(type, null, 0)

    init {
        if (type == PieceType.BLUE && orientation == null) {
            throw IllegalArgumentException("Cannot create a piece of type $type without an orientation")
        }
    }
}