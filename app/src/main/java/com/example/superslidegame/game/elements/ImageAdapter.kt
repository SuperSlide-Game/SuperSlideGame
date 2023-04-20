package com.example.superslidegame.game.elements

import android.content.Context
import android.media.Image
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.ImageButton
import com.example.superslidegame.R
import com.example.superslidegame.game.elements.gamePiece

class ImageAdapter(private val context: Context) : BaseAdapter() {

    private val BOARD_SIZE = 20
    fun getPiecesState() : Array<gamePiece> {
        return pieces
    }
    private val pieces = arrayOf(
        gamePiece(R.drawable.play_button, PiecesSize.YELLOW),
        gamePiece(R.drawable.play_button, PiecesSize.YELLOW),
        gamePiece(R.drawable.play_button, PiecesSize.YELLOW),
        gamePiece(R.drawable.play_button, PiecesSize.YELLOW),
        gamePiece(R.drawable.play_button, PiecesSize.YELLOW),
        gamePiece(R.drawable.play_button, PiecesSize.YELLOW),
        gamePiece(R.drawable.play_button, PiecesSize.YELLOW),
        gamePiece(R.drawable.play_button, PiecesSize.RED),
        gamePiece(R.drawable.play_button, PiecesSize.EMPTY),
        gamePiece(R.drawable.play_button, PiecesSize.EMPTY),
        gamePiece(R.drawable.play_button, PiecesSize.EMPTY),
        gamePiece(R.drawable.play_button, PiecesSize.EMPTY),
        gamePiece(R.drawable.play_button, PiecesSize.BLUE),
        gamePiece(R.drawable.play_button, PiecesSize.BLUE),
        gamePiece(R.drawable.play_button, PiecesSize.BLUE),
        gamePiece(R.drawable.play_button, PiecesSize.RED),
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
        if(convertView == null){
            imageButton = ImageButton(context)
            imageButton.setImageResource(pieces[position].getImgSource())
            imageButton.scaleX = 0.3F
            imageButton.scaleY = 0.1F
            imageButton.setPadding(2,2,2,2)

        }else{
            imageButton = convertView as ImageButton
        }

        imageButton.setOnTouchListener(PiecesListener(position,pieces, context))
        return imageButton
    }
}