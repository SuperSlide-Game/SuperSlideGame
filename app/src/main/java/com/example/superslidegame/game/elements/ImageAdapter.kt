package com.example.superslidegame.game.elements

import android.content.Context
import android.widget.BaseAdapter
import android.widget.ImageButton
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.example.superslidegame.R

class ImageAdapter(private val context: Context) : BaseAdapter() {
    //Mutejada per testejar amb la classe CellGroup
    /*fun getPiecesState() : Array<GamePiece> {
        return pieces
    }*/
    // Board of 5x4
    private val pieces = arrayOf(
        GamePiece(PieceType.YELLOW),
        GamePiece(PieceType.YELLOW),
        GamePiece(PieceType.YELLOW),
        GamePiece(PieceType.EMPTY),
        GamePiece(PieceType.YELLOW),
        GamePiece(PieceType.YELLOW),
        GamePiece(PieceType.YELLOW),
        GamePiece(PieceType.YELLOW),
        GamePiece(PieceType.EMPTY),
        GamePiece(PieceType.BLUE, Orientation.HORIZONTAL, 1,1),
        GamePiece(PieceType.BLUE, Orientation.HORIZONTAL, 2,1),
        GamePiece(PieceType.YELLOW),
        GamePiece(PieceType.BLUE, Orientation.HORIZONTAL, 3,2),
        GamePiece(PieceType.YELLOW),
        GamePiece(PieceType.BLUE, Orientation.HORIZONTAL, 3,2),
        GamePiece(PieceType.YELLOW),
    )
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
        imageButton.setOnClickListener(ClickListener(context, position, pieces))
        return imageButton
    }
}