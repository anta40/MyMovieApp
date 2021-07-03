package com.anta40.app.mymovieapp.util

import com.anta40.app.mymovieapp.model.Movie

interface MovieListClickListener {
    fun onRowClickListener(data: Movie)
}