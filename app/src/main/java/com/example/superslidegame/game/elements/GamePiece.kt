package com.example.superslidegame.game.elements

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class GamePiece(val type: PieceType, val groupId: Int, val imgSrc: Int = type.imgSrc, val rotation: Float = 0f) : Parcelable {
    constructor(type: PieceType) : this(type, 0)
}