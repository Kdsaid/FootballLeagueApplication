package com.example.footballleagueapplication.ui.about

import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.footballleagueapplication.R
import com.example.footballleagueapplication.data.models.teams_model.Team
import com.example.footballleagueapplication.utils.inflate
import kotlinx.android.synthetic.main.team_item_row.view.*
import kotlinx.android.synthetic.main.team_item_row.view.tvTeamName


class TeamDataAdapter(
    private var teams: ArrayList<Team>,private val itemClick: (View, Int) -> Unit
) :
    RecyclerView.Adapter<TeamDataAdapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder =
        ViewHolder(parent.inflate(R.layout.team_item_row))




    override fun onBindViewHolder(holder: ViewHolder, postion: Int) {


        teams[postion].let { holder.bind(it,itemClick) }


    }



    override fun getItemCount() = teams.size

     class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(team: Team, itemClick: (View, Int) -> Unit) {
            itemView.tvTeamName.text = team.name
            itemView.tvClubColor.text = team.clubColors
            itemView.tvUrl.text = team.website
            itemView.tvVenue.text = team.venue
            itemView.tvShotTeamName.text = team.shortName
            itemView.setOnClickListener {
            itemClick(it,team.id)
            }

        }


    }


}
