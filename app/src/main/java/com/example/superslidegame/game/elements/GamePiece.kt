package com.example.superslidegame.game.elements

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * GamePiece is a class that represents a piece in the game.
 * @param type The type of piece.
 * @param groupId The group id of the piece.
 * @param rotation The rotation of the piece.
 * @param imgSrc The image source of the piece.
 * @constructor Creates a GamePiece.
 * @see PieceType
 */
@Parcelize
class GamePiece(val type: PieceType, val groupId: Int, val rotation: Float = 0f, val imgSrc: Int = type.imgSrc) : Parcelable {
    constructor(type: PieceType) : this(type, 0)
}