package com.example.footballleagueapplication.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.footballleagueapplication.R
import com.example.footballleagueapplication.data.models.teams_model.Team
import com.example.footballleagueapplication.view.fragment.AboutFragmentDirections
import kotlinx.android.synthetic.main.team_item_row.view.*
import kotlinx.android.synthetic.main.team_item_row.view.cvTeam
import kotlinx.android.synthetic.main.team_item_row.view.tvTeamName


class TeamDataAdapter(teams: ArrayList<Team>, context: Context) :
    RecyclerView.Adapter<TeamDataAdapter.TeamHolder>() {
    private var context: Context
    private var teams: ArrayList<Team>? = null

    override fun onCreateViewHolder(parent: ViewGroup, i: Int): TeamHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.team_item_row,
            parent, false
        )

        return TeamHolder(itemView)
    }

    init {
        this.teams = teams

        this.context = context
    }

    override fun onBindViewHolder(holder: TeamHolder, postion: Int) {


        teams?.get(postion)?.let { holder.bind(it) }



    }

//
//    fun setTeamList(teams: ArrayList<Team>, context: TeamsActivity) {
//        this.teams = teams
//        notifyDataSetChanged()
//        this.context = context
//    }

    override fun getItemCount() = teams?.size ?: 0

    inner class TeamHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(team: Team) {
            itemView.tvTeamName.text = team.name
            itemView.tvClubColor.text = team.clubColors
            itemView.tvUrl.text = team.website
            itemView.tvVenue.text = team.venue
            itemView.tvShotTeamName.text = team.shortName
            itemView.cvTeam.setOnClickListener {

                val nextAction = AboutFragmentDirections.nextAction()
                nextAction.playerId = team.id
                Navigation.findNavController(it).navigate(nextAction)

            }

      }


    }


}
