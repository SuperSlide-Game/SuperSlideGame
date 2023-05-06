package com.example.superslidegame.game

import android.content.Context
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.superslidegame.fragments.PopUpFragment
import com.example.superslidegame.game.elements.Direction
import com.example.superslidegame.game.elements.GamePiece
import com.example.superslidegame.game.elements.GameState
import com.example.superslidegame.game.elements.ImageAdapter
import com.example.superslidegame.game.elements.Orientation
import com.example.superslidegame.game.elements.PieceType
import com.example.superslidegame.log.Logger

val WINNING_POSITIONS = arrayOf(13, 14, 17, 18)

class GameLogic(private val context: Context, private val adapter: ImageAdapter) {

    companion object {
        var GAME_STATE : GameState.Type = GameState.Type.IN_PROGRESS
        private val logger = Logger.getLogger()

        fun onLose(seconds : Long) {
            GAME_STATE = GameState.Type.LOSE
            logger.setResult(false)
            logger.setTime(seconds)
        }
    }

    fun whereToMove(positionClicked: Int, actualState: List<GamePiece>): Any? {
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

    fun move(positionClicked: Int, positionToMove: Any, actualState: List<GamePiece>) {
        Logger.moves++
        when (actualState[positionClicked].type) {
            PieceType.YELLOW -> {
                moveYellowPiece(actualState[positionClicked], positionToMove as Int)
            }

            PieceType.BLUE -> {
                moveBluePiece(actualState[positionClicked], positionToMove as Direction)
            }

            PieceType.RED -> {
                moveRedPiece(actualState[positionClicked], positionToMove as Direction)
            }

            else -> {}
        }
    }

    private fun moveRedPiece(piece: GamePiece, moveTo: Direction) {
        val pieceGroup = adapter.getGroup(piece.groupId)
        val pieces = pieceGroup.pieces
        val leftUpperCornerPosition = adapter.getPositionOfPiece(pieces[0])
        val rightUpperCornerPosition = adapter.getPositionOfPiece(pieces[1])
        val leftLowerCornerPosition = adapter.getPositionOfPiece(pieces[2])
        val rightLowerCornerPosition = adapter.getPositionOfPiece(pieces[3])

        when (moveTo) {
            Direction.RIGHT -> {
                adapter.swapPositions(rightUpperCornerPosition, rightUpperCornerPosition + 1)
                adapter.swapPositions(rightLowerCornerPosition, rightLowerCornerPosition + 1)
                adapter.swapPositions(leftUpperCornerPosition, leftUpperCornerPosition + 1)
                adapter.swapPositions(leftLowerCornerPosition, leftLowerCornerPosition + 1)
            }

            Direction.LEFT -> {
                adapter.swapPositions(leftUpperCornerPosition, leftUpperCornerPosition - 1)
                adapter.swapPositions(leftLowerCornerPosition, leftLowerCornerPosition - 1)
                adapter.swapPositions(rightUpperCornerPosition, rightUpperCornerPosition - 1)
                adapter.swapPositions(rightLowerCornerPosition, rightLowerCornerPosition - 1)
            }

            Direction.DOWN -> {
                adapter.swapPositions(leftLowerCornerPosition, leftLowerCornerPosition + 4)
                adapter.swapPositions(rightLowerCornerPosition, rightLowerCornerPosition + 4)
                adapter.swapPositions(leftUpperCornerPosition, leftUpperCornerPosition + 4)
                adapter.swapPositions(rightUpperCornerPosition, rightUpperCornerPosition + 4)


            }

            Direction.UP -> {
                adapter.swapPositions(leftUpperCornerPosition, leftUpperCornerPosition - 4)
                adapter.swapPositions(rightUpperCornerPosition, rightUpperCornerPosition - 4)
                adapter.swapPositions(leftLowerCornerPosition, leftLowerCornerPosition - 4)
                adapter.swapPositions(rightLowerCornerPosition, rightLowerCornerPosition - 4)
            }
        }
    }

    private fun moveBluePiece(piece: GamePiece, moveTo: Direction) {
        val pieceGroup = adapter.getGroup(piece.groupId)

        when (pieceGroup.orientation) {
            Orientation.HORIZONTAL -> {
                when (moveTo) {
                    Direction.LEFT -> {
                        val leftPiecePosition = adapter.getPositionOfPiece(pieceGroup.pieces[0])
                        val rightPiecePosition = adapter.getPositionOfPiece(pieceGroup.pieces[1])
                        adapter.swapPositions(leftPiecePosition, leftPiecePosition - 1)
                        adapter.swapPositions(rightPiecePosition, rightPiecePosition - 1)
                    }

                    Direction.RIGHT -> {
                        val leftPiecePosition = adapter.getPositionOfPiece(pieceGroup.pieces[0])
                        val rightPiecePosition = adapter.getPositionOfPiece(pieceGroup.pieces[1])
                        adapter.swapPositions(rightPiecePosition, rightPiecePosition + 1)
                        adapter.swapPositions(leftPiecePosition, leftPiecePosition + 1)
                    }

                    Direction.DOWN -> {
                        val leftPiecePosition = adapter.getPositionOfPiece(pieceGroup.pieces[0])
                        val rightPiecePosition = adapter.getPositionOfPiece(pieceGroup.pieces[1])
                        adapter.swapPositions(leftPiecePosition, leftPiecePosition + 4)
                        adapter.swapPositions(rightPiecePosition, rightPiecePosition + 4)
                    }

                    Direction.UP -> {
                        val leftPiecePosition = adapter.getPositionOfPiece(pieceGroup.pieces[0])
                        val rightPiecePosition = adapter.getPositionOfPiece(pieceGroup.pieces[1])
                        adapter.swapPositions(leftPiecePosition, leftPiecePosition - 4)
                        adapter.swapPositions(rightPiecePosition, rightPiecePosition - 4)
                    }
                }
            }
            Orientation.VERTICAL -> {
                when (moveTo) {
                    Direction.LEFT -> {
                        val upperPiecePosition = adapter.getPositionOfPiece(pieceGroup.pieces[0])
                        val lowerPiecePosition = adapter.getPositionOfPiece(pieceGroup.pieces[1])
                        adapter.swapPositions(upperPiecePosition, upperPiecePosition - 1)
                        adapter.swapPositions(lowerPiecePosition, lowerPiecePosition - 1)
                    }

                    Direction.RIGHT -> {
                        val upperPiecePosition = adapter.getPositionOfPiece(pieceGroup.pieces[0])
                        val lowerPiecePosition = adapter.getPositionOfPiece(pieceGroup.pieces[1])
                        adapter.swapPositions(upperPiecePosition, upperPiecePosition + 1)
                        adapter.swapPositions(lowerPiecePosition, lowerPiecePosition + 1)
                    }

                    Direction.DOWN -> {
                        val upperPiecePosition = adapter.getPositionOfPiece(pieceGroup.pieces[0])
                        val lowerPiecePosition = adapter.getPositionOfPiece(pieceGroup.pieces[1])
                        adapter.swapPositions(lowerPiecePosition, lowerPiecePosition + 4)
                        adapter.swapPositions(upperPiecePosition, upperPiecePosition + 4)
                    }

                    Direction.UP -> {
                        val upperPiecePosition = adapter.getPositionOfPiece(pieceGroup.pieces[0])
                        val lowerPiecePosition = adapter.getPositionOfPiece(pieceGroup.pieces[1])
                        adapter.swapPositions(upperPiecePosition, upperPiecePosition - 4)
                        adapter.swapPositions(lowerPiecePosition, lowerPiecePosition - 4)
                    }
                }
            }

            else -> {}
        }
    }

    private fun moveYellowPiece(piece : GamePiece, moveTo: Int) {
        val pieces = adapter.getPiecesState()

        Toast.makeText(context,
            "State of the moving position: " + pieces[moveTo].type, Toast.LENGTH_SHORT).show()

        adapter.swapPositions(adapter.getPositionOfPiece(piece), moveTo)

        Toast.makeText(context,
            "State of that position after move: " + pieces[moveTo].type, Toast.LENGTH_SHORT).show()
    }

    private fun whereToMoveRed(positionClicked: Int, actualState: List<GamePiece>): Direction? {
        val clickedPiece = actualState[positionClicked]

        val pieceGroup = adapter.getGroup(clickedPiece.groupId)
        val piecesOfThePieceGroup = pieceGroup.pieces

        return directionToMoveRed(actualState, piecesOfThePieceGroup)
    }

    private fun whereToMoveBlue(positionClicked: Int, actualState: List<GamePiece>): Direction? {
        // Piece size is 2, so depending of the orientation, we have to check if the position that is able to be moved to is empty
        // If the piece is horizontal, we have to check if the positions to the right or left are empty
        // If the piece is vertical, we have to check if the positions below or above are empty

        val clickedPiece = actualState[positionClicked]

        val pieceGroup = adapter.getGroup(clickedPiece.groupId)
        val orientationOfThePieceGroup = pieceGroup.orientation
        val piecesOfThePieceGroup = pieceGroup.pieces

        return directionToMove(actualState, orientationOfThePieceGroup, piecesOfThePieceGroup)
    }

    private fun directionToMove(
        actualState: List<GamePiece>,
        orientationOfThePieceGroup: Orientation?,
        piecesOfThePieceGroup: List<GamePiece>
    ): Direction? {
        when(orientationOfThePieceGroup) {
            Orientation.VERTICAL -> {
                val canMoveUpVertically = canMoveUpVertically(actualState, piecesOfThePieceGroup)
                val canMoveDownVertically = canMoveDownVertically(actualState, piecesOfThePieceGroup)
                val canMoveLeftVertically = canMoveLeftVertically(actualState, piecesOfThePieceGroup)
                val canMoveRightVertically = canMoveRightVertically(actualState, piecesOfThePieceGroup)
                return canMoveDownVertically ?: canMoveUpVertically ?: canMoveLeftVertically ?: canMoveRightVertically
            }
            Orientation.HORIZONTAL -> {
                val canMoveLeftHorizontally = canMoveLeftHorizontally(actualState, piecesOfThePieceGroup)
                val canMoveRightHorizontally = canMoveRightHorizontally(actualState, piecesOfThePieceGroup)
                val canMoveUpHorizontally = canMoveUpHorizontally(actualState, piecesOfThePieceGroup)
                val canMoveDownHorizontally = canMoveDownHorizontally(actualState, piecesOfThePieceGroup)
                return canMoveRightHorizontally ?: canMoveLeftHorizontally ?: canMoveUpHorizontally ?: canMoveDownHorizontally
            }
            else -> return null
        }
    }

    private fun directionToMoveRed(
        actualState: List<GamePiece>,
        piecesOfThePieceGroup: List<GamePiece>
    ): Direction? {
            val canMoveUpVertically = canMoveUpRed(actualState, piecesOfThePieceGroup)
            val canMoveDownVertically = canMoveDownRed(actualState, piecesOfThePieceGroup)
            val canMoveLeftVertically = canMoveLeftRed(actualState, piecesOfThePieceGroup)
            val canMoveRightVertically = canMoveRightRed(actualState, piecesOfThePieceGroup)
            return canMoveDownVertically ?: canMoveUpVertically ?: canMoveLeftVertically ?: canMoveRightVertically
    }

    private fun canMoveDownHorizontally(
        actualState: List<GamePiece>,
        piecesOfThePieceGroup: List<GamePiece>
    ): Direction? {
        val leftPiece = piecesOfThePieceGroup[0]
        val leftPiecePosition = adapter.getPositionOfPiece(leftPiece)
        val leftPiecePositionIsInLastRow = pieceIsInLastRow(leftPiecePosition)
        if (leftPiecePositionIsInLastRow) {
            return null
        }
        val rightPiece = piecesOfThePieceGroup[1]
        val rightPiecePosition = adapter.getPositionOfPiece(rightPiece)
        return if (actualState[rightPiecePosition + 4].type == PieceType.EMPTY && actualState[leftPiecePosition + 4].type == PieceType.EMPTY) {
            Direction.DOWN
        } else {
            null
        }
    }

    private fun canMoveUpHorizontally(
        actualState: List<GamePiece>,
        piecesOfThePieceGroup: List<GamePiece>
    ): Direction? {
        val leftPiece = piecesOfThePieceGroup[0]
        val leftPiecePosition = adapter.getPositionOfPiece(leftPiece)
        val leftPiecePositionIsInFirstRow = pieceIsInFirstRow(leftPiecePosition)
        if (leftPiecePositionIsInFirstRow) {
            return null
        }
        val rightPiece = piecesOfThePieceGroup[1]
        val rightPiecePosition = adapter.getPositionOfPiece(rightPiece)
        return if (actualState[rightPiecePosition - 4].type == PieceType.EMPTY && actualState[leftPiecePosition - 4].type == PieceType.EMPTY) {
            Direction.UP
        } else {
            null
        }
    }

    private fun canMoveRightVertically(
        actualState: List<GamePiece>,
        piecesOfThePieceGroup: List<GamePiece>
    ): Direction? {
        val lowerPiece = piecesOfThePieceGroup[1]
        val lowerPiecePosition = adapter.getPositionOfPiece(lowerPiece)
        val lowerPiecePositionIsInLastColumn = pieceIsInLastColumn(lowerPiecePosition)
        if (lowerPiecePositionIsInLastColumn) {
            return null
        }
        val upperPiece = piecesOfThePieceGroup[0]
        val upperPiecePosition = adapter.getPositionOfPiece(upperPiece)
        return if (actualState[upperPiecePosition + 1].type == PieceType.EMPTY && actualState[lowerPiecePosition + 1].type == PieceType.EMPTY) {
            Direction.RIGHT
        } else {
            null
        }
    }
    private fun canMoveRightRed(
        actualState: List<GamePiece>,
        piecesOfThePieceGroup: List<GamePiece>
    ): Direction? {
        val lowerPiece = piecesOfThePieceGroup[1]
        val lowerPiecePosition = adapter.getPositionOfPiece(lowerPiece)
        val lowerPiecePositionIsInLastColumn = pieceIsInLastColumn(lowerPiecePosition)
        if (lowerPiecePositionIsInLastColumn) {
            return null
        }
        val upperPiece = piecesOfThePieceGroup[3]
        val upperPiecePosition = adapter.getPositionOfPiece(upperPiece)
        return if (actualState[upperPiecePosition + 1].type == PieceType.EMPTY && actualState[lowerPiecePosition + 1].type == PieceType.EMPTY) {
            Direction.RIGHT
        } else {
            null
        }
    }
    private fun canMoveLeftVertically(
        actualState: List<GamePiece>,
        piecesOfThePieceGroup: List<GamePiece>
    ): Direction? {
        val upperPiece = piecesOfThePieceGroup[0]
        val upperPiecePosition = adapter.getPositionOfPiece(upperPiece)
        val upperPiecePositionIsInFirstColumn = pieceIsInFirstColumn(upperPiecePosition)
        if (upperPiecePositionIsInFirstColumn) {
            return null
        }
        val lowerPiece = piecesOfThePieceGroup[1]
        val lowerPiecePosition = adapter.getPositionOfPiece(lowerPiece)

        return if (actualState[upperPiecePosition - 1].type == PieceType.EMPTY && actualState[lowerPiecePosition - 1].type == PieceType.EMPTY) {
            Direction.LEFT
        } else {
            null
        }
    }
    private fun canMoveLeftRed(
        actualState: List<GamePiece>,
        piecesOfThePieceGroup: List<GamePiece>
    ): Direction? {
        val upperPiece = piecesOfThePieceGroup[0]
        val upperPiecePosition = adapter.getPositionOfPiece(upperPiece)
        val upperPiecePositionIsInFirstColumn = pieceIsInFirstColumn(upperPiecePosition)
        if (upperPiecePositionIsInFirstColumn) {
            return null
        }
        val lowerPiece = piecesOfThePieceGroup[2]
        val lowerPiecePosition = adapter.getPositionOfPiece(lowerPiece)

        return if (actualState[upperPiecePosition - 1].type == PieceType.EMPTY && actualState[lowerPiecePosition - 1].type == PieceType.EMPTY) {
            Direction.LEFT
        } else {
            null
        }
    }

    private fun canMoveRightHorizontally(
        actualState: List<GamePiece>,
        piecesOfThePieceGroup: List<GamePiece>
    ): Direction? {
        val rightPiece = piecesOfThePieceGroup[1]
        val rightPiecePosition = adapter.getPositionOfPiece(rightPiece)
        val rightPiecePositionIsInLastColumn = pieceIsInLastColumn(rightPiecePosition)
        if (rightPiecePositionIsInLastColumn) {
            return null
        }

        return if (actualState[rightPiecePosition + 1].type == PieceType.EMPTY) {
            Direction.RIGHT
        } else {
            null
        }
    }

    private fun canMoveLeftHorizontally(
        actualState: List<GamePiece>,
        piecesOfThePieceGroup: List<GamePiece>
    ): Direction? {
        val leftPiece = piecesOfThePieceGroup[0]
        val leftPiecePosition = adapter.getPositionOfPiece(leftPiece)
        val leftPiecePositionIsInFirstColumn = pieceIsInFirstColumn(leftPiecePosition)
        if (leftPiecePositionIsInFirstColumn) {
            return null
        }

        return if (actualState[leftPiecePosition - 1].type == PieceType.EMPTY) {
            Direction.LEFT
        } else {
            null
        }
    }

    private fun canMoveDownVertically(
        actualState: List<GamePiece>,
        piecesOfThePieceGroup: List<GamePiece>
    ): Direction? {
        val lowerPiece = piecesOfThePieceGroup[1]
        val lowerPiecePosition = adapter.getPositionOfPiece(lowerPiece)
        val lowerPiecePositionIsInLastRow = pieceIsInLastRow(lowerPiecePosition)
        if (lowerPiecePositionIsInLastRow) {
            return null
        }

        return if (actualState[lowerPiecePosition + 4].type == PieceType.EMPTY) {
            Direction.DOWN
        } else {
            null
        }
    }
    private fun canMoveDownRed(
        actualState: List<GamePiece>,
        piecesOfThePieceGroup: List<GamePiece>
    ): Direction? {
        val lowerPiece = piecesOfThePieceGroup[2]
        val lowerPiece2 = piecesOfThePieceGroup[3]
        val lowerPiecePosition = adapter.getPositionOfPiece(lowerPiece)
        val lowerPiecePosition2 = adapter.getPositionOfPiece(lowerPiece2)
        val lowerPiecePositionIsInLastRow = pieceIsInLastRow(lowerPiecePosition)
        if (lowerPiecePositionIsInLastRow) {
            return null
        }
        return if (actualState[lowerPiecePosition + 4].type == PieceType.EMPTY && actualState[lowerPiecePosition2 + 4].type == PieceType.EMPTY ) {
            Direction.DOWN
        } else {
            null
        }
    }
    private fun pieceIsInLastRow(piecePosition: Int): Boolean {
        return piecePosition > 15
    }

    private fun canMoveUpVertically(
        actualState: List<GamePiece>,
        piecesOfThePieceGroup: List<GamePiece>
    ): Direction? {
        val upperPiece = piecesOfThePieceGroup[0]
        val upperPiecePosition = adapter.getPositionOfPiece(upperPiece)
        val upperPiecePositionIsInFirstRow = pieceIsInFirstRow(upperPiecePosition)
        if (upperPiecePositionIsInFirstRow) {
            return null
        }

        return if (actualState[upperPiecePosition - 4].type == PieceType.EMPTY) {
            Direction.UP
        } else {
            null
        }
    }
    private fun canMoveUpRed(
        actualState: List<GamePiece>,
        piecesOfThePieceGroup: List<GamePiece>
    ): Direction? {
        val upperPiece = piecesOfThePieceGroup[0]
        val upperPiece2 = piecesOfThePieceGroup[1]
        val upperPiecePosition = adapter.getPositionOfPiece(upperPiece)
        val upperPiecePosition2 = adapter.getPositionOfPiece(upperPiece2)
        val upperPiecePositionIsInFirstRow = pieceIsInFirstRow(upperPiecePosition)
        if (upperPiecePositionIsInFirstRow) {
            return null
        }
        return if (actualState[upperPiecePosition - 4].type == PieceType.EMPTY && actualState[upperPiecePosition2 - 4].type == PieceType.EMPTY) {
            Direction.UP
        } else {
            null
        }
    }
    private fun pieceIsInFirstRow(piecePosition: Int): Boolean {
        return piecePosition < 4
    }


    private fun whereToMoveYellow(positionClicked: Int, actualState: List<GamePiece>): Int? {
        // Piece size is 1, so we have to check if any of the surrounding pieces is empty
        return getAnySurroundingPieceEmpty(positionClicked, actualState)
    }

    private fun getAnySurroundingPieceEmpty(positionClicked: Int, actualState: List<GamePiece>): Int? {
        val surroundingPiecesInsideBoard = getSurroundingPositionsInsideBoard(positionClicked)

        // Return the first piece position that is empty
        val gamePiece = surroundingPiecesInsideBoard.map { actualState[it] }.firstOrNull { it.type == PieceType.EMPTY }
        return gamePiece?.let { adapter.getPositionOfPiece(it) }
    }

    private fun pieceIsInLastColumn(positionClicked: Int): Boolean {
        return positionClicked % 4 == 3
    }

    private fun pieceIsInFirstColumn(positionClicked: Int): Boolean {
        return positionClicked % 4 == 0
    }

    private fun getSurroundingPositionsInsideBoard(positionClicked: Int): List<Int> {
        val row = positionClicked / 4
        val column = positionClicked % 4
        val surroundingPieces = mutableListOf(
            (row - 1) * 4 + column,
            (row + 1) * 4 + column,
            row * 4 + column - 1,
            row * 4 + column + 1
        )

        if (pieceIsInFirstColumn(positionClicked) || pieceIsInLastColumn(positionClicked)) {
            if (pieceIsInFirstColumn(positionClicked)) {
                // Remove the left piece
                surroundingPieces.remove(positionClicked - 1)
            } else {
                // Remove the right piece
                surroundingPieces.remove(positionClicked + 1)
            }
        }

        // Check if the surrounding pieces are inside the board
        return surroundingPieces.filter { it in 0..19 }
    }

    fun whereToMove2Cells(positionClicked: Int, actualState: MutableList<GamePiece>): Any? {
        val pieceClicked = actualState[positionClicked]
        return when (pieceClicked.type) {
            PieceType.YELLOW -> {
                whereToMove2CellsYellow(positionClicked, actualState)
            }

            PieceType.BLUE -> {
                whereToMove2CellsBlue(positionClicked, actualState)
            }

            else -> {
                null
            }
        }
    }

    private fun whereToMove2CellsBlue(
        positionClicked: Int,
        actualState: MutableList<GamePiece>
    ): Direction? {

        val pieceGroup = adapter.getGroup(actualState[positionClicked].groupId)
        val piece1Position = adapter.getPositionOfPiece(pieceGroup.pieces[0]) // The upper/leftest one
        val piece2Position = adapter.getPositionOfPiece(pieceGroup.pieces[1]) // The lower/rightest one

        val firstMovementDirection = whereToMoveBlue(positionClicked, actualState) ?: return null
        val firstAfterMovementPiece1Position = getPositionAfterDirectionMovement(piece1Position, firstMovementDirection)
        val firstAfterMovementPiece2Position = getPositionAfterDirectionMovement(piece2Position, firstMovementDirection)

        when (firstMovementDirection) {
            Direction.UP -> {
                return if (pieceIsInFirstRow(firstAfterMovementPiece1Position)) {
                    null
                } else {
                    val positionAfterSecondMovement = getPositionAfterDirectionMovement(firstAfterMovementPiece1Position, Direction.UP)
                    if (actualState[positionAfterSecondMovement].type == PieceType.EMPTY) {
                        Direction.UP
                    } else {
                        null
                    }
                }
            }
            Direction.DOWN -> {
                return if (pieceIsInLastRow(firstAfterMovementPiece2Position)) {
                    null
                } else {
                    val positionAfterSecondMovement = getPositionAfterDirectionMovement(firstAfterMovementPiece2Position, Direction.DOWN)
                    if (actualState[positionAfterSecondMovement].type == PieceType.EMPTY) {
                        Direction.DOWN
                    } else {
                        null
                    }
                }
            }
            Direction.LEFT -> {
                return if (pieceIsInFirstColumn(firstAfterMovementPiece1Position)) {
                    null
                } else {
                    val positionAfterSecondMovement = getPositionAfterDirectionMovement(firstAfterMovementPiece1Position, Direction.LEFT)
                    if (actualState[positionAfterSecondMovement].type == PieceType.EMPTY) {
                        Direction.LEFT
                    } else {
                        null
                    }
                }
            }
            Direction.RIGHT -> {
                return if (pieceIsInLastColumn(firstAfterMovementPiece2Position)) {
                    null
                } else {
                    val positionAfterSecondMovement = getPositionAfterDirectionMovement(firstAfterMovementPiece2Position, Direction.RIGHT)
                    if (actualState[positionAfterSecondMovement].type == PieceType.EMPTY) {
                        Direction.RIGHT
                    } else {
                        null
                    }
                }
            }
        }
    }

    private fun getPositionAfterDirectionMovement(
        positionClicked: Int,
        movementDirection: Direction
    ): Int {
        when (movementDirection) {
            Direction.UP -> {
                return positionClicked - 4
            }

            Direction.DOWN -> {
                return positionClicked + 4
            }

            Direction.LEFT -> {
                return positionClicked - 1
            }

            Direction.RIGHT -> {
                return positionClicked + 1
            }
        }
    }

    private fun whereToMove2CellsYellow(
        positionClicked: Int,
        actualState: MutableList<GamePiece>
    ): Int? {
        return getAnySurroundingPieceEmpty(getAnySurroundingPieceEmpty(positionClicked, actualState)!!, actualState)
    }

    fun move2Cells(
        positionClicked: Int,
        whereToMove: Any,
        actualState: MutableList<GamePiece>
    ) {
        val pieceClicked = actualState[positionClicked]
        when (pieceClicked.type) {
            PieceType.YELLOW -> {
                moveYellowPiece(pieceClicked, whereToMove as Int)
            }

            PieceType.BLUE -> {
                moveBluePiece2Cells(pieceClicked, whereToMove as Direction)
            }

            else -> {}
        }
    }

    private fun moveBluePiece2Cells(pieceClicked: GamePiece, directionToMove: Direction) {
        moveBluePiece(pieceClicked, directionToMove)
        moveBluePiece(pieceClicked, directionToMove)
    }

    fun checkWin(actualState: MutableList<GamePiece>) {
        if (WINNING_POSITIONS.all { actualState[it].type == PieceType.RED }) {
            gameWon()
        }
    }

    private fun gameWon() {
        val timer = adapter.getGameTimer()
        logger.setResult(true); logger.setTime(timer.cancelAndGetTimeLeft()); logger.addWonLevel(adapter.getLevelNumber())
        GAME_STATE = GameState.Type.WIN
        val dialogFragment = PopUpFragment()
        dialogFragment.show((context as AppCompatActivity).supportFragmentManager, "My  Fragment")
    }

}