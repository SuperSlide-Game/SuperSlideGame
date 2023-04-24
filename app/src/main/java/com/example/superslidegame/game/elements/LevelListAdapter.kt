package com.example.superslidegame.game.elements

import android.app.Activity
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams
import android.widget.BaseAdapter
import android.widget.Button
import com.example.superslidegame.game.levels.GameLevel
import com.example.superslidegame.game.screen.SelectLevel

class LevelListAdapter(private val activity: Activity) : BaseAdapter() {

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
            button.layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
            button.setOnClickListener(LevelSelectorClickListener(position, activity as SelectLevel))
        } else {
            button = convertView as Button
        }
        button.text = String.format("Level %d", position + 1)
        return button
    }
}