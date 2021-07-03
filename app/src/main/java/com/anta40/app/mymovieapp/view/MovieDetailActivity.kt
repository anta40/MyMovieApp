package com.anta40.app.mymovieapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.anta40.app.mymovieapp.R
import android.widget.TabHost
import android.widget.TabHost.TabSpec
import androidx.annotation.Nullable
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout


class MovieDetailActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        var tab_toolbar = findViewById<Toolbar>(R.id.toolbar)
        var tab_viewpager = findViewById<ViewPager>(R.id.tab_viewpager)
        var tab_tablayout = findViewById<TabLayout>(R.id.tab_tablayout)

       // setSupportActionBar(tab_toolbar)
        setupViewPager(tab_viewpager)

        tab_tablayout.setupWithViewPager(tab_viewpager)

    }

    private fun setupViewPager(viewpager: ViewPager) {
        var adapter: ViewPagerAdapter = ViewPagerAdapter(supportFragmentManager)

        // LoginFragment is the name of Fragment and the Login
        // is a title of tab
        adapter.addFragment(InfoFragment(), "Info")
        adapter.addFragment(TrailerFragment(), "Trailer")
        adapter.addFragment(ReviewFragment(), "Review")

        // setting adapter to view pager.
        viewpager.setAdapter(adapter)
    }


    class ViewPagerAdapter : FragmentPagerAdapter {

        // objects of arraylist. One is of Fragment type and
        // another one is of String type.*/
        private final var fragmentList1: ArrayList<Fragment> = ArrayList()
        private final var fragmentTitleList1: ArrayList<String> = ArrayList()

        // this is a secondary constructor of ViewPagerAdapter class.
        public constructor(supportFragmentManager: FragmentManager)
                : super(supportFragmentManager)

        // returns which item is selected from arraylist of fragments.
        override fun getItem(position: Int): Fragment {
            return fragmentList1.get(position)
        }

        // returns which item is selected from arraylist of titles.
        @Nullable
        override fun getPageTitle(position: Int): CharSequence {
            return fragmentTitleList1.get(position)
        }

        // returns the number of items present in arraylist.
        override fun getCount(): Int {
            return fragmentList1.size
        }

        // this function adds the fragment and title in 2 separate  arraylist.
        fun addFragment(fragment: Fragment, title: String) {
            fragmentList1.add(fragment)
            fragmentTitleList1.add(title)
        }
    }
}