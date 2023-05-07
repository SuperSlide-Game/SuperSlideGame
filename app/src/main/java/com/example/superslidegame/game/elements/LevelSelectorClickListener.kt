package com.example.superslidegame.game.elements

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.View
import android.view.View.OnClickListener
import com.example.superslidegame.game.screen.SelectLevel

/**
 * LevelSelectorClickListener
 * This class is used to handle the click events of the level buttons in the level selector screen.
 * @param levelClicked The level that was clicked
 * @param activity The activity that the level selector is in
 * @param adapter The adapter that is used to display the level buttons
 */
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
        // Set the background tint list of the other buttons to the default color by refreshing the adapter
        adapter.notifyDataSetChanged()
    }
}