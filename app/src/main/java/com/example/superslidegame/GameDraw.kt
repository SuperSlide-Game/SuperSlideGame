package com.example.superslidegame

import android.content.Context
import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat

class GameDraw(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    var fillPaint = Paint()
    var strokePaint = Paint()

    val rows = 5
    val cols = 4

    // Get the screen width and height
    private val screenWidth = Resources.getSystem().displayMetrics.widthPixels
    private val screenHeight = Resources.getSystem().displayMetrics.heightPixels

    // Calculate the cell size based on the screen size
    private val cellSize = screenWidth / cols - screenWidth / 20

    // Calculate the origin of the grid based on the screen size
    private val originX = screenWidth / 2 - (cellSize * cols) / 2f
    private val originY = screenHeight / 2 - (cellSize * rows) / 2f - screenHeight / 10
    private fun initPaints() {

        // fill
        fillPaint.style = Paint.Style.FILL
        fillPaint.color = (ContextCompat.getColor(context, R.color.light_white))

        // stroke
        strokePaint.style = Paint.Style.STROKE
        strokePaint.color = Color.GRAY
        strokePaint.strokeWidth = 10f
    }
    override fun onDraw(canvas: Canvas) {
        var r: RectF
        // First rectangle
        for(j in 0 until rows){
            for(i in 0 until cols){
                initPaints()
                val cornerRadius = 20
                r = RectF(originX + (i) * cellSize, originY + j * cellSize, originX + (i + 1) * cellSize, originY + (j +1) * cellSize)
                canvas.drawRoundRect(r, cornerRadius.toFloat(), cornerRadius.toFloat(), fillPaint)    // fill
                canvas.drawRoundRect(
                    r,
                    cornerRadius.toFloat(),
                    cornerRadius.toFloat(),
                    strokePaint
                ) // stroke
            }
        }
    }
}