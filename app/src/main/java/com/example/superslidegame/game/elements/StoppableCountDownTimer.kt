package com.example.superslidegame.game.elements

import android.os.CountDownTimer
import android.widget.TextView
import com.example.superslidegame.game.screen.GameScreen

class StoppableCountDownTimer(millisInFuture : Long, countDownInterval : Long, private val gameScreen: GameScreen, private val textView: TextView) : CountDownTimer(millisInFuture, countDownInterval) {

    private var secondsRemaining : Long = 0

    override fun onTick(millisUntilFinished: Long) {
        secondsRemaining = millisUntilFinished / 1000
        textView.text = String.format("Time left: %d seconds", secondsRemaining)
    }

    override fun onFinish() {
        gameScreen.onGameFinished(secondsRemaining)
    }

    fun cancelAndGetTimeLeft() : Long {
        cancel()
        return secondsRemaining
    }
}