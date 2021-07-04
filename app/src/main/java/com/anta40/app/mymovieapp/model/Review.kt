package com.anta40.app.mymovieapp.model

import com.google.gson.annotations.SerializedName

data class Review(
    @SerializedName("author") val author: String,
    @SerializedName("content") val content: String,
    @SerializedName("created_at") val created_at: String,
)

data class ReviewData (
    @SerializedName("results") val reviews: List<Review>
)
