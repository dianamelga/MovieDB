package com.parser.moviedb.presentation.ui

import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.parser.moviedb.presentation.BuildConfig
import com.parser.moviedb.presentation.R
import com.parser.moviedb.presentation.databinding.FragmentVideoPlayerBinding

class VideoPlayerActivity : YouTubeBaseActivity() {
    private lateinit var binding: FragmentVideoPlayerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.fragment_video_player)

        val youtubeId = intent?.extras?.getString(YOUTUBE_ID) ?: ""

        binding.ytPlayer.initialize(BuildConfig.YOUTUBE_API_KEY, object : YouTubePlayer.OnInitializedListener {
            override fun onInitializationSuccess(
                p0: YouTubePlayer.Provider?,
                p1: YouTubePlayer?,
                wasRestored: Boolean
            ) {
                if (!wasRestored) {
                    // p1?.cueVideo(youtubeId)
                    p1?.cueVideo("d9MyW72ELq0") // harcoded because the api doesn't return any valid id
                }
            }

            override fun onInitializationFailure(
                p0: YouTubePlayer.Provider?,
                p1: YouTubeInitializationResult?
            ) {
                Log.e(TAG, "error: ${p1?.name}")
            }
        })
    }

    companion object {
        val TAG = VideoPlayerActivity::class.java.simpleName
        const val YOUTUBE_ID = "youtube_id"
    }
}