package com.example.footballleagueapplication.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.footballleagueapplication.data.api.ApiHelper
import com.example.footballleagueapplication.data.models.leagues_model.LeaguesModel
import com.example.footballleagueapplication.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class LeaguesViewModel(private val repository: ApiHelper) : ViewModel() {
    private val _mutableStateFlow =
        MutableStateFlow<Resource<LeaguesModel>>(Resource.loading(data = null))
    val leagues: StateFlow<Resource<LeaguesModel>> = _mutableStateFlow

    init {
        getLeagues()
    }

    private fun getLeagues() {


        viewModelScope.launch {
            _mutableStateFlow.value = Resource.loading(data = null)
            repository.leagues().flowOn(Dispatchers.IO).catch { e ->
                _mutableStateFlow.value = Resource.error(data = null, message = e.toString())
            }.collect {
                _mutableStateFlow.value = Resource.success(it)
            }

        }
    }
}







