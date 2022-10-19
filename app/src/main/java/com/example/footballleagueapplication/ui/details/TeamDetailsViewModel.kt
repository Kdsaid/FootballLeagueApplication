package com.example.footballleagueapplication.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.footballleagueapplication.data.api.ApiHelper
import com.example.footballleagueapplication.data.models.leagues_model.LeaguesModel
import com.example.footballleagueapplication.data.models.team_details_model.TeamDetailsModels
import com.example.footballleagueapplication.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class TeamDetailsViewModel(private val repository: ApiHelper, id: Int) : ViewModel() {
    private val _mutableStateFlow =
        MutableStateFlow<Resource<TeamDetailsModels>>(Resource.loading())
    val teamDetails: StateFlow<Resource<TeamDetailsModels>> = _mutableStateFlow

    init {
        getTeamDetails(id = id)
    }

    private fun getTeamDetails(
        id: Int

    ) {
        viewModelScope.launch {
            _mutableStateFlow.value = Resource.loading()
            repository.getTeamDetails(id = id).flowOn(Dispatchers.IO).catch { e ->
                _mutableStateFlow.value = Resource.error(data = null, message = e.toString())
            }.collect {
                _mutableStateFlow.value = Resource.success(it)
            }

        }


    }
}