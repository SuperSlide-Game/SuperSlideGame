package com.example.superslidegame.game.elements

import android.graphics.Color
import android.util.SparseBooleanArray
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import com.example.superslidegame.game.screen.SelectLevel

class LevelSelectorClickListener(private val levelClicked: Int, private val activity: SelectLevel, private val button : Button, private val buttonStateArray : SparseBooleanArray) : OnClickListener {

    override fun onClick(position: View?) {
        if (buttonStateArray.get(levelClicked, false)) {
            button.setBackgroundColor(Color.GREEN); // Change to the color you want when clicked
        } else {
            button.setBackgroundColor(Color.TRANSPARENT); // Change to the default color
        }
        activity.setLevel(levelClicked + 1)
    }
}