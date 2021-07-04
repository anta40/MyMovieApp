package com.anta40.app.mymovieapp.repository

import com.anta40.app.mymovieapp.data.ApiClient
import com.anta40.app.mymovieapp.model.MovieData
import com.anta40.app.mymovieapp.model.TrailerData
import retrofit2.Call

object MovieDetailActivityRepository {

    fun getTrailerListApiCall(movie_id: Int, api_key: String): Call<TrailerData> {
        val call = ApiClient.apiInterface.getTrailerList(movie_id, api_key)
        return call
    }
}