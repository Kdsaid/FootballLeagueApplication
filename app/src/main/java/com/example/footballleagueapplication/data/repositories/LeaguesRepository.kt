package com.example.footballleagueapplication.data.repositories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.footballleagueapplication.Constants
import com.example.footballleagueapplication.data.network.RetrofitClient
import com.example.footballleagueapplication.data.network.responses.leagues_model.Competition
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.*

class LeaguesRepository {
    private var teams = ArrayList<Competition>()
    private val mutableLiveData = MutableLiveData<List<Competition>>()

    private val userDataService = RetrofitClient.getInstance().api

    fun getMutableLiveData(coroutineScope: CoroutineScope): MutableLiveData<List<Competition>> {
//


        coroutineScope.launch {
            val call = userDataService.leagues(Constants.API_KEY)

            // Get the Deferred object for our Retrofit request
            try {

//                _status.value = MarsApiStatus.LOADING
//                // this will run on a thread managed by Retrofit
                var listResult = call.await()

                teams = listResult.competitions as ArrayList<Competition>
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


//        call.enqueue(object : Callback<TeamsModel> {
//            override fun onResponse(call: Call<TeamsModel>, response: Response<TeamsModel>) {
//                val teamResponse = response.body()
//
//                if (teamResponse != null) {
//                    teams = teamResponse.competitions as ArrayList<Competition>
//                    mutableLiveData.value = teams
//                    Log.i("onResponsexxxx",""+Gson().toJson(teamResponse))
//
//                }
//
//            }
//
//            override fun onFailure(call: Call<TeamsModel>, t: Throwable) {
//                Log.i("asdsad","onFailure")
//            }
//        })

        return mutableLiveData
    }
}