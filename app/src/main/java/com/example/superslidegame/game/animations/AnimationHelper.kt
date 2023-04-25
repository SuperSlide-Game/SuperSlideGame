package com.example.superslidegame.game.animations

import android.app.Activity
import android.media.AudioAttributes
import android.media.SoundPool
import com.example.superslidegame.R

class AnimationHelper(gameScreen: Activity) {

    private val audioAttributes = AudioAttributes.Builder().setContentType(AudioAttributes.CONTENT_TYPE_MUSIC).setUsage(AudioAttributes.USAGE_GAME).build()
    private val soundsPool = SoundPool.Builder().setMaxStreams(2).setAudioAttributes(audioAttributes).build()

    private val sound1 = soundsPool.load(gameScreen.baseContext, R.raw.move_sound, 0)

    fun playMoveSound() {
        soundsPool.play(sound1, 1F, 1F, 1, 0, 1F)
    }

    fun playWinSound() {
        TODO()
    }

}