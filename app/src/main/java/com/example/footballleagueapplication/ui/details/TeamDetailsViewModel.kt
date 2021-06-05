package com.example.footballleagueapplication.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.footballleagueapplication.data.api.ApiHelper
import com.example.footballleagueapplication.data.api.RetrofitBuilder
import com.example.footballleagueapplication.data.api.repository.MainRepository
import com.example.footballleagueapplication.utils.Resource
import kotlinx.coroutines.Dispatchers

class TeamDetailsViewModel : ViewModel() {
    private val repository = MainRepository(ApiHelper(RetrofitBuilder.apiService))


    fun getTeamDetails(
        id:Int

    ) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(repository.getTeamDetails(id)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

}