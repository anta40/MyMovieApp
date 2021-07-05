package com.anta40.app.mymovieapp.view

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
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

        recview_genre = findViewById(R.id.recview_genre) as RecyclerView

        if (isNetworkAvailable(applicationContext)){
            Toast.makeText(applicationContext, "Koneksi internet tidak ada...", Toast.LENGTH_SHORT).show()
        }
        else {
            vm.getData(resources.getString(R.string.API_KEY))
            observe()
        }
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

    fun isNetworkAvailable(context: Context?): Boolean {
        if (context == null) return false
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        return true
                    }
                }
            }
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                return true
            }
        }
        return false
    }

}