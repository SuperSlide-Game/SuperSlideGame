package com.example.superslidegame.game.elements

import android.content.Context
import android.widget.BaseAdapter
import android.widget.ImageButton
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.example.superslidegame.R
import com.example.superslidegame.game.levels.GameLevel

class ImageAdapter(private val context: Context) : BaseAdapter() {

    private val level = GameLevel()

    private val pieces = level.getPieces()

    private val groups = level.getGroups()

    override fun getCount(): Int {
        return pieces.size
    }

    override fun getItem(position: Int): Any {
        return pieces[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: android.view.View?, parent: android.view.ViewGroup?): android.view.View {
        val imageButton : ImageButton
        if (convertView == null) {
            imageButton = ImageButton(context)
            imageButton.setImageResource(pieces[position].imgSrc)
            imageButton.setBackgroundColor(ContextCompat.getColor(context, R.color.transparent))
            imageButton.scaleType = ImageView.ScaleType.FIT_CENTER
            imageButton.adjustViewBounds = true
            imageButton.setPadding(0, 0, 0, 0)
        } else {
            imageButton = convertView as ImageButton
        }
        imageButton.setOnClickListener(ClickListener(context, position, this))
        return imageButton
    }

    fun getPiecesState() : Array<GamePiece> {
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
    /*fun movePiece(piece : GamePiece) {
        val pos = getPositionOfPiece(piece)
        pieces[pos] = GamePiece(PieceType.EMPTY, 0)

        //Agafar la posicio on es pot moure
        //moure la peça
    }*/
}