package com.example.superslidegame.game.elements

import android.content.Context
import android.view.View
import com.example.superslidegame.game.GameLogic

/**
 * Listener for the 2-cell movement
 * @param context Context of the application
 * @param positionClicked Position of the piece clicked
 * @param adapter Adapter of the board
 */
class LongClickListener(context: Context, private val positionClicked: Int, private val adapter: ImageAdapter) :
    View.OnLongClickListener {

    private val gameLogic : GameLogic = GameLogic(context, adapter)

    override fun onLongClick(view: View?): Boolean {

        val pieceClicked = adapter.getPiecesState()[positionClicked]
        val actualState = adapter.getPiecesState()

        when (pieceClicked.type) {
            PieceType.EMPTY -> {
                // Empty position cannot be moved
            }
            PieceType.RED -> {
                // Red piece cannot be moved 2 cells
            } else -> {
                val whereToMove = gameLogic.whereToMove2Cells(positionClicked, actualState)
                if (whereToMove != null && GameLogic.GAME_STATE == GameState.Type.IN_PROGRESS) {
                    gameLogic.move2Cells(positionClicked, whereToMove, actualState)
                    adapter.updateBoard()
                    adapter.updateScreenLog(positionClicked, pieceClicked, whereToMove)
                    gameLogic.checkWin(actualState)
                } else {
                    // Do nothing
                }
            }
        }
        return true
    }

}
