package com.example.footballleagueapplication.data.api.repository

import com.example.footballleagueapplication.data.api.ApiHelper

class MainRepository(private val apiHelper: ApiHelper) {
    suspend fun leagues(
    ) = apiHelper.leagues()

    suspend fun getTeam(
        id: Int
    ) = apiHelper.getTeam(id)
    suspend fun getTeamDetails(
        id: Int
    ) = apiHelper.getTeamDetails(id)

}