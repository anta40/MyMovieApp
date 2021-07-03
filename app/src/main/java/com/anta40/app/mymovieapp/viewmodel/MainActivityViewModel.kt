package com.anta40.app.mymovieapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anta40.app.mymovieapp.model.Genre
import com.anta40.app.mymovieapp.model.GenreData
import com.anta40.app.mymovieapp.repository.MainActivityRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel: ViewModel() {

    private val _genreLiveData = MutableLiveData<GenreData>()
    val genreLiveData: LiveData<GenreData> = _genreLiveData

    fun getData(api_key: String){
        MainActivityRepository.getGenreApiCall(api_key).enqueue(object: Callback<GenreData> {
            override fun onResponse(
                call: Call<GenreData>,
                response: Response<GenreData>
            ) {
                val body = response.body()
                _genreLiveData.value = response.body()
            }

            override fun onFailure(call: Call<GenreData>, t: Throwable) {
                Log.e("MainViewModel", "onFailure: ", t)
            }
        })
    }
}