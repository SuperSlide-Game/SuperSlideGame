package com.example.superslidegame.game

import android.content.Context
import android.widget.Toast
import com.example.superslidegame.game.elements.GamePiece
import com.example.superslidegame.game.elements.ImageAdapter
import com.example.superslidegame.game.elements.Orientation
import com.example.superslidegame.game.elements.PieceType
import kotlin.math.max


class GameLogic(private val context: Context, private val adapter: ImageAdapter) {

    fun whereToMove(positionClicked: Int, actualState: List<GamePiece>): Int? {
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

    fun move(positionClicked: Int, positionToMove: Int, actualState: List<GamePiece>) {
        when (actualState[positionClicked].type) {
            PieceType.YELLOW -> {
                moveYellowPiece(actualState[positionClicked], positionToMove)
            }

            PieceType.BLUE -> {
                moveBluePiece(actualState[positionClicked], positionToMove)
            }

            PieceType.RED -> {
                moveRedPiece(actualState[positionClicked], positionToMove)
            }

            else -> {}
        }
    }

    private fun moveRedPiece(piece: GamePiece, moveTo: Int) {
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

    private fun whereToMoveBlue(positionClicked: Int, actualState: List<GamePiece>): Int? {
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

    private fun whereToMoveYellow(positionClicked: Int, actualState: List<GamePiece>): Int? {
        // Piece size is 1, so we have to check if any of the surrounding pieces is empty
        return getAnySurroundingPieceEmpty(positionClicked, actualState)
    }

    private fun getAnySurroundingPieceEmpty(positionClicked: Int, actualState: List<GamePiece>): Int? {
        val surroundingPiecesInsideBoard = getSurroundingPositionsInsideBoard(positionClicked)

        // Return the first piece that is empty
        val gamePiece = surroundingPiecesInsideBoard.map { actualState[it] }.firstOrNull { it.type == PieceType.EMPTY }
        return gamePiece?.let { adapter.getPositionOfPiece(it) }
    }

    private fun getAnySurroundingPieceEmpty(positionClicked: Int, actualState: List<GamePiece>, pieceOrientation: Orientation?): Int? {
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