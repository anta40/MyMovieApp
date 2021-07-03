package com.anta40.app.mymovieapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anta40.app.mymovieapp.R
import com.anta40.app.mymovieapp.adapter.GenreAdapter
import com.anta40.app.mymovieapp.model.Genre
import com.anta40.app.mymovieapp.util.GenreClickListener
import com.anta40.app.mymovieapp.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity(), GenreClickListener {

    private val vm: MainActivityViewModel by lazy { MainActivityViewModel() }
    private lateinit var recview_genre: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        vm.getData(resources.getString(R.string.API_KEY))
        recview_genre = findViewById(R.id.recview_genre) as RecyclerView

        observe()

    }

    private fun observe() {
        vm.genreLiveData.observe(this) {
            if (it.genres.isEmpty()){
                Toast.makeText(applicationContext, "Data kosong", Toast.LENGTH_SHORT).show()
            }
            else  {
                val genre_adapter = GenreAdapter(applicationContext, it.genres, this)
                recview_genre.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
                recview_genre.adapter = genre_adapter
            }
        }
    }

    override fun onRowClickListener(data: Genre) {
        val intent = Intent(this@MainActivity, MovieListActivity::class.java )
        intent.putExtra("movie_genre", data.id)
        startActivity(intent)
    }

}