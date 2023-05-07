package com.example.superslidegame.game.elements

import android.content.Context
import android.view.View
import android.view.View.OnClickListener
import com.example.superslidegame.game.GameLogic

/**
 * Listener for the 1-cell movement
 * @param context Context of the application
 * @param positionClicked Position of the piece clicked
 * @param adapter Adapter of the board
 */
class ClickListener(context: Context, private val positionClicked: Int, private val adapter: ImageAdapter) : OnClickListener {

    private val gameLogic : GameLogic = GameLogic(context, adapter)
    override fun onClick(view: View?) {

        val pieceClicked = adapter.getPiecesState()[positionClicked]
        val actualState = adapter.getPiecesState()

        when (pieceClicked.type) {
            PieceType.EMPTY -> {
                // Empty position, do nothing
            }
            else -> {
                val positionToMove = gameLogic.whereToMove(positionClicked, actualState)
                if (positionToMove != null && GameLogic.GAME_STATE == GameState.Type.IN_PROGRESS) {
                    gameLogic.move(positionClicked, positionToMove, actualState)
                    adapter.updateBoard()
                    gameLogic.checkWin(actualState)
                } else {
                    // Do nothing
                }
            }
        }
    }
}