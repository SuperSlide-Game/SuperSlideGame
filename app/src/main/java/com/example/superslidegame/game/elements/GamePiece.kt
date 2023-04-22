package com.example.superslidegame.game.elements

class GamePiece(val type: PieceType, val groupId: Int) {
    var imgSrc = type.imgSrc

    constructor(type: PieceType) : this(type, 0)
}