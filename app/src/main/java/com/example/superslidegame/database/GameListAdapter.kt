package com.example.superslidegame.database

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.superslidegame.R
import com.example.superslidegame.game.entities.Game

class GameListAdapter : ListAdapter<Game, GameListAdapter.GameViewHolder>(GameComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        return GameViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }

    class GameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val levelTextView: TextView = itemView.findViewById(R.id.levelTextView)

        fun bind(game: Game?) {
            levelTextView.text = game?.level.toString()
            game?.won?.let {
                if (it) {
                    // If the game was won, set the background to green
                    levelTextView.backgroundTintList = ColorStateList.valueOf(Color.GREEN)
                } else {
                    // If the game was lost, set the background to red
                    levelTextView.backgroundTintList = ColorStateList.valueOf(Color.RED)
                }
            }
            levelTextView.background
        }

        companion object {
            fun create(parent: ViewGroup): GameViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recyclerview_item, parent, false)
                return GameViewHolder(view)
            }
        }
    }

    class GameComparator : DiffUtil.ItemCallback<Game>() {
        override fun areItemsTheSame(oldItem: Game, newItem: Game): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Game, newItem: Game): Boolean {
            return oldItem.id == newItem.id
        }
    }
}