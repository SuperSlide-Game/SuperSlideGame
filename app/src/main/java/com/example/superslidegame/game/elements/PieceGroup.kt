package com.example.superslidegame.game.elements

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * A data class representing a group of individual pieces that are the same one
 * @param id the id of the group
 * @param pieces the pieces that form the group
 */

// It is very important that when defining game levels, if you create an HORIZONTAL group, the first piece of the group is the leftmost one,
// and if you create a VERTICAL group, the first piece of the group is the topmost one.
@Parcelize
data class PieceGroup(val id: Int, val orientation: Orientation?, val pieces: MutableList<GamePiece>) :
    Parcelable {
    constructor(id: Int, pieces: MutableList<GamePiece>) : this(id, null, pieces)
}