package com.example.superslidegame.game.elements

import com.example.superslidegame.R

enum class PieceType(val imgSrc: Int, val size: Int) {
    YELLOW(R.drawable.yellow_piece, 1),
    BLUE(R.drawable.blue_piece, 2),
    RED(R.drawable.red_piece, 4),
    EMPTY(R.drawable.empty_piece, 0);
}