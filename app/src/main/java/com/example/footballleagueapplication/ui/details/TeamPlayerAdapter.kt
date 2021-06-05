package com.example.footballleagueapplication.ui.details

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.footballleagueapplication.R
import com.example.footballleagueapplication.data.models.team_details_model.Squad
import com.example.footballleagueapplication.utils.inflate
import kotlinx.android.synthetic.main.team_player_item_row.view.*


class TeamPlayerAdapter(private var  squad: List<Squad>?) :
    RecyclerView.Adapter<TeamPlayerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder =
        ViewHolder(parent.inflate(R.layout.team_player_item_row))


    override fun onBindViewHolder(holder: ViewHolder, postion: Int) {
        val squad = squad?.get(postion)
        squad?.let { holder.bind(it) }
    }


    override fun getItemCount() = squad?.size ?: 0

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(it: Squad) {
            itemView.tvPlayer.text=it.name
        }
    }

}
