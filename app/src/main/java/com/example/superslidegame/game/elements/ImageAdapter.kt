package com.example.superslidegame.game.elements

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageButton
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.example.superslidegame.R
import com.example.superslidegame.game.levels.Level
import com.example.superslidegame.game.screen.GameScreen

class ImageAdapter(private val screenActivity: GameScreen, val level: Level) : BaseAdapter() {

    private val pieces: MutableList<GamePiece> = level.getPieces()

    private val groups: MutableList<PieceGroup> = level.getGroups()

    private val context : Context = screenActivity.baseContext
    override fun getCount(): Int {
        return pieces.size
    }

    override fun getItem(position: Int): Any? {
        return null
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val imageButton : ImageButton
        if (convertView == null) {
            imageButton = ImageButton(context)
            imageButton.setBackgroundColor(ContextCompat.getColor(context, R.color.transparent))
            imageButton.scaleType = ImageView.ScaleType.FIT_CENTER
            imageButton.adjustViewBounds = true
            imageButton.setPadding(0, 0, 0, 0)
            // If the device is in landscape mode, the width of the buttons is reduced in size
            if (screenActivity.resources.configuration.orientation == 2) {
                imageButton.layoutParams = ViewGroup.LayoutParams(150, 150)
                imageButton.adjustViewBounds = false
            }

        } else {
            imageButton = convertView as ImageButton
        }

        // Listener for the 1-cell movement
        imageButton.setOnClickListener(ClickListener(screenActivity, position, this))
        // Listener for the 2-cell movement
        imageButton.setOnLongClickListener(LongClickListener(context, position, this))

        imageButton.setImageResource(pieces[position].imgSrc)

        imageButton.rotation = pieces[position].rotation
        if(pieces[position].type == PieceType.EMPTY){
            if(position == 13 || position == 14 || position == 17 || position ==18){
                imageButton.setImageResource(R.drawable.empty_piece_yes)
            }
        }
        return imageButton
    }

    fun getPiecesState() : MutableList<GamePiece> {
        return pieces
    }

    fun getPositionOfPiece(piece: GamePiece) : Int {
        for (i in pieces.indices) {
            if (pieces[i] == piece) {
                return i
            }
        }
        throw Exception("Piece not found")
    }

    fun getGroup(groupId: Int) : PieceGroup {
        for (group in groups) {
            if (group.id == groupId) {
                return group
            }
        }
        throw Exception("Group not found")
    }

    fun swapPositions(fromPosition: Int, toPosition: Int) {
        // Swap the positions of the pieces list
        val temp = pieces[fromPosition]
        pieces[fromPosition] = pieces[toPosition]
        pieces[toPosition] = temp
    }
    fun updateBoard() {
        screenActivity.runOnUiThread {
            notifyDataSetChanged()
        }
    }

    fun getGameTimer() : StoppableCountDownTimer {
        return screenActivity.getGameTimer()
    }

    fun getLevelNumber() : Int {
        return screenActivity.getPlayingLevel()
    }

    fun updateMoves(moves: Int) {
        screenActivity.updateMoves(moves)
    }

    fun onGameFinished() {
        val seconds = screenActivity.getGameTimer().cancelAndGetTimeLeft()
        screenActivity.onGameFinished(seconds)
    }

    fun isExtremeModeGame(): Boolean {
        return screenActivity.isExtremeModeGame()
    }
}