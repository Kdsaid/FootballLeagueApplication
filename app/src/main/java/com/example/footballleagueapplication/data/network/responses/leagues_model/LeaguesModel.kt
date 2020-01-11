package com.example.footballleagueapplication.data.network.responses.leagues_model


import com.google.gson.annotations.SerializedName

data class LeaguesModel(
    @SerializedName("competitions")
    val competitions: List<Competition>,
    @SerializedName("count")
    val count: Int,
    @SerializedName("filters")
    val filters: Filters
)