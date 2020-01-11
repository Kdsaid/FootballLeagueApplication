package com.example.footballleagueapplication.data.repositories

import android.util.Log
import androidx.lifecycle.MutableLiveData
//import com.example.footballleague.util.Constants
import com.example.footballleagueapplication.data.network.RetrofitClient
import com.example.footballleague.data.network.responses.team_details_model.TeamDetailsModels
import com.example.footballleagueapplication.Constants
import com.example.footballleagueapplication.data.network.responses.teams_model.Team
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class TeamDetailsRepository {

    private val mutableLiveData = MutableLiveData<TeamDetailsModels>()

    fun getMutableLiveData(
        id: Int,
        coroutineScope: CoroutineScope
    ): MutableLiveData<TeamDetailsModels> {

        val userDataService = RetrofitClient.getInstance().api




        coroutineScope.launch {
            val call = userDataService.getTeamDetails(Constants.API_KEY, id)

            // Get the Deferred object for our Retrofit request
            try {

//                _status.value = MarsApiStatus.LOADING
//                // this will run on a thread managed by Retrofit
                var listResult = call.await()


                mutableLiveData.value = listResult
                Log.i("onResponsexxxx", "" + Gson().toJson(listResult))

                //
//                _status.value = MarsApiStatus.DONE
//                _properties.value = listResult
            } catch (e: Exception) {
//                _status.value = MarsApiStatus.ERROR
//                _properties.value = ArrayList()
            }
        }

        return mutableLiveData
    }
}