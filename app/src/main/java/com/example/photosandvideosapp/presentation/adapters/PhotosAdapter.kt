package com.example.photosandvideosapp.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.photosandvideosapp.R
import com.example.photosandvideosapp.databinding.ItemPhotoBinding
import com.example.photosandvideosapp.models.PhotoData

class PhotosAdapter(
    private val adapterEventListener: AdapterEventListener
) : RecyclerView.Adapter<PhotosAdapter.PhotosViewHolder>() {

    private var photoDatas: MutableList<PhotoData> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosViewHolder {
        return PhotosViewHolder(
            ItemPhotoBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return photoDatas.size
    }

    override fun onBindViewHolder(holder: PhotosViewHolder, position: Int) {
        val photoItem = photoDatas[position]

        holder.itemPhotoBinding.apply {

            photoItem.isVideo = photoItem.srcdata == null
            if (photoItem.isVideo) {
                Glide.with(photoId).load(photoItem.image).into(photoId)
                userName.text = photoItem.user?.name
                playIcon.visibility = View.VISIBLE
            } else {
                Glide.with(photoId).load(photoItem.srcdata?.medium).into(photoId)
                userName.text = photoItem.photographer
                playIcon.visibility = View.GONE
            }

            if (photoItem.isBookmark) {
                imageFavourite.setImageResource(R.drawable.favorite_icon)
                root.setOnClickListener {
                    adapterEventListener.onPhotoClickListener(
                        photoItem
                    )
                }
                imageFavourite.setOnClickListener {
                    imageFavourite.setImageResource(R.drawable.unfavorite_icon)
                    adapterEventListener.onPhotoUnfavoriteListener(position, photoItem)
                }
            } else {
                imageFavourite.setImageResource(R.drawable.unfavorite_icon)
                root.setOnClickListener {
                    adapterEventListener.onPhotoClickListener(
                        photoItem
                    )
                }
                imageFavourite.setOnClickListener {
                    imageFavourite.setImageResource(R.drawable.favorite_icon)
                    adapterEventListener.onPhotoLikeListener(position, photoItem)
                }
            }
        }
    }

    fun setData(articles: List<PhotoData>) {
        photoDatas = articles.toMutableList()
        notifyDataSetChanged()
    }

    fun addMoreData(articles: List<PhotoData>) {
        var size = articles.toMutableList().size
        this.photoDatas.addAll(articles.toMutableList())
        var sizeNew = this.photoDatas.size
        notifyItemRangeChanged(size, sizeNew)
    }

    fun removeItem(position: Int) {
        if (position in photoDatas.indices) {
            photoDatas.removeAt(position)
            notifyItemRemoved(position)
            notifyDataSetChanged()
        }
    }


    inner class PhotosViewHolder(val itemPhotoBinding: ItemPhotoBinding) :
        RecyclerView.ViewHolder(itemPhotoBinding.root) {
    }
}