package com.example.superslidegame.game

import android.content.Context
import android.widget.Toast
import com.example.superslidegame.game.elements.Direction
import com.example.superslidegame.game.elements.GamePiece
import com.example.superslidegame.game.elements.ImageAdapter
import com.example.superslidegame.game.elements.Orientation
import com.example.superslidegame.game.elements.PieceType


class GameLogic(private val context: Context, private val adapter: ImageAdapter) {

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
        TODO("Not yet implemented")
    }

    private fun moveBluePiece(piece: GamePiece, moveTo: Int) {
        val groups = adapter.getGroup(piece.groupId)
        val BigPiece = groups.pieces
        if(groups.orientation == Orientation.HORIZONTAL){
            val posP1 = adapter.getPositionOfPiece(BigPiece[0])
            val posP2 = adapter.getPositionOfPiece(BigPiece[1])
            val bigN = posP1.coerceAtLeast(posP2)
            val minN = posP1.coerceAtMost(posP2)
            if(posP1 == adapter.getPositionOfPiece(piece)){
                if(moveTo < posP1 && moveTo < posP2){
                    if(minN == posP1){
                        adapter.swapPositions(adapter.getPositionOfPiece(piece), moveTo)
                        adapter.swapPositions(adapter.getPositionOfPiece(BigPiece[1]), moveTo+1)
                    }else{
                        adapter.swapPositions(adapter.getPositionOfPiece(BigPiece[1]), moveTo)
                        adapter.swapPositions(adapter.getPositionOfPiece(piece), moveTo+1)
                    }
                }else{
                    if(bigN == posP1){
                        adapter.swapPositions(adapter.getPositionOfPiece(piece), moveTo)
                        adapter.swapPositions(adapter.getPositionOfPiece(BigPiece[1]), moveTo-1)
                    }else{
                        adapter.swapPositions(adapter.getPositionOfPiece(BigPiece[1]), moveTo)
                        adapter.swapPositions(adapter.getPositionOfPiece(piece), moveTo-1)
                    }
                }
            }else{
                if(moveTo < posP1 && moveTo < posP2){
                    if(minN == posP1){
                        adapter.swapPositions(adapter.getPositionOfPiece(piece), moveTo)
                        adapter.swapPositions(adapter.getPositionOfPiece(BigPiece[0]), moveTo+1)
                    }else{
                        adapter.swapPositions(adapter.getPositionOfPiece(BigPiece[0]), moveTo)
                        adapter.swapPositions(adapter.getPositionOfPiece(piece), moveTo+1)
                    }
                }else{
                    if(bigN == posP1){
                        adapter.swapPositions(adapter.getPositionOfPiece(piece), moveTo)
                        adapter.swapPositions(adapter.getPositionOfPiece(BigPiece[0]), moveTo-1)
                    }else{
                        adapter.swapPositions(adapter.getPositionOfPiece(BigPiece[0]), moveTo)
                        adapter.swapPositions(adapter.getPositionOfPiece(piece), moveTo-1)
                    }
                }
            }
        }else{
            val posP1 = adapter.getPositionOfPiece(BigPiece[0])
            val posP2 = adapter.getPositionOfPiece(BigPiece[1])
            val bigN = posP1.coerceAtLeast(posP2)
            val minN = posP1.coerceAtMost(posP2)
            if(posP1 == adapter.getPositionOfPiece(piece)){
                if(moveTo < posP1 && moveTo < posP2){
                    adapter.swapPositions(adapter.getPositionOfPiece(piece), moveTo)
                    adapter.swapPositions(adapter.getPositionOfPiece(BigPiece[1]), moveTo+4)
                }else{
                    adapter.swapPositions(adapter.getPositionOfPiece(BigPiece[1]), moveTo)
                    adapter.swapPositions(adapter.getPositionOfPiece(piece), moveTo-4)
                }
            }else{
                if(moveTo < posP1 && moveTo < posP2){
                    adapter.swapPositions(adapter.getPositionOfPiece(piece), moveTo)
                    adapter.swapPositions(adapter.getPositionOfPiece(BigPiece[1]), moveTo+4)
                }else{
                    adapter.swapPositions(adapter.getPositionOfPiece(BigPiece[1]), moveTo)
                    adapter.swapPositions(adapter.getPositionOfPiece(piece), moveTo-4)
                }
            }
        }

    }

    private fun moveBluePiece(piece: GamePiece, moveTo: Direction) {
        val pieceGroup = adapter.getGroup(piece.groupId)
        val orientationOfThePieceGroup = pieceGroup.orientation

        when (orientationOfThePieceGroup) {
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
                        adapter.swapPositions(leftPiecePosition, leftPiecePosition + 1)
                        adapter.swapPositions(rightPiecePosition, rightPiecePosition + 1)
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
                        adapter.swapPositions(upperPiecePosition, upperPiecePosition + 4)
                        adapter.swapPositions(lowerPiecePosition, lowerPiecePosition + 4)
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

    private fun whereToMoveRed(positionClicked: Int, actualState: List<GamePiece>): Int? {
        TODO("Not yet implemented")
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

    private fun pieceIsInLastRow(piecePosition: Int): Boolean {
        return piecePosition > 11
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

    private fun filterGoodPieces(pieces : List<Int>, posClicked : Int) : List<Int>{
        val existingRanges = listOf<Array<Int>>(arrayOf(0,3), arrayOf(4,7), arrayOf(8,11), arrayOf(12,15), arrayOf(16,19))
        var gRange = arrayOf(0,0)
        for(ranges in existingRanges){
            if(posClicked >= ranges[0] && posClicked <= ranges[1] ){
                gRange = ranges
            }
        }
        val result = ArrayList<Int>()
        for(piece in pieces){
            if(piece <= gRange[1] && piece >= gRange[0]){
                result.add(piece)
            }
        }
        return result
    }

    private fun getAnySurroundingPieceEmpty(positionClicked: Int, actualState: List<GamePiece>, pieceOrientation: Orientation?): Int? {
        // Checks if the piece of the direction we want to check is empty

        val surroundingPiecesInsideBoard = getSurroundingPositionsInsideBoard(positionClicked)

        val emptySurroundingPieces = surroundingPiecesInsideBoard.filter { actualState[it].type == PieceType.EMPTY }

        Toast.makeText(context, "Empty surrounding pieces: ${filterGoodPieces(emptySurroundingPieces, positionClicked)} of the piece $positionClicked", Toast.LENGTH_SHORT).show()

        return when (pieceOrientation) {
            Orientation.HORIZONTAL -> {
                val gamePiece = filterGoodPieces(emptySurroundingPieces, positionClicked).firstOrNull { it == positionClicked - 1 || it == positionClicked + 1 }
                gamePiece?.let { adapter.getPositionOfPiece(actualState[it]) }
            }

            Orientation.VERTICAL -> {
                val gamePiece = filterGoodPieces(emptySurroundingPieces, positionClicked).firstOrNull { it == positionClicked - 1 || it == positionClicked + 1 }
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

}