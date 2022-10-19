package com.example.footballleagueapplication.ui.about


import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.footballleagueapplication.data.api.ApiHelper
import com.example.footballleagueapplication.data.models.team_details_model.TeamDetailsModels
import com.example.footballleagueapplication.data.models.teams_model.TeemsModel

import com.example.footballleagueapplication.utils.Resource

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch


class TeamsViewModel( private val repository: ApiHelper,private val id:Int) : ViewModel() {



    private val _mutableStateFlow =
        MutableStateFlow<Resource<TeemsModel>>(Resource.loading())
    val team: StateFlow<Resource<TeemsModel>> = _mutableStateFlow

    init {
        getTeam()
    }

    private fun getTeam(

    ) {
        viewModelScope.launch {
            _mutableStateFlow.value = Resource.loading()
            repository.getTeam(id = id).flowOn(Dispatchers.IO).catch { e ->
                _mutableStateFlow.value = Resource.error(data = null, message = e.toString())
            }.collect {
                _mutableStateFlow.value = Resource.success(it)
            }

        }


    }
}



