package com.example.footballleague.data.network.responses.team_details_model


import com.google.gson.annotations.SerializedName

data class Area(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)