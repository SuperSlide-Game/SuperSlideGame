package com.example.superslidegame.game.elements

import android.content.Context
import android.view.View
import android.widget.Toast
import com.example.superslidegame.game.GameLogic

class LongClickListener(private val context: Context, private val positionClicked: Int, private val adapter: ImageAdapter) :
    View.OnLongClickListener {

    private val gameLogic : GameLogic = GameLogic(context, adapter)

    override fun onLongClick(view: View?): Boolean {

        val animationHelper = adapter.animationHelper
        val pieceClicked = adapter.getPiecesState()[positionClicked]
        val actualState = adapter.getPiecesState()

        when (pieceClicked.type) {
            PieceType.EMPTY -> {
                Toast.makeText(context, "You cannot perform any action in an empty position", Toast.LENGTH_SHORT).show()
            }
            PieceType.RED -> {
                Toast.makeText(context, "You cannot move a red piece 2 cells", Toast.LENGTH_SHORT).show()
            } else -> {
                val whereToMove = gameLogic.whereToMove2Cells(positionClicked, actualState)
                if (whereToMove != null && GameLogic.GAME_STATE == GameState.Type.IN_PROGRESS) {
                    Toast.makeText(context,
                        "You can move this piece to position: $whereToMove", Toast.LENGTH_SHORT).show()
                    animationHelper.playMoveSound()
                    gameLogic.move2Cells(positionClicked, whereToMove, actualState)
                    adapter.updateBoard()
                    gameLogic.checkWin(actualState)
                } else {
                    Toast.makeText(context, "You cannot move this piece", Toast.LENGTH_SHORT).show()
                }
            }
        }
        return true
    }

}
