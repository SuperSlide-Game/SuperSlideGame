package com.example.superslidegame.game.elements

import android.content.Context
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.widget.Toast

class PiecesListener(private val position : Int, private val actualState : Array<gamePiece> , private val context : Context) : OnTouchListener{
    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        v?.performClick()
        if (event != null) {
            return when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    Toast.makeText(context, "Down$position", Toast.LENGTH_SHORT).show()
                    true
                }
                MotionEvent.ACTION_MOVE -> {
                    Toast.makeText(context, "Move$position", Toast.LENGTH_SHORT).show()
                    true
                }
                MotionEvent.ACTION_UP -> {
                    Toast.makeText(context, "Up$position", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }
        return false
    }

}