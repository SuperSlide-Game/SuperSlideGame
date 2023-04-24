package com.example.superslidegame.game.elements

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageButton
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.example.superslidegame.R
import com.example.superslidegame.game.animations.AnimationHelper
import com.example.superslidegame.game.levels.Level

class ImageAdapter(private val screenActivity: Activity, private val level: Level, val animationHelper: AnimationHelper) : BaseAdapter() {

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
            // Listener for the 1-cell movement
            imageButton.setOnClickListener(ClickListener(screenActivity, position, this))
            // Listener for the 2-cell movement
            imageButton.setOnLongClickListener(LongClickListener(context, position, this))
        } else {
            imageButton = convertView as ImageButton
        }

        imageButton.setImageResource(pieces[position].imgSrc)
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
}