package com.example.photosandvideosapp.presentation.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.webkit.URLUtil;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;
import com.example.photosandvideosapp.R


class VideoPlayerActivity : AppCompatActivity() {

    private var mVideoView: VideoView? = null
    private var mBufferingTextView: TextView? = null
    private var mCurrentPosition = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_player)
        mVideoView = findViewById(R.id.videoview)
        mBufferingTextView = findViewById(R.id.buffering_textview)
        if (savedInstanceState != null) {
            mCurrentPosition = savedInstanceState.getInt(PLAYBACK_TIME)
        }
        val url = intent?.getStringExtra(VIDEO_SAMPLE) ?: return
        VIDEO_SAMPLE = url;
        val controller = MediaController(this)
        controller.setMediaPlayer(mVideoView)
        mVideoView?.setMediaController(controller)
    }

    override fun onStart() {
        super.onStart()
        initializePlayer()
    }

    override fun onPause() {
        super.onPause()
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            mVideoView!!.pause()
        }
    }

    override fun onStop() {
        super.onStop()
        releasePlayer()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(PLAYBACK_TIME, mVideoView!!.currentPosition)
    }

    private fun initializePlayer() {
        mBufferingTextView!!.visibility = VideoView.VISIBLE

        val videoUri: Uri = getMedia(VIDEO_SAMPLE)
        mVideoView!!.setVideoURI(videoUri)

        mVideoView!!.setOnPreparedListener { // Hide buffering message.
            mBufferingTextView!!.visibility = VideoView.INVISIBLE

            if (mCurrentPosition > 0) {
                mVideoView!!.seekTo(mCurrentPosition)
            } else {
                mVideoView!!.seekTo(1)
            }

            mVideoView!!.start()
        }

        mVideoView!!.setOnCompletionListener {
            Toast.makeText(
                this@VideoPlayerActivity,
                R.string.app_name,
                Toast.LENGTH_SHORT
            ).show()

            mVideoView!!.seekTo(0)
        }
    }


    private fun releasePlayer() {
        mVideoView!!.stopPlayback()
    }

    private fun getMedia(mediaName: String): Uri {
        return if (URLUtil.isValidUrl(mediaName)) {
            Uri.parse(mediaName)
        } else {
            Uri.parse(
                "android.resource://" + packageName +
                        "/raw/" + mediaName
            )
        }
    }

    companion object {
        private const val PLAYBACK_TIME = "play_time"
        var VIDEO_SAMPLE = "VIDEO_URL"
        fun getStartIntent(activity: Context?, articleUrl: String): Intent {
            return Intent(activity, VideoPlayerActivity::class.java).apply {
                putExtra(VIDEO_SAMPLE, articleUrl)
            }
        }
    }

}