package com.example.footballleagueapplication.view.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.footballleague.data.network.responses.team_details_model.TeamDetailsModels
import com.example.footballleagueapplication.data.repositories.TeamDetailsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class TeamDetailsViewModel : ViewModel() {
    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private var teamDetailsRepository: TeamDetailsRepository =
        TeamDetailsRepository()
    fun getDetailsTeam(id:Int): LiveData<TeamDetailsModels> {

        return teamDetailsRepository.getMutableLiveData(id,coroutineScope)
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()

    }
}