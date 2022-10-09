package com.example.footballleagueapplication.data.api

import com.example.footballleagueapplication.data.models.leagues_model.LeaguesModel
import com.example.footballleagueapplication.data.models.team_details_model.TeamDetailsModels
import com.example.footballleagueapplication.data.models.teams_model.TeemsModel
import kotlinx.coroutines.flow.Flow

interface ApiHelper {
    fun leagues(): Flow<LeaguesModel>

    fun getTeam(id: Int): Flow<TeemsModel>

    fun getTeamDetails(id: Int): Flow<TeamDetailsModels>
}