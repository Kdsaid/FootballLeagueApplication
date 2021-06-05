package com.example.footballleagueapplication.data.models.teams_model


import com.google.gson.annotations.SerializedName

data class Area(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)