package com.example.footballleagueapplication.data.models.leagues_model


import com.google.gson.annotations.SerializedName

data class Winner(
    @SerializedName("crestUrl")
    val crestUrl: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("shortName")
    val shortName: String,
    @SerializedName("tla")
    val tla: String
)