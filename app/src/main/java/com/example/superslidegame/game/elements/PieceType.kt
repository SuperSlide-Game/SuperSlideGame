package com.example.superslidegame.game.elements

import com.example.superslidegame.R

/**
 * Enum class for the different types of pieces
 * @param imgSrc the image source of the piece
 */
enum class PieceType(val imgSrc: Int) {
    YELLOW(R.drawable.yellow_piece),
    BLUE(R.drawable.blue_piece),
    RED(R.drawable.red_piece),
    EMPTY(R.drawable.empty_piece_no);
}