package com.anta40.app.mymovieapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.anta40.app.mymovieapp.R
import com.anta40.app.mymovieapp.model.Review

class ReviewAdapter(private val dataSet: List<Review>):
    RecyclerView.Adapter<ReviewAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtReviewAuthor: TextView
        val txtReviewContent: TextView
        val txtReviewCreated: TextView

        init {
            txtReviewAuthor =  view.findViewById(R.id.txt_review_author)
            txtReviewContent = view.findViewById(R.id.txt_review_content)
            txtReviewCreated = view.findViewById(R.id.txt_review_created)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_review, parent, false)

        return ReviewAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtReviewAuthor.text = dataSet[position].author
        holder.txtReviewContent.text = dataSet[position].content
        holder.txtReviewCreated.text = dataSet[position].created_at
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}