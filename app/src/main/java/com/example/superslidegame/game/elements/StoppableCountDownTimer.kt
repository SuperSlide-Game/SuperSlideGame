package com.example.superslidegame.game.elements

import android.os.CountDownTimer
import android.widget.TextView
import com.example.superslidegame.game.screen.GameScreen

/**
 * A CountDownTimer that can be stopped and returns the time left when stopped
 * @param millisInFuture The number of millis in the future from the call to start() until the countdown is done and onFinish() is called
 * @param countDownInterval The interval along the way to receive onTick(long) callbacks
 * @param gameScreen The GameScreen that created this timer
 * @param textView The TextView that will be updated with the time left
 */
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

    fun getTimeLeft() : Long {
        return secondsRemaining
    }
}