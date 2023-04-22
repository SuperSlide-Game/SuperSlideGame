package com.example.superslidegame.game.levels.data

import com.example.superslidegame.game.elements.GamePiece
import com.example.superslidegame.game.elements.Orientation
import com.example.superslidegame.game.elements.PieceGroup
import com.example.superslidegame.game.elements.PieceType
import com.example.superslidegame.game.levels.Level

class Level1 : Level {
    private var pieces: MutableList<GamePiece> = mutableListOf(
        GamePiece(PieceType.YELLOW),
        GamePiece(PieceType.YELLOW),
        GamePiece(PieceType.YELLOW),
        GamePiece(PieceType.EMPTY),
        GamePiece(PieceType.YELLOW),
        GamePiece(PieceType.YELLOW),
        GamePiece(PieceType.YELLOW),
        GamePiece(PieceType.YELLOW),
        GamePiece(PieceType.EMPTY),
        GamePiece(PieceType.BLUE, 1),
        GamePiece(PieceType.BLUE, 1),
        GamePiece(PieceType.YELLOW),
        GamePiece(PieceType.RED, 3),
        GamePiece(PieceType.RED, 3),
        GamePiece(PieceType.BLUE, 2),
        GamePiece(PieceType.YELLOW),
        GamePiece(PieceType.RED, 3),
        GamePiece(PieceType.RED, 3),
        GamePiece(PieceType.BLUE, 2),
        GamePiece(PieceType.YELLOW),
    )

    private var groups: List<PieceGroup>

    init {

        val blueGroup1 = PieceGroup(1, Orientation.HORIZONTAL, listOf(
            pieces[9],
            pieces[10],
        ))
        val blueGroup2 = PieceGroup(2, Orientation.VERTICAL, listOf(
            pieces[14],
            pieces[18],
        ))

        val redGroup = PieceGroup(3, listOf(
            pieces[12],
            pieces[13],
            pieces[17],
            pieces[17],
        ))


        groups = listOf(
            blueGroup1,
            blueGroup2,
            redGroup,
        )
    }

    override fun getNumber(): Int {
        return 1
    }

    override fun getPieces(): MutableList<GamePiece> {
        return pieces
    }

    override fun getGroups(): List<PieceGroup> {
        return groups
    }
}