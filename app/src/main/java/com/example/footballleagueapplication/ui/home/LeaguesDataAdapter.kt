package com.example.footballleagueapplication.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.footballleagueapplication.data.models.leagues_model.Competition
import com.example.footballleagueapplication.databinding.TeamsItemRowBinding

class LeaguesDataAdapter(private var teams: List<Competition>?,
                         private val itemClick: (View, Int) -> Unit) :
    RecyclerView.Adapter<LeaguesDataAdapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            TeamsItemRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: ViewHolder, postion: Int) {
        holder.bind(teams?.get(postion),itemClick)
    }


    override fun getItemCount() = teams?.size ?: 0

    class ViewHolder(private var binding: TeamsItemRowBinding) :
        RecyclerView.ViewHolder(binding.root){

        fun bind(get: Competition?, itemClick: (View, Int) -> Unit) {
            binding.tvTeamName.text = get!!.name
            itemView.setOnClickListener {
                itemClick(it,get.id)
            }

        }
    }


}



