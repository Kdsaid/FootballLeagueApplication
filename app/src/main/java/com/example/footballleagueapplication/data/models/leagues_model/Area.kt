package com.example.footballleagueapplication.data.models.leagues_model


import com.google.gson.annotations.SerializedName

data class Area(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)