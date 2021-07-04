package com.anta40.app.mymovieapp.model

import com.google.gson.annotations.SerializedName

data class Movie (
    @SerializedName("id") val id: Int,
    @SerializedName("original_title") val original_title: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("popularity") val popularity: Float,
    @SerializedName("poster_path") val poster_path: String,
    @SerializedName("backdrop_path") val backdrop_path: String,
    @SerializedName("release_date") val release_date: String,
    @SerializedName("video") val video: Boolean,
    @SerializedName("vote_average") val vote_average: Float,
    @SerializedName("vote_count") val vote_count: Float
)

data class MovieData (
    @SerializedName("results") val movies : List<Movie>
)