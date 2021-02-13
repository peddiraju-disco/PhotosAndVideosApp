package com.example.photosandvideosapp.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.photosandvideosapp.R
import com.example.photosandvideosapp.constant.pageNo
import com.example.photosandvideosapp.databinding.FragmentPhotoBinding
import com.example.photosandvideosapp.models.PaginationScrollListener
import com.example.photosandvideosapp.models.PhotoData
import com.example.photosandvideosapp.presentation.activities.ImageDetailActivity
import com.example.photosandvideosapp.presentation.adapters.AdapterEventListener
import com.example.photosandvideosapp.presentation.adapters.PhotosAdapter
import com.example.photosandvideosapp.viewmodels.MainViewModel
import org.koin.android.viewmodel.ext.android.viewModel


class PhotoFragment : Fragment(R.layout.fragment_photo) {

    private var fragmentFirstBinding: FragmentPhotoBinding? = null
    private val mainViewModel: MainViewModel by viewModel()
    private lateinit var photosAdapter: PhotosAdapter
    var isLastPage: Boolean = false
    var isLoading: Boolean = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentPhotoBinding.bind(view)
        fragmentFirstBinding = binding
        initialize()
        observeData()
        getData()
    }

    private fun initialize() {
        photosAdapter = PhotosAdapter(getAdapterClickListener())
        fragmentFirstBinding?.apply {
            recyclerPhoto.apply {
                isNestedScrollingEnabled = false
                layoutManager = LinearLayoutManager(activity)
                adapter = photosAdapter
            }
        }

        /*fragmentFirstBinding?.recyclerPhoto?.addOnScrollListener(object :
            PaginationScrollListener(fragmentFirstBinding?.recyclerPhoto?.layoutManager as LinearLayoutManager) {
            override fun isLastPage(): Boolean {
                return isLastPage
            }

            override fun isLoading(): Boolean {
                return isLoading
            }

            override fun loadMoreItems() {
                isLoading = true
                //you have to call loadmore items to get more data
                pageNo += 1
                getData()
            }
        })*/
    }

    private fun observeData() {
        mainViewModel.apply {
            activity?.let {
                photosData.observe(it, Observer {
                    if (pageNo > 1) {
                        photosAdapter.addMoreData(it)
                    } else {
                        photosAdapter.setData(it)
                    }
                })
            }
        }
    }

    private fun getAdapterClickListener(): AdapterEventListener {
        return object : AdapterEventListener {
            override fun onPhotoClickListener(photoData: PhotoData) {
                startActivity(
                    ImageDetailActivity.getStartIntent(
                        activity = context,
                        articleUrl = photoData.srcdata?.large2x ?: ""
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
        isLoading = false
        mainViewModel.getPhotos()
    }

    override fun onDestroyView() {
        fragmentFirstBinding = null
        super.onDestroyView()
    }

}