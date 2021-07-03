package com.anta40.app.mymovieapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.anta40.app.mymovieapp.model.MovieData
import com.anta40.app.mymovieapp.repository.MovieListActivityRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieListActivityViewModel {
    private val _movieLiveData = MutableLiveData<MovieData>()
    val movieLiveData: LiveData<MovieData> = _movieLiveData

    fun getData(api_key: String, movie_genre: String){
        MovieListActivityRepository.getMovieApiCall(api_key, movie_genre).enqueue(object: Callback<MovieData> {
            override fun onResponse(
                call: Call<MovieData>,
                response: Response<MovieData>
            ) {
                val body = response.body()
                _movieLiveData.value = response.body()
            }

            override fun onFailure(call: Call<MovieData>, t: Throwable) {
                Log.e("MainViewModel", "onFailure: ", t)
            }
        })
    }
}