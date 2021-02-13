package com.example.photosandvideosapp.presentation.activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.photosandvideosapp.R
import com.example.photosandvideosapp.databinding.ActivityImageDetailBinding

class ImageDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityImageDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImageDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        initialize()
        loadDetails()
    }

    private fun initialize() {
        binding.apply {
            ivBack.setOnClickListener { finish() }
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun loadDetails() {
        val url = intent?.getStringExtra(PHOTO_URL) ?: return
        Glide.with(binding.photoId).load(url)
            .into(binding.photoId)

    }

    companion object {
        const val PHOTO_URL = "PHOTO_URL"
        fun getStartIntent(activity: Context?, articleUrl: String): Intent {
            return Intent(activity, ImageDetailActivity::class.java).apply {
                putExtra(PHOTO_URL, articleUrl)
            }
        }
    }
}