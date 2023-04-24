package com.example.superslidegame.game.elements

import android.view.View
import android.view.View.OnClickListener
import com.example.superslidegame.game.screen.SelectLevel

class LevelSelectorClickListener(private val levelClicked: Int, private val activity: SelectLevel) : OnClickListener {

    override fun onClick(position: View?) {
        activity.setLevel(levelClicked + 1)
    }
}