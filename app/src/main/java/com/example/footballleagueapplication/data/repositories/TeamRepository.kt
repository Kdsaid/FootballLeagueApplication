package com.example.footballleagueapplication.data.repositories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.footballleagueapplication.Constants
import com.example.footballleagueapplication.data.network.RetrofitClient
import com.example.footballleagueapplication.data.network.responses.teams_model.Team
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.*

class TeamRepository {
    private var teams = ArrayList<Team>()
    private val mutableLiveData = MutableLiveData<List<Team>>()


    fun getMutableLiveData(coroutineScope: CoroutineScope, leagueId:Int): MutableLiveData<List<Team>> {

        val userDataService = RetrofitClient.getInstance().api



        coroutineScope.launch {
            val call = userDataService.getTeam(Constants.API_KEY,leagueId)

            // Get the Deferred object for our Retrofit request
            try {

//                _status.value = MarsApiStatus.LOADING
//                // this will run on a thread managed by Retrofit
                var listResult = call.await()

                teams = listResult.teams as ArrayList<Team>
                mutableLiveData.value = teams
                Log.i("onResponsexxxx",""+Gson().toJson(listResult))

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