package com.example.superslidegame.game.elements

import android.content.Context
import android.view.View
import android.view.View.OnClickListener
import android.widget.Toast
import com.example.superslidegame.game.GameLogic

class ClickListener(private val context: Context, private val positionClicked: Int, private val adapter: ImageAdapter) : OnClickListener {

    private val gameLogic : GameLogic = GameLogic(context, adapter)
    override fun onClick(view: View?) {

        val pieceClicked = adapter.getPiecesState()[positionClicked]
        val actualState = adapter.getPiecesState()

        when (pieceClicked.type) {
            PieceType.EMPTY -> {
                Toast.makeText(context, "You cannot perform any action in an empty position", Toast.LENGTH_SHORT).show()
            }
            else -> {
                val positionToMove = gameLogic.whereToMove(positionClicked, actualState)
                if (positionToMove != null) {
                    Toast.makeText(context,
                        "You can move this piece to position: $positionToMove", Toast.LENGTH_SHORT).show()
                    gameLogic.move(positionClicked, positionToMove, actualState)
                    adapter.updateBoard()
                    gameLogic.checkWin(actualState)
                } else {
                    Toast.makeText(context, "You cannot move this piece", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}