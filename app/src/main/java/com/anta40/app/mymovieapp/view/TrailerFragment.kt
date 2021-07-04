package com.anta40.app.mymovieapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.anta40.app.mymovieapp.R
import android.content.Intent
import android.net.Uri
import com.bumptech.glide.Glide


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class TrailerFragment: Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    private lateinit var imgTrailer: ImageView

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

        return inflater.inflate(R.layout.fragment_trailer, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        imgTrailer = view.findViewById(R.id.img_trailer) as ImageView

        val args = arguments

        val img_url = args?.getString("img_url")

        activity?.let {
            Glide.with(it)
                .load("https://image.tmdb.org/t/p/w500/$img_url")
                .fitCenter()
                .placeholder(R.drawable.poster_noimage)
                .into(imgTrailer)
        };

        imgTrailer.setOnClickListener {

            val youtube_code =  args?.getString("youtube_code")
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=$youtube_code"));
            activity?.startActivity(intent)
        }

        super.onViewCreated(view, savedInstanceState)
    }


    companion object {

        @JvmStatic
        fun newInstance(youtube_code: String, img_url: String) =
            InfoFragment().apply {
                arguments = Bundle().apply {
                    putString("youtube_code", "")
                    putString("img_url", "")
                }
            }
    }
}