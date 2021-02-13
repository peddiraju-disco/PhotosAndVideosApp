package com.example.photosandvideosapp.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.photosandvideosapp.R
import com.example.photosandvideosapp.databinding.FragmentVideoBinding
import com.example.photosandvideosapp.models.PhotoData
import com.example.photosandvideosapp.presentation.activities.ImageDetailActivity
import com.example.photosandvideosapp.presentation.activities.VideoPlayerActivity
import com.example.photosandvideosapp.presentation.adapters.AdapterEventListener
import com.example.photosandvideosapp.presentation.adapters.PhotosAdapter
import com.example.photosandvideosapp.viewmodels.MainViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class VideoFragment : Fragment(R.layout.fragment_video) {
    private var fragmentFirstBinding: FragmentVideoBinding? = null
    private val mainViewModel: MainViewModel by viewModel()
    private lateinit var photosAdapter: PhotosAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentVideoBinding.bind(view)
        fragmentFirstBinding = binding
        initialize()
        observeData()
        getData()
    }

    private fun initialize() {
        photosAdapter = PhotosAdapter(getAdapterClickListener())
        fragmentFirstBinding?.apply {
            recyclerVideo.apply {
                isNestedScrollingEnabled = false
                layoutManager = LinearLayoutManager(activity)
                adapter = photosAdapter
            }
        }
    }

    private fun observeData() {
        mainViewModel.apply {
            activity?.let {
                videosData.observe(it, Observer {
                    if (it != null)
                        photosAdapter.setData(it)
                })
            }
        }
    }

    private fun getAdapterClickListener(): AdapterEventListener {
        return object : AdapterEventListener {
            override fun onPhotoClickListener(photoData: PhotoData) {
                startActivity(
                    VideoPlayerActivity.getStartIntent(
                        activity = context,
                        articleUrl = photoData.video_files?.get(0)?.link?: ""
                    )
                )
            }

            override fun onPhotoLikeListener(position: Int, artiphotocle: PhotoData) {
                saveItem(position, artiphotocle)
            }

            override fun onPhotoUnfavoriteListener(position: Int, artiphotocle: PhotoData) {
                deleteItem(position, artiphotocle)
            }
        }
    }

    private fun saveItem(position: Int, photoData: PhotoData) {
        photoData.isBookmark = true
        mainViewModel.saveFavorite(photoData)
        photosAdapter.notifyItemChanged(position, photoData)
    }

    private fun deleteItem(position: Int, photoData: PhotoData) {
        photoData.isBookmark = false
        mainViewModel.deleteFavorite(photoData)
        photosAdapter.notifyItemChanged(position, photoData)
    }

    private fun getData() {
        mainViewModel.getVideos()
    }


    override fun onDestroyView() {
        fragmentFirstBinding = null
        super.onDestroyView()
    }
}