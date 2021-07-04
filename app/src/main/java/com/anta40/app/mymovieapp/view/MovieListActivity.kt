package com.anta40.app.mymovieapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anta40.app.mymovieapp.R
import com.anta40.app.mymovieapp.adapter.GenreAdapter
import com.anta40.app.mymovieapp.adapter.MovieListAdapter
import com.anta40.app.mymovieapp.model.Movie
import com.anta40.app.mymovieapp.util.MovieListClickListener
import com.anta40.app.mymovieapp.viewmodel.MainActivityViewModel
import com.anta40.app.mymovieapp.viewmodel.MovieListActivityViewModel

class MovieListActivity : AppCompatActivity(), MovieListClickListener {

    private val vm: MovieListActivityViewModel by lazy { MovieListActivityViewModel() }
    private lateinit var recview_movie_list: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)

        recview_movie_list = findViewById(R.id.recview_movie_list) as RecyclerView

        vm.getData(resources.getString(R.string.API_KEY), intent.getIntExtra("movie_genre", 0).toString())

        observe()
    }

    private fun observe() {
        vm.movieLiveData.observe(this) {
            if (it.movies.isEmpty()){
                Toast.makeText(applicationContext, "Data kosong", Toast.LENGTH_SHORT).show()
            }
            else  {
                val movie_list_adapter = MovieListAdapter(applicationContext, it.movies, this)
                recview_movie_list.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
                recview_movie_list.adapter = movie_list_adapter

            }
        }
    }

    override fun onRowClickListener(data: Movie) {
        val iii = Intent(this@MovieListActivity, MovieDetailActivity::class.java)
        iii.putExtra("movie_id", data.id)
        iii.putExtra("movie_title", data.original_title)
        iii.putExtra("movie_release_date", data.release_date)
        iii.putExtra("movie_overview", data.overview)
        startActivity(iii)
    }
}