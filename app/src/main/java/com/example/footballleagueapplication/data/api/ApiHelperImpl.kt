package com.example.footballleagueapplication.data.api

import kotlinx.coroutines.flow.flow

class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {


    override fun leagues() = flow { emit(apiService.leagues()) }

    override fun getTeam(id: Int) = flow { emit(apiService.getTeam(id = id)) }

    override fun getTeamDetails(id: Int) = flow { emit(apiService.getTeamDetails(id = id)) }


}