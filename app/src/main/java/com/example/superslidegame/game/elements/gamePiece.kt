package com.example.superslidegame.game.elements

import androidx.appcompat.app.AppCompatActivity

class gamePiece (private val imgSource: Int, private val size: PiecesSize) {
    fun getImgSource() : Int{
        return imgSource
    }
    fun getSize() : PiecesSize{
        return size
    }
}