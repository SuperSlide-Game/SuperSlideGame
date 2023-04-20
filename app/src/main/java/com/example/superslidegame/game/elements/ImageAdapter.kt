package com.example.superslidegame.game.elements

import android.content.Context
import android.widget.BaseAdapter

class ImageAdapter(private val context: Context) : BaseAdapter() {

    private val BOARD_SIZE = 20
    private val pieces = arrayOf(

    )

    override fun getCount(): Int {
        return BOARD_SIZE
    }

    override fun getItem(position: Int): Any {
        return pieces[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: android.view.View?, parent: android.view.ViewGroup?): android.view.View {
        TODO("Not yet implemented")
    }
}