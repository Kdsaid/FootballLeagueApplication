package com.example.footballleagueapplication.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.footballleagueapplication.data.api.ApiHelper
import com.example.footballleagueapplication.ui.about.TeamsViewModel
import com.example.footballleagueapplication.ui.details.TeamDetailsViewModel
import com.example.footballleagueapplication.ui.home.LeaguesViewModel

class ViewModelFactory(private val apiHelper: ApiHelper,private val id: Int=0) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TeamsViewModel::class.java)) {
            return TeamsViewModel(apiHelper,id) as T
        }
        if (modelClass.isAssignableFrom(TeamDetailsViewModel::class.java)) {
            return TeamDetailsViewModel(apiHelper,id) as T
        }
        if (modelClass.isAssignableFrom(LeaguesViewModel::class.java)) {
            return LeaguesViewModel(apiHelper) as T
        }

        throw IllegalArgumentException("Unknown class name")
    }

}