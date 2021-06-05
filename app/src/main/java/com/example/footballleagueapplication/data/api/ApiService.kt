package com.example.footballleagueapplication.data.api

import com.example.footballleagueapplication.data.models.team_details_model.TeamDetailsModels
import com.example.footballleagueapplication.configs.Constants
import com.example.footballleagueapplication.configs.Constants.API_KEY
import com.example.footballleagueapplication.configs.Constants.teams
import com.example.footballleagueapplication.data.models.leagues_model.LeaguesModel
import com.example.footballleagueapplication.data.models.teams_model.TeemsModel
import retrofit2.http.*


interface ApiService {

    @Headers("Accept: application/json")
    @GET("competitions")
    suspend fun leagues(@Header(Constants.X_Auth_Token) key: String= API_KEY): LeaguesModel
    @Headers("Accept: application/json")
    @GET("competitions/{${Constants.leagueId}}/teams")
    suspend fun getTeam(
        @Header(Constants.X_Auth_Token) key: String,
        @Path(Constants.leagueId) id: Int
    ): TeemsModel
    @Headers("Accept: application/json")
    @GET("${teams}/{${Constants.idTeamDetails}}")
    suspend fun getTeamDetails(
        @Header(Constants.X_Auth_Token) key: String,
        @Path(Constants.idTeamDetails) id: Int
    ): TeamDetailsModels


}