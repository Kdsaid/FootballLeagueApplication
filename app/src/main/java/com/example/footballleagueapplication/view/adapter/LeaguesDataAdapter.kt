package com.example.footballleagueapplication.view.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.footballleagueapplication.R
import com.example.footballleagueapplication.data.network.responses.leagues_model.Competition
import com.example.footballleagueapplication.toast
import com.example.footballleagueapplication.view.fragment.HomeFragmentDirections
import com.google.gson.Gson
import kotlinx.android.synthetic.main.teams_item_row.view.*
import kotlin.collections.ArrayList


class LeaguesDataAdapter(teams: List<Competition>?, context: Context) :
    RecyclerView.Adapter<LeaguesDataAdapter.TeamHolder>() {
    private var context: Context
    private var teams: List<Competition>? = null

    override fun onCreateViewHolder(parent: ViewGroup, i: Int): TeamHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.teams_item_row,
            parent, false
        )

        return TeamHolder(itemView)
    }

    init {
        this.teams = teams as ArrayList<Competition>?

        this.context = context
    }

    override fun onBindViewHolder(holder: TeamHolder, postion: Int) {

        Log.d("asdasd", "dsasa" + teams?.size)

        holder.bind(teams?.get(postion))

    }

//
//    fun setTeamList(teams: ArrayList<Team>, context: TeamsActivity) {
//        this.teams = teams
//        notifyDataSetChanged()
//        this.context = context
//    }

    override fun getItemCount() = teams?.size ?: 0

    inner class TeamHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(get: Competition?) {
            itemView.tvTeamName.text = get!!.name
            itemView.cvTeam.setOnClickListener {
//                context.toast(get.id.toString())

                val nextAction = HomeFragmentDirections.nextAction()
                nextAction.leagueId = get.id
                Navigation.findNavController(it).navigate(nextAction)

            }

            Log.i("DSAD", "" + Gson().toJson(get))
        }
    }



}



