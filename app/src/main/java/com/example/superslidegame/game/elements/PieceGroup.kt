package com.example.superslidegame.game.elements

/**
 * A data class representing a group of individual pieces that are the same one
 * @param id the id of the group
 * @param pieces the pieces that form the group
 */
data class PieceGroup(val id: Int, val orientation: Orientation?, val pieces: MutableList<GamePiece>) {
    constructor(id: Int, pieces: MutableList<GamePiece>) : this(id, null, pieces)
}