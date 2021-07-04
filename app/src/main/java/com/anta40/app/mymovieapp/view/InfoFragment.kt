package com.anta40.app.mymovieapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.anta40.app.mymovieapp.R
import com.bumptech.glide.Glide

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class InfoFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    private lateinit var txtMovieTitle: TextView
    private lateinit var txtMovieReleaseDate: TextView
    private lateinit var txtMovieDescription: TextView
    private lateinit var txtMovieRating: TextView
    private lateinit var imgMovie: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        txtMovieTitle = view.findViewById(R.id.movie_title) as TextView
        txtMovieReleaseDate = view.findViewById(R.id.movie_release_date) as TextView
        txtMovieDescription = view.findViewById(R.id.movie_description) as TextView
        txtMovieRating = view.findViewById(R.id.detail_tv_movie_rating) as TextView
        imgMovie = view.findViewById(R.id.detail_iv_poster) as ImageView

        txtMovieTitle.text = activity?.intent?.getStringExtra("movie_title")
        txtMovieReleaseDate.text = activity?.intent?.getStringExtra("movie_release_date")
        txtMovieDescription.text = activity?.intent?.getStringExtra("movie_overview")
        txtMovieRating.text = activity?.intent?.getFloatExtra("movie_rating", 0.00f).toString() + " / 10"

        val img_url = activity?.intent?.getStringExtra("movie_poster")

        activity?.let {
            Glide.with(it)
                .load("https://image.tmdb.org/t/p/w500/$img_url")
                .override(300, 300)
                .into(imgMovie)
        };

        super.onViewCreated(view, savedInstanceState)
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            InfoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}