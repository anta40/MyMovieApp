package com.anta40.app.mymovieapp.repository

import com.anta40.app.mymovieapp.data.ApiClient
import com.anta40.app.mymovieapp.model.GenreData
import retrofit2.Call

object MainActivityRepository {
    fun getGenreApiCall(api_key: String): Call<GenreData> {

        val call = ApiClient.apiInterface.getGenreList(api_key)

        return call
    }

}