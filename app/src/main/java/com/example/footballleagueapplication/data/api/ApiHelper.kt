package com.example.footballleagueapplication.data.api

class ApiHelper(private val apiService: ApiService) {


    suspend fun leagues(
    ) = apiService.leagues()

    suspend fun getTeam(
        id: Int
    ) = apiService.getTeam(id=id)
    suspend fun getTeamDetails(
        id: Int
    ) = apiService.getTeamDetails(id = id)

}