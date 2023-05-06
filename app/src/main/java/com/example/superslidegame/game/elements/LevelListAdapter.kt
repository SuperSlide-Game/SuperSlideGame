package com.example.superslidegame.game.elements

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams
import android.widget.BaseAdapter
import android.widget.Button
import com.example.superslidegame.game.levels.GameLevel
import com.example.superslidegame.game.screen.SelectLevel

class LevelListAdapter(private val activity: SelectLevel) : BaseAdapter() {
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

        // Set a click listener for the button
        button.setOnClickListener(LevelSelectorClickListener(
            position,
            activity,
            this
        ))
        val selectedLevel = activity.selectedLevel
        val buttonText = button.text.toString()
        val levelNumber = buttonText.substring(buttonText.length - 1).toInt()
        if (levelNumber == selectedLevel) {
            button.backgroundTintList = ColorStateList.valueOf(Color.GREEN)
        } else {
            button.backgroundTintList = ColorStateList.valueOf(Color.WHITE)
        }
        return button
    }
}