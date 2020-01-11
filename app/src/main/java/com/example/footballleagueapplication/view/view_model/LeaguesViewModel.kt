package com.example.footballleagueapplication.view.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.footballleagueapplication.data.network.responses.leagues_model.Competition
import com.example.footballleagueapplication.data.repositories.LeaguesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class LeaguesViewModel : ViewModel() {
    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private var leaguesRepository: LeaguesRepository =
        LeaguesRepository()

    fun getAllTeam(): LiveData<List<Competition>> {
        return leaguesRepository.getMutableLiveData(coroutineScope)
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}