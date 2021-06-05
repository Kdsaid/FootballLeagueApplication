package com.example.footballleagueapplication.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.footballleagueapplication.data.models.team_details_model.Squad
import com.example.footballleagueapplication.R
import kotlinx.android.synthetic.main.team_player_item_row.view.*


class TeamPlayerAdapter(private var  squad: List<Squad>?, private val  context: Context) :
    RecyclerView.Adapter<TeamPlayerAdapter.TeamHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, i: Int): TeamHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.team_player_item_row,
            parent, false
        )

        return TeamHolder(itemView)
    }


    override fun onBindViewHolder(holder: TeamHolder, postion: Int) {
        val squad = squad?.get(postion)
        squad?.let { holder.bind(it) }
    }



    override fun getItemCount() = squad?.size ?: 0

    inner class TeamHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(squad: Squad) {
            itemView.tvPlayer.text=squad.name

        }


    }


}
