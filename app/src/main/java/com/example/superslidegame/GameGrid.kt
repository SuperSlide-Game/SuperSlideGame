package com.example.superslidegame

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View


class GameGrid(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    var fillPaint = Paint()
    var strokePaint = Paint()
    private final val originX = 130f
    private final val originY = 140f
    private final val cellSize =  300f
    fun initPaints() {

        // fill
        fillPaint.style = Paint.Style.FILL
        fillPaint.color = Color.parseColor("#E1E1E1E1")

        // stroke
        strokePaint.style = Paint.Style.STROKE
        strokePaint.color = Color.GRAY
        strokePaint.strokeWidth = 10f
    }
    override fun onDraw(canvas: Canvas) {
        var r: RectF
        // First rectangle
        for(j in 0..4){
            for(i in 0..3){
                initPaints()
                val cornerRadius = 20
                r = RectF(originX + (i) * cellSize, originY + j * cellSize, originX + (i + 1) * cellSize, originY + (j +1) * cellSize)
                canvas.drawRoundRect(r, cornerRadius.toFloat(), cornerRadius.toFloat(), fillPaint);    // fill
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