package com.anta40.app.mymovieapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.anta40.app.mymovieapp.R
import android.widget.TabHost
import android.widget.TabHost.TabSpec
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.anta40.app.mymovieapp.adapter.GenreAdapter
import com.anta40.app.mymovieapp.viewmodel.MainActivityViewModel
import com.anta40.app.mymovieapp.viewmodel.MovieDetailActivityViewModel
import com.google.android.material.tabs.TabLayout


class MovieDetailActivity : AppCompatActivity() {

    private val vm: MovieDetailActivityViewModel by lazy { MovieDetailActivityViewModel() }
    private lateinit var youtube_code: String

    private lateinit var tab_toolbar: Toolbar
    private lateinit var tab_viewpager: ViewPager
    private lateinit var tab_tablayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        tab_toolbar = findViewById<Toolbar>(R.id.toolbar)
        tab_viewpager = findViewById<ViewPager>(R.id.tab_viewpager)
        tab_tablayout = findViewById<TabLayout>(R.id.tab_tablayout)

        youtube_code = ""


        vm.getTrailerData(intent.getIntExtra("movie_id", 0), resources.getString(R.string.API_KEY))

        observe()
    }

    private fun observe() {
        vm.trailerLiveData.observe(this) {
            if (it.trailers.isEmpty()){
                Toast.makeText(applicationContext, "Data kosong", Toast.LENGTH_SHORT).show()
            }
            else  {
                youtube_code = it.trailers.get(0).key
                setupViewPager(tab_viewpager)
                tab_tablayout.setupWithViewPager(tab_viewpager)
            }
        }
    }

    private fun setupViewPager(viewpager: ViewPager) {
        var adapter: ViewPagerAdapter = ViewPagerAdapter(supportFragmentManager)

        adapter.addFragment(InfoFragment(), "Info")


        val tf = TrailerFragment()
        val trailer_args = Bundle()
        trailer_args.putString("youtube_code", youtube_code)
        trailer_args.putString("img_url", intent?.getStringExtra("movie_backdrop"))
        tf.arguments = trailer_args
        adapter.addFragment(tf, "Trailer")

        val rf = ReviewFragment()
        val review_args = Bundle()
        review_args.putInt("movie_id", intent.getIntExtra("movie_id", 0))
        rf.arguments = review_args
        adapter.addFragment(rf, "Review")

        viewpager.setAdapter(adapter)
    }


    class ViewPagerAdapter : FragmentPagerAdapter {

        private final var fragmentList1: ArrayList<Fragment> = ArrayList()
        private final var fragmentTitleList1: ArrayList<String> = ArrayList()

        public constructor(supportFragmentManager: FragmentManager)
                : super(supportFragmentManager)

        override fun getItem(position: Int): Fragment {
            return fragmentList1.get(position)
        }

        @Nullable
        override fun getPageTitle(position: Int): CharSequence {
            return fragmentTitleList1.get(position)
        }

        override fun getCount(): Int {
            return fragmentList1.size
        }

        fun addFragment(fragment: Fragment, title: String) {
            fragmentList1.add(fragment)
            fragmentTitleList1.add(title)
        }
    }
}