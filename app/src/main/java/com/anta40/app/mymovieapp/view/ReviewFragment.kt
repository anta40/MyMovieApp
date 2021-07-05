package com.anta40.app.mymovieapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anta40.app.mymovieapp.R
import com.anta40.app.mymovieapp.adapter.GenreAdapter
import com.anta40.app.mymovieapp.adapter.ReviewAdapter
import com.anta40.app.mymovieapp.viewmodel.MovieDetailActivityViewModel

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ReviewFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null
    private lateinit var recview_review: RecyclerView
    private val vm: MovieDetailActivityViewModel by lazy { MovieDetailActivityViewModel() }

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

        return inflater.inflate(R.layout.fragment_review, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recview_review = view.findViewById(R.id.recview_review) as RecyclerView
        val args = arguments
        val movie_id = args!!.getInt("movie_id")

        vm.getTrailerData(movie_id, resources.getString(R.string.API_KEY))

        super.onViewCreated(view, savedInstanceState)
    }

    private fun observe() {
        vm.reviewLiveData.observe(this) {
            if (it.reviews.isEmpty()){
                Toast.makeText(activity?.applicationContext, "Data kosong", Toast.LENGTH_SHORT).show()
            }
            else  {
                val review_adapter = ReviewAdapter(it.reviews)
                recview_review.layoutManager = LinearLayoutManager(activity?.applicationContext,
                    LinearLayoutManager.VERTICAL, false)
                recview_review.adapter = review_adapter
            }
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(movie_id: Int) =
            ReviewFragment().apply {
                arguments = Bundle().apply {
                    putInt("movie_id", 0)
                }
            }
    }
}