package com.example.footballleagueapplication.ui.home

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.footballleagueapplication.R
import com.example.footballleagueapplication.data.models.leagues_model.Competition
import com.example.footballleagueapplication.utils.inflate
import kotlinx.android.synthetic.main.teams_item_row.view.*

class LeaguesDataAdapter(private var teams: List<Competition>?,
                         private val itemClick: (View, Int) -> Unit) :
    RecyclerView.Adapter<LeaguesDataAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        position: Int
    ): ViewHolder =
        ViewHolder(parent.inflate(R.layout.teams_item_row))



    override fun onBindViewHolder(holder: ViewHolder, postion: Int) {
        holder.bind(teams?.get(postion),itemClick)
    }


    override fun getItemCount() = teams?.size ?: 0

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(get: Competition?, itemClick: (View, Int) -> Unit) {
            itemView.tvTeamName.text = get!!.name
            itemView.setOnClickListener {
                itemClick(it,get.id)
            }

        }
    }


}



