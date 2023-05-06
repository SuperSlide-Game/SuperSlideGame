package com.example.superslidegame.game.elements

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.View
import android.view.View.OnClickListener
import com.example.superslidegame.game.screen.SelectLevel

class LevelSelectorClickListener(
    private val levelClicked: Int,
    private val activity: SelectLevel,
    private val adapter: LevelListAdapter
) : OnClickListener {

    override fun onClick(position: View?) {
        // Set the level to the level clicked
        activity.setLevel(levelClicked + 1)
        // Set the background tint list of the button clicked to green
        position?.backgroundTintList = ColorStateList.valueOf(Color.GREEN)
        adapter.notifyDataSetChanged()
    }
}