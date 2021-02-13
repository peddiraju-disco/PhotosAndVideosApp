package com.example.photosandvideosapp.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.photosandvideosapp.R
import com.example.photosandvideosapp.databinding.FragmentFavoriteBinding
import com.example.photosandvideosapp.models.PhotoData
import com.example.photosandvideosapp.presentation.adapters.AdapterEventListener
import com.example.photosandvideosapp.presentation.adapters.PhotosAdapter
import com.example.photosandvideosapp.viewmodels.FavoriteViewModel
import org.koin.android.viewmodel.ext.android.viewModel


class FavoriteFragment : Fragment(R.layout.fragment_favorite) {

    private var fragmentFirstBinding: FragmentFavoriteBinding? = null
    private val favoriteViewModel: FavoriteViewModel by viewModel()
    private lateinit var photosAdapter: PhotosAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentFavoriteBinding.bind(view)
        fragmentFirstBinding = binding
        initialize()
        observeData()
        getData()
    }

    private fun initialize() {
        photosAdapter = PhotosAdapter(getAdapterClickListener())
        fragmentFirstBinding?.apply {
            recyclerFavorite.apply {
                isNestedScrollingEnabled = false
                layoutManager = LinearLayoutManager(activity)
                adapter = photosAdapter
            }
        }
    }

    private fun observeData() {
        favoriteViewModel.apply {
            activity?.let {
                favoriteItemsLive.observe(it, Observer {
                    photosAdapter.setData(it)
                })
            }
        }
    }

    private fun getAdapterClickListener(): AdapterEventListener {
        return object : AdapterEventListener {
            override fun onPhotoClickListener(photoData: PhotoData) {
            }

            override fun onPhotoLikeListener(position: Int, artiphotocle: PhotoData) {
            }

            override fun onPhotoUnfavoriteListener(position: Int, artiphotocle: PhotoData) {
                deleteItem(position, artiphotocle)
            }
        }
    }


    private fun deleteItem(position: Int, photoData: PhotoData) {
        photoData.isBookmark = false
        favoriteViewModel.deleteItem(photoData)
        photosAdapter.removeItem(position)
    }

    private fun getData() {
        favoriteViewModel.loadFavorite()
    }


    override fun onDestroyView() {
        fragmentFirstBinding = null
        super.onDestroyView()
    }

}