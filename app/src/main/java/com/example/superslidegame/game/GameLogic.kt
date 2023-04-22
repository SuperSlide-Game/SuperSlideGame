package com.example.superslidegame.game

import android.content.Context
import android.widget.Toast
import com.example.superslidegame.game.elements.GamePiece
import com.example.superslidegame.game.elements.ImageAdapter
import com.example.superslidegame.game.elements.Orientation
import com.example.superslidegame.game.elements.PieceType

class GameLogic(private val context: Context, private val adapter: ImageAdapter) {

    fun whereToMove(positionClicked: Int, actualState: Array<GamePiece>): Int? {
        return when (actualState[positionClicked].type) {
            PieceType.YELLOW -> {
                whereToMoveYellow(positionClicked, actualState)
            }

            PieceType.BLUE -> {
                whereToMoveBlue(positionClicked, actualState)
            }

            PieceType.RED -> {
                whereToMoveRed(positionClicked, actualState)
            }

            else -> {
                null
            }
        }
    }

    fun move(positionClicked: Int, positionToMove: Int, actualState: Array<GamePiece>) {
        adapter.movePiece(actualState[positionClicked], positionToMove)
        adapter.notifyDataSetChanged()
    }

    private fun whereToMoveRed(positionClicked: Int, actualState: Array<GamePiece>): Int? {
        TODO("Not yet implemented")
    }

    private fun whereToMoveBlue(positionClicked: Int, actualState: Array<GamePiece>): Int? {
        // Piece size is 2, so depending of the orientation, we have to check if the position that is able to be moved to is empty
        // If the piece is horizontal, we have to check if the positions to the right or left are empty
        // If the piece is vertical, we have to check if the positions below or above are empty

        val clickedPiece = actualState[positionClicked]

        val pieceGroup = adapter.getGroup(clickedPiece.groupId)
        val orientationOfThePieceGroup = pieceGroup.orientation
        val piecesOfThePieceGroup = pieceGroup.pieces

        val whereToMovePiece1 = getAnySurroundingPieceEmpty(adapter.getPositionOfPiece(piecesOfThePieceGroup[0]), actualState, orientationOfThePieceGroup)
        val whereToMovePiece2 = getAnySurroundingPieceEmpty(adapter.getPositionOfPiece(piecesOfThePieceGroup[1]), actualState, orientationOfThePieceGroup)

        // If any of the pieces can be moved, we return the position of the piece that can be moved or null if none of them can be moved
        return whereToMovePiece1 ?: whereToMovePiece2
    }

    private fun whereToMoveYellow(positionClicked: Int, actualState: Array<GamePiece>): Int? {
        // Piece size is 1, so we have to check if any of the surrounding pieces is empty
        return getAnySurroundingPieceEmpty(positionClicked, actualState)
    }

    private fun getAnySurroundingPieceEmpty(positionClicked: Int, actualState: Array<GamePiece>): Int? {
        val surroundingPiecesInsideBoard = getSurroundingPositionsInsideBoard(positionClicked)

        // Return the first piece that is empty
        val gamePiece = surroundingPiecesInsideBoard.map { actualState[it] }.firstOrNull { it.type == PieceType.EMPTY }
        return gamePiece?.let { adapter.getPositionOfPiece(it) }
    }

    private fun getAnySurroundingPieceEmpty(positionClicked: Int, actualState: Array<GamePiece>, pieceOrientation: Orientation?): Int? {
        // Checks if the piece of the direction we want to check is empty

        val surroundingPiecesInsideBoard = getSurroundingPositionsInsideBoard(positionClicked)

        val emptySurroundingPieces = surroundingPiecesInsideBoard.filter { actualState[it].type == PieceType.EMPTY }

        Toast.makeText(context, "Empty surrounding pieces: $emptySurroundingPieces of the piece $positionClicked", Toast.LENGTH_SHORT).show()

        return when (pieceOrientation) {
            Orientation.HORIZONTAL -> {
                val gamePiece = emptySurroundingPieces.firstOrNull { it == positionClicked - 1 || it == positionClicked + 1 }
                gamePiece?.let { adapter.getPositionOfPiece(actualState[it]) }
            }

            Orientation.VERTICAL -> {
                val gamePiece = emptySurroundingPieces.firstOrNull { it == positionClicked - 4 || it == positionClicked + 4 }
                gamePiece?.let { adapter.getPositionOfPiece(actualState[it]) }
            }

            else -> {
                null
            }
        }
    }

    private fun getSurroundingPositionsInsideBoard(positionClicked: Int): List<Int> {
        val row = positionClicked / 4
        val column = positionClicked % 4
        val surroundingPieces = listOf(
            (row - 1) * 4 + column,
            (row + 1) * 4 + column,
            row * 4 + column - 1,
            row * 4 + column + 1
        )

        // Check if the surrounding pieces are inside the board
        return surroundingPieces.filter { it in 0..19 }
    }

}