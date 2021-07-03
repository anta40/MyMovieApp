package com.anta40.app.mymovieapp.model

import com.google.gson.annotations.SerializedName

data class Genre (
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String
)

data class GenreData (
    @SerializedName("genres") val genres : List<Genre>
)