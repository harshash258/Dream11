package com.mycompany.dream11

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class WKAdapter(val players: List<String>, var click: OnClick) :
    RecyclerView.Adapter<WKAdapter.ViewHolder>() {

    var max = 0
    var positions: MutableList<Int> = mutableListOf()


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.playerName)
        val selectPlayer: ImageView = itemView.findViewById(R.id.image)
/*        val team: TextView = itemView.findViewById(R.id.playerTeam)
        val image: CircleImageView = itemView.findViewById(R.id.playerImage)*/

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.player_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var isSelected = true
        val player = players[position]

        holder.name.text = player
        if (max >= 2) {
            val newPositions: MutableSet<Int> = mutableSetOf()
            GlobalScope.launch {
                for (i in 0..players.size)
                    newPositions.add(i)
                newPositions.removeAll(positions)
                Log.d("New", newPositions.toString())
            }
            GlobalScope.launch {
                for (i in newPositions) {
                    if (position == i) {
                        holder.itemView.setBackgroundResource(R.drawable.disable_player)
                        holder.itemView.isEnabled = false
                    }else{
                        holder.itemView.isEnabled = true
                    }
                }
            }

        }

        holder.itemView.setOnClickListener {
            isSelected = if (isSelected) {
                click.click(position, holder.itemView, holder.selectPlayer, true)
                max++
                if (max >= 2) {
                    notifyDataSetChanged()
                }
                positions.add(position)

                false
            } else {
                click.click(position, holder.itemView, holder.selectPlayer, false)
                max--
                positions.remove(position)
                true
            }
        }
    }


    override fun getItemCount(): Int {
        return players.size
    }

    interface OnClick {
        fun click(position: Int, itemView: View, selectPlayer: ImageView, b: Boolean)
    }
}