package com.anta40.app.mymovieapp.data

import com.anta40.app.mymovieapp.model.GenreData
import com.anta40.app.mymovieapp.model.MovieData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("genre/movie/list")
    fun getGenreList(@Query("api_key") api_key: String) : Call<GenreData>

    @GET("discover/movie")
    fun getMovieList(@Query("api_key") api_key: String, @Query("with_genres") with_genre: String) : Call<MovieData>
}