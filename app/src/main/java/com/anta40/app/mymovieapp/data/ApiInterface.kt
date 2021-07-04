package com.anta40.app.mymovieapp.data

import com.anta40.app.mymovieapp.model.GenreData
import com.anta40.app.mymovieapp.model.MovieData
import com.anta40.app.mymovieapp.model.ReviewData
import com.anta40.app.mymovieapp.model.TrailerData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @GET("genre/movie/list")
    fun getGenreList(@Query("api_key") api_key: String) : Call<GenreData>

    @GET("discover/movie")
    fun getMovieList(@Query("api_key") api_key: String, @Query("with_genres") with_genre: String) : Call<MovieData>

    @GET("movie/{movie_id}/videos")
    fun getTrailerList(@Path("movie_id") movie_id: Int, @Query("api_key") api_key: String): Call<TrailerData>

    @GET("movie/{movie_id}/reviews")
    fun getReviewList(@Path("movie_id") movie_id: Int, @Query("api_key") api_key: String): Call<ReviewData>
}