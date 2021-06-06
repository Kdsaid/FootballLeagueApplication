package com.example.footballleagueapplication.ui.about

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView

import com.example.footballleagueapplication.data.models.teams_model.Team
import com.example.footballleagueapplication.databinding.TeamItemRowBinding

class TeamDataAdapter(
    private var teams: ArrayList<Team>,private val itemClick: (View, Int) -> Unit
) :
    RecyclerView.Adapter<TeamDataAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            TeamItemRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = teams.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        teams[position].let { holder.bind(it,itemClick) }

    }
    class ViewHolder(private var binding: TeamItemRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(team: Team, itemClick: (View, Int) -> Unit) {

            binding.tvTeamName.text = team.name
            binding.tvClubColor.text = team.clubColors
            binding.tvUrl.text = team.website
            binding.tvVenue.text = team.venue
            binding.tvShotTeamName.text = team.shortName
            itemView.setOnClickListener {
                itemClick(it,team.id)
            }

        }
    }

}
