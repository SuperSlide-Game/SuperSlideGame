package com.example.superslidegame.game.levels.data

import com.example.superslidegame.game.elements.GamePiece
import com.example.superslidegame.game.elements.Orientation
import com.example.superslidegame.game.elements.PieceGroup
import com.example.superslidegame.game.elements.PieceType
import com.example.superslidegame.game.levels.Level

class Level1 : Level {
    private var pieces: Array<GamePiece> = arrayOf(
        GamePiece(PieceType.YELLOW),
        GamePiece(PieceType.YELLOW),
        GamePiece(PieceType.YELLOW),
        GamePiece(PieceType.EMPTY),
        GamePiece(PieceType.YELLOW),
        GamePiece(PieceType.YELLOW),
        GamePiece(PieceType.YELLOW),
        GamePiece(PieceType.YELLOW),
        GamePiece(PieceType.EMPTY),
        GamePiece(PieceType.BLUE, Orientation.HORIZONTAL, 1),
        GamePiece(PieceType.BLUE, Orientation.HORIZONTAL, 1),
        GamePiece(PieceType.YELLOW),
        GamePiece(PieceType.RED, Orientation.HORIZONTAL, 3),
        GamePiece(PieceType.RED, Orientation.HORIZONTAL, 3),
        GamePiece(PieceType.BLUE, Orientation.VERTICAL, 2),
        GamePiece(PieceType.YELLOW),
        GamePiece(PieceType.RED, Orientation.HORIZONTAL, 3),
        GamePiece(PieceType.RED, Orientation.HORIZONTAL, 3),
        GamePiece(PieceType.BLUE, Orientation.VERTICAL, 2),
        GamePiece(PieceType.YELLOW),
    )

    private var groups: Array<PieceGroup>

    init {

        val blueGroup1 = PieceGroup(1, listOf(
            pieces[9],
            pieces[10],
        ))
        val blueGroup2 = PieceGroup(2, listOf(
            pieces[14],
            pieces[18],
        ))

        val redGroup = PieceGroup(3, listOf(
            pieces[12],
            pieces[13],
            pieces[17],
            pieces[17],
        ))


        groups = arrayOf(
            blueGroup1,
            blueGroup2,
            redGroup,
        )
    }

    override fun getNumber(): Int {
        return 1
    }

    override fun getPieces(): Array<GamePiece> {
        return pieces
    }

    override fun getGroups(): Array<PieceGroup> {
        return groups
    }
}