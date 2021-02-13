package com.example.photosandvideosapp.presentation.activities

import android.os.Bundle
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Observer
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.bumptech.glide.Glide
import com.example.photosandvideosapp.R
import com.example.photosandvideosapp.databinding.ActivityMainBinding
import com.example.photosandvideosapp.models.PhotoData
import com.example.photosandvideosapp.presentation.adapters.CustomViewPager
import com.example.photosandvideosapp.presentation.fragments.FavoriteFragment
import com.example.photosandvideosapp.presentation.fragments.PhotoFragment
import com.example.photosandvideosapp.presentation.fragments.VideoFragment
import com.example.photosandvideosapp.viewmodels.MainViewModel
import com.google.android.material.tabs.TabLayout
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initialize()
        observeData()
        getData()
    }

    private fun initialize() {
        val tab_viewpager = findViewById<CustomViewPager>(R.id.tab_viewpager)
        val tab_tablayout = findViewById<TabLayout>(R.id.tab_tablayout)
        setupViewPager(tab_viewpager)
        tab_tablayout.setupWithViewPager(tab_viewpager)
    }

    private fun observeData() {
        mainViewModel.apply {
            banersData.observe(this@MainActivity, Observer {
                val photoDatas: MutableList<PhotoData> = it.toMutableList()
                Glide.with(binding.headerImage).load(photoDatas[0].srcdata?.large2x)
                    .into(binding.headerImage)

            })
        }
    }

    private fun getData() {
        mainViewModel.getBanner()
    }


    private fun setupViewPager(viewpager: ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager)

        adapter.addFragment(PhotoFragment(), "Photo")
        adapter.addFragment(VideoFragment(), "Video")
        adapter.addFragment(FavoriteFragment(), "Favorites")

        // setting adapter to view pager.
        viewpager.setAdapter(adapter)

        viewpager.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                viewpager.getAdapter()?.notifyDataSetChanged()
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
    }


    class ViewPagerAdapter : FragmentPagerAdapter {

        private final var fragmentList1: ArrayList<Fragment> = ArrayList()
        private final var fragmentTitleList1: ArrayList<String> = ArrayList()


        constructor(supportFragmentManager: FragmentManager)
                : super(supportFragmentManager)

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

        override fun getItemPosition(`object`: Any): Int {
            return POSITION_NONE;
        }

        private var currentPosition = -1

        override fun setPrimaryItem(container: ViewGroup, position: Int, `object`: Any) {
            super.setPrimaryItem(container, position, `object`)
            if (position != currentPosition) {
                val fragment = `object` as Fragment
                val pager = container as CustomViewPager
                if (fragment.view != null) {
                    currentPosition = position
                    pager.measureCurrentView(fragment.view)
                }
            }
        }

    }
}

