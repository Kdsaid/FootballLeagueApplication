package com.example.footballleagueapplication.data.models.teams_model


import com.google.gson.annotations.SerializedName

data class TeemsModel(
    @SerializedName("competition")
    val competition: Competition,
    @SerializedName("count")
    val count: Int,
    @SerializedName("filters")
    val filters: Filters,
    @SerializedName("season")
    val season: Season,
    @SerializedName("teams")
    val teams: List<Team>
)