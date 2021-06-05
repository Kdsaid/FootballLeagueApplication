package com.example.footballleagueapplication.data.api

import com.example.footballleagueapplication.configs.Constants

class ApiHelper(private val apiService: ApiService) {


    suspend fun leagues(
    ) = apiService.leagues()

    suspend fun getTeam(
        id: Int
    ) = apiService.getTeam(Constants.API_KEY,id)
    suspend fun getTeamDetails(
        id: Int
    ) = apiService.getTeamDetails(Constants.API_KEY,id)

}