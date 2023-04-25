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

class ImageAdapter(private val screenActivity: Activity, level: Level, val animationHelper: AnimationHelper) : BaseAdapter() {

    private val pieces: MutableList<GamePiece> = level.getPieces()

    private val groups: MutableList<PieceGroup> = level.getGroups()

    private val context : Context = screenActivity.baseContext
    private var bN = 0
    private var bNv = 0
    private var rN = 0
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
        } else {
            imageButton = convertView as ImageButton
        }

        // Listener for the 1-cell movement
        imageButton.setOnClickListener(ClickListener(screenActivity, position, this))
        // Listener for the 2-cell movement
        imageButton.setOnLongClickListener(LongClickListener(context, position, this))

        imageButton.setImageResource(pieces[position].imgSrc)
        if(pieces[position].type == PieceType.BLUE){
            if(getGroup(pieces[position].groupId).orientation == Orientation.VERTICAL){
                imageButton.rotation = 90.0F
                if(bNv == 1){
                    imageButton.rotation = 270.0F
                    bNv = 0
                }else{
                    bNv+=1
                }
            }else{
                if(bN == 1){
                    imageButton.setImageResource(R.drawable.blue_piece)
                    imageButton.rotation = 180.0F
                    bN = 0
                }else{
                    bN+=1
                }
            }
        }
        if(pieces[position].type == PieceType.RED){
            if(rN == 1){
                imageButton.rotation = 90F
            }
            if(rN == 2){
                imageButton.rotation = 270F
            }
            if(rN == 3){
                imageButton.rotation = 180F
            }
            rN +=1
        }
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
}