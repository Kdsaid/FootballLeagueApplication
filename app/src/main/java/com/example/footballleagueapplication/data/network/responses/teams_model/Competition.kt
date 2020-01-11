package com.example.footballleagueapplication.data.network.responses.teams_model


import com.example.footballleagueapplication.data.network.responses.teams_model.AreaX
import com.google.gson.annotations.SerializedName

data class Competition(
    @SerializedName("area")
    val area: AreaX,
    @SerializedName("code")
    val code: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("lastUpdated")
    val lastUpdated: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("plan")
    val plan: String
)