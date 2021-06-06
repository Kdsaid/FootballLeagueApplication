package com.example.footballleagueapplication.ui.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.footballleagueapplication.R
import com.example.footballleagueapplication.data.models.team_details_model.Squad
import com.example.footballleagueapplication.databinding.TeamItemRowBinding
import com.example.footballleagueapplication.databinding.TeamPlayerItemRowBinding
import com.example.footballleagueapplication.ui.about.TeamDataAdapter
import com.example.footballleagueapplication.utils.inflate
import kotlinx.android.synthetic.main.team_player_item_row.view.*


class TeamPlayerAdapter(private var  squad: List<Squad>?) :
    RecyclerView.Adapter<TeamPlayerAdapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            TeamPlayerItemRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
    override fun onBindViewHolder(holder: ViewHolder, postion: Int) {
        val squad = squad?.get(postion)
        squad?.let { holder.bind(it) }
    }


    override fun getItemCount() = squad?.size ?: 0

    class ViewHolder(var binding: TeamPlayerItemRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(it: Squad) {
           binding.tvPlayer.text=it.name
        }
    }

}
