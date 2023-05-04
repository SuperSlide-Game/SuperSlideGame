package com.example.superslidegame.game.elements

import android.app.Activity
import android.graphics.Color
import android.util.SparseBooleanArray
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams
import android.widget.BaseAdapter
import android.widget.Button
import com.example.superslidegame.game.levels.GameLevel


class LevelListAdapter(private val activity: Activity) : BaseAdapter() {
    private val buttonStateArray = SparseBooleanArray()
    private val maxLevel = GameLevel.MAX_LEVEL

    override fun getCount(): Int {
        return maxLevel
    }

    override fun getItem(p0: Int): Any? {
        return null
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val button : Button
        if (convertView == null) {
            button = Button(activity.baseContext)
            button.textAlignment = View.TEXT_ALIGNMENT_CENTER
            button.gravity = Gravity.CENTER
            button.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
            button.textSize = 15f

        } else {
            button = convertView as Button
        }
        button.text = String.format("Level %d", position + 1)
        if (buttonStateArray[position, false]) {
            button.setBackgroundColor(Color.GREEN) // Change to the color you want when clicked
        } else {
            button.setBackgroundColor(Color.WHITE) // Change to the default color
        }

        // Set a click listener for the button

        // Set a click listener for the button
        button.setOnClickListener {
            val currentState = buttonStateArray[position, false]
            buttonStateArray.put(position, !currentState)

            // Set the background color based on the new state
            if (!currentState) {
                button.setBackgroundColor(Color.GREEN) // Change to the color you want when clicked
            } else {
                button.setBackgroundColor(Color.WHITE) // Change to the default color
            }
        }
        return button
    }
}