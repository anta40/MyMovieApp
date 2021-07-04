package com.anta40.app.mymovieapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.anta40.app.mymovieapp.model.MovieData
import com.anta40.app.mymovieapp.model.TrailerData
import com.anta40.app.mymovieapp.repository.MovieDetailActivityRepository
import com.anta40.app.mymovieapp.repository.MovieListActivityRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetailActivityViewModel {

    private val _trailerLiveData = MutableLiveData<TrailerData>()
    val trailerLiveData: LiveData<TrailerData> = _trailerLiveData

    fun getTrailerData(movie_id: Int, api_key: String){
        MovieDetailActivityRepository.getTrailerListApiCall(movie_id, api_key).enqueue(object:
            Callback<TrailerData> {
            override fun onResponse(
                call: Call<TrailerData>,
                response: Response<TrailerData>
            ) {
                val body = response.body()
                _trailerLiveData.value = response.body()
            }

            override fun onFailure(call: Call<TrailerData>, t: Throwable) {
                Log.e("MainViewModel", "onFailure: ", t)
            }
        })
    }
}