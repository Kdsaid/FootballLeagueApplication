package com.example.footballleagueapplication.view.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.footballleagueapplication.data.network.responses.teams_model.Team
import com.example.footballleagueapplication.data.repositories.TeamRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class TeamsViewModel : ViewModel() {

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private var teamRepository: TeamRepository =
        TeamRepository()

    fun getAllTeam(id:Int): LiveData<List<Team>> {
        return teamRepository.getMutableLiveData(coroutineScope,id)
    }


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
