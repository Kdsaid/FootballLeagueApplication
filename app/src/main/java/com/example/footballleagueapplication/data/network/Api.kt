package com.example.footballleagueapplication.data.network

import com.example.footballleague.data.network.responses.team_details_model.TeamDetailsModels
import com.example.footballleagueapplication.Constants
import com.example.footballleagueapplication.Constants.teams
import com.example.footballleagueapplication.data.network.responses.leagues_model.LeaguesModel
import com.example.footballleagueapplication.data.network.responses.teams_model.TeemsModel
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path


interface Api {
    @Headers("Accept: application/json")
    @GET("competitions")
    fun leagues(@Header(Constants.X_Auth_Token) key: String): Deferred<LeaguesModel>

    @Headers("Accept: application/json")
    @GET("competitions/{${Constants.leagueId}}/teams")
    fun getTeam(
        @Header(Constants.X_Auth_Token) key: String,
        @Path(Constants.leagueId) id: Int
    ): Deferred<TeemsModel>

    @Headers("Accept: application/json")
    @GET("${teams}/{${Constants.idTeamDetails}}")
    fun getTeamDetails(
        @Header(Constants.X_Auth_Token) key: String,
        @Path(Constants.idTeamDetails) id: Int
    ): Deferred<TeamDetailsModels>

}