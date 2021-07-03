package com.anta40.app.mymovieapp.repository

import com.anta40.app.mymovieapp.data.ApiClient
import com.anta40.app.mymovieapp.model.MovieData
import retrofit2.Call

object MovieListActivityRepository {

    fun getMovieApiCall(api_key: String, movie_genre: String): Call<MovieData> {

        val call = ApiClient.apiInterface.getMovieList(api_key, movie_genre)

        return call
    }
}