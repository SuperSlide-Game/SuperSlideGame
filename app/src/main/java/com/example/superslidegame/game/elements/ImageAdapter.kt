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
        GamePiece(PieceType.BLUE, Orientation.HORIZONTAL, 1,2),
        GamePiece(PieceType.YELLOW),
        GamePiece(PieceType.RED, Orientation.HORIZONTAL, 3,1),
        GamePiece(PieceType.RED, Orientation.HORIZONTAL, 3,2),
        GamePiece(PieceType.BLUE, Orientation.VERTICAL, 2,3),
        GamePiece(PieceType.YELLOW),
        GamePiece(PieceType.RED, Orientation.HORIZONTAL, 3,3),
        GamePiece(PieceType.RED, Orientation.HORIZONTAL, 3,4),
        GamePiece(PieceType.BLUE, Orientation.VERTICAL, 2,1),
        GamePiece(PieceType.YELLOW),
    )

    // Definition of the cell groups
    private val blueGroup1 = PieceGroup(1, listOf(
        pieces[9],
        pieces[10],
    ))
    private val blueGroup2 = PieceGroup(2, listOf(
        pieces[14],
        pieces[18],
    ))

    private val redGroup = PieceGroup(3, listOf(
        pieces[12],
        pieces[13],
        pieces[17],
        pieces[17],
    ))


    private val groups = arrayOf(
        blueGroup1,
        blueGroup2,
        redGroup,
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
}