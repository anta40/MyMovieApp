package com.anta40.app.mymovieapp.util

import com.anta40.app.mymovieapp.model.Genre

interface GenreClickListener {
    fun onRowClickListener(data: Genre)
}