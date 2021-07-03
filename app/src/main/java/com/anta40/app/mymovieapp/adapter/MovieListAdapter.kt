package com.anta40.app.mymovieapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.anta40.app.mymovieapp.R
import com.anta40.app.mymovieapp.model.Movie
import com.anta40.app.mymovieapp.util.MovieListClickListener
import com.bumptech.glide.Glide

class MovieListAdapter (private val context: Context, private val dataSet: List<Movie>, private val clickListener: MovieListClickListener ):
    RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtMovieTitle: TextView
        val imgMovie: ImageView
        val txtMovieReleaseDate: TextView
        val txtMoviePopularity: TextView

        init {
            txtMovieTitle =  view.findViewById(R.id.movie_title)
            imgMovie = view.findViewById(R.id.movie_backdrop)
            txtMoviePopularity = view.findViewById(R.id.movie_popularity)
            txtMovieReleaseDate = view.findViewById(R.id.movie_release_date)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieListAdapter.ViewHolder, position: Int) {
        holder.txtMovieTitle.text = dataSet[position].original_title
        holder.txtMovieReleaseDate.text = "Release date: " + dataSet[position].release_date
        holder.txtMoviePopularity.text = "Popularity: "+ dataSet[position].popularity.toString()

        val IMG_BASE_PATH = "https://image.tmdb.org/t/p/w500"

        Glide.with(context)
            .load(IMG_BASE_PATH + dataSet[position].backdrop_path)
            .override(300, 200)
            .into(holder.imgMovie);

        holder.itemView.setOnClickListener {
            clickListener.onRowClickListener(dataSet[position])
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

}