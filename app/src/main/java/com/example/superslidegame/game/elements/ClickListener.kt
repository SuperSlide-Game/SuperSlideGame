package com.example.superslidegame.game.elements

import android.content.Context
import android.view.View
import android.view.View.OnClickListener
import android.widget.Toast

class ClickListener(private val context: Context, private val positionClicked: Int, private val actualState: Array<GamePiece>) : OnClickListener {
    override fun onClick(view: View?) {

        val pieceClicked = actualState[positionClicked]

        when (pieceClicked.type) {
            PieceType.EMPTY -> {
                Toast.makeText(context, "You cannot perform any action in an empty position", Toast.LENGTH_SHORT).show()
            }
            else -> {
                if (canMove(positionClicked, actualState)) {
                    Toast.makeText(context, "You can move this piece", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "You cannot move this piece", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun canMove(positionClicked: Int, actualState: Array<GamePiece>): Boolean {
        val typeOfTheClickedPiece = actualState[positionClicked].type
        when (typeOfTheClickedPiece) {
            PieceType.YELLOW -> {
                return canMoveYellow(positionClicked, actualState)
            }
            PieceType.BLUE -> {
                return canMoveBlue(positionClicked, actualState)
            }
            PieceType.RED -> {
                return canMoveRed(positionClicked, actualState)
            }
            else -> {
                return false
            }
        }
    }

    private fun canMoveRed(positionClicked: Int, actualState: Array<GamePiece>): Boolean {
        TODO("Not yet implemented")
    }

    private fun canMoveBlue(positionClicked: Int, actualState: Array<GamePiece>): Boolean {
        // Piece size is 2, so depending of the orientation, we have to check if the position that is able to be moved to is empty
        // If the piece is horizontal, we have to check if the positions to the right or left are empty
        // If the piece is vertical, we have to check if the positions below or above are empty

        val clickedPiece = actualState[positionClicked]
        val orientationOfTheClickedPiece = clickedPiece.orientation
        val relativeOrderOfTheClickedPiece = clickedPiece.relativeOrder

        // Get the positions of the other part of the piece
        val otherPiecePositions = when (orientationOfTheClickedPiece) {
            Orientation.HORIZONTAL -> {
                when (relativeOrderOfTheClickedPiece) {
                    1 -> {
                        arrayOf(positionClicked + 1, positionClicked + 2)
                    }
                    2 -> {
                        arrayOf(positionClicked - 1, positionClicked + 1)
                    }
                    else -> {
                        arrayOf(positionClicked - 2, positionClicked - 1)
                    }
                }
            }
            Orientation.VERTICAL -> {
                when (relativeOrderOfTheClickedPiece) {
                    1 -> {
                        arrayOf(positionClicked + 4, positionClicked + 8)
                    }
                    2 -> {
                        arrayOf(positionClicked - 4, positionClicked + 4)
                    }
                    else -> {
                        arrayOf(positionClicked - 8, positionClicked - 4)
                    }
                }
            }
            else -> {
                arrayOf()
            }
        }

        Toast.makeText(context, "Other piece positions: ${otherPiecePositions[0]}, ${otherPiecePositions[1]}", Toast.LENGTH_SHORT).show()

        return isAnySurroundingPieceEmpty(positionClicked, actualState, orientationOfTheClickedPiece) ||
                isAnySurroundingPieceEmpty(otherPiecePositions[0], actualState, orientationOfTheClickedPiece) ||
                isAnySurroundingPieceEmpty(otherPiecePositions[1], actualState, orientationOfTheClickedPiece)
    }

    private fun canMoveYellow(positionClicked: Int, actualState: Array<GamePiece>): Boolean {
        // Piece size is 1, so we have to check if any of the surrounding pieces is empty
        return isAnySurroundingPieceEmpty(positionClicked, actualState)
    }

    private fun isAnySurroundingPieceEmpty(positionClicked: Int, actualState: Array<GamePiece>): Boolean {
        val surroundingPiecesInsideBoard = getSurroundingPositionsInsideBoard(positionClicked)

        return surroundingPiecesInsideBoard.any { actualState[it].type == PieceType.EMPTY }
    }

    private fun isAnySurroundingPieceEmpty(positionClicked: Int, actualState: Array<GamePiece>, pieceOrientation: Orientation?): Boolean {
        // Checks if the piece of the direction we want to check is empty

        val surroundingPiecesInsideBoard = getSurroundingPositionsInsideBoard(positionClicked)

        val emptySurroundingPieces = surroundingPiecesInsideBoard.filter { actualState[it].type == PieceType.EMPTY }

        Toast.makeText(context, "Empty surrounding pieces: $emptySurroundingPieces of the piece $positionClicked", Toast.LENGTH_SHORT).show()

        return when (pieceOrientation) {
            Orientation.HORIZONTAL -> {
                emptySurroundingPieces.any { it == positionClicked - 1 || it == positionClicked + 1 }
            }

            Orientation.VERTICAL -> {
                emptySurroundingPieces.any { it == positionClicked - 4 || it == positionClicked + 4 }
            }

            else -> {
                false
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
        val surroundingPiecesInsideBoard = surroundingPieces.filter { it >= 0 && it < 20 }
        return surroundingPiecesInsideBoard
    }

}