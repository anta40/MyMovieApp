package com.anta40.app.mymovieapp.model

import com.google.gson.annotations.SerializedName

data class Trailer (
    @SerializedName("id") val id: Int,
    @SerializedName("iso_639_1") val iso_639_1: String,
    @SerializedName("iso_3166_1") val iso_3166_1: String,
    @SerializedName("key") val key: String,
    @SerializedName("name") val name: String,
    @SerializedName("site") val site: String,
    @SerializedName("size") val size: Int,
    @SerializedName("type") val type: String
    )

data class TrailerData (
    @SerializedName("results") val trailers: List<Trailer>
)
