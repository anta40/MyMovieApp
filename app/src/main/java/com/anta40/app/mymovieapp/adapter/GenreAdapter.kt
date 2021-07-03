package com.anta40.app.mymovieapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.anta40.app.mymovieapp.R
import com.anta40.app.mymovieapp.model.Genre
import com.anta40.app.mymovieapp.util.GenreClickListener

class GenreAdapter(private val context: Context, private val dataSet: List<Genre>, private val clickListener: GenreClickListener) :
    RecyclerView.Adapter<GenreAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtGenre: TextView

        init {
            txtGenre = view.findViewById(R.id.txtGenre)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_genre, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtGenre.text = dataSet[position].name
        holder.itemView.setOnClickListener {
            clickListener.onRowClickListener(dataSet[position])
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}