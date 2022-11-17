package com.parser.moviedb.presentation.ui.custom

import android.content.Context
import android.net.Uri
import android.os.Handler
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.google.android.exoplayer2.C
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.PlaybackException
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.Player.REPEAT_MODE_ALL
import com.google.android.exoplayer2.Player.REPEAT_MODE_OFF
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.source.dash.DashMediaSource
import com.google.android.exoplayer2.source.hls.HlsMediaSource
import com.google.android.exoplayer2.source.rtsp.RtspMediaSource
import com.google.android.exoplayer2.source.smoothstreaming.SsMediaSource
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource
import com.google.android.exoplayer2.util.Util.getUserAgent
import com.google.android.exoplayer2.util.Util.inferContentType
import com.parser.moviedb.presentation.databinding.PlayerViewBinding

class MovieVideoPlayer @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    FrameLayout(context, attrs, defStyleAttr), Player.Listener, DefaultLifecycleObserver {

    var callback: SpeedeVideoPlayerCallback? = null
    val exoPlayer: ExoPlayer = ExoPlayer.Builder(context).build()
    var playInLoop = false

    private lateinit var timeRunnable: Runnable
    private var timeHandler: Handler? = null
    private val userAgent = getUserAgent(context, "MovieDB")
    private var pausedByUser = false

    init {
        exoPlayer.addListener(this)

        val playerViewBinding = PlayerViewBinding.inflate(LayoutInflater.from(context), this, false)
        playerViewBinding.exoPlayerView.player = exoPlayer
        addView(playerViewBinding.root)

        // loop in charge to update the position  of the video
        timeRunnable = Runnable {
            if (isPlaying()) {
                val position = this.exoPlayer.currentPosition / 1000
                callback?.onTimeChanged(position.toInt())
            }
            timeHandler?.postDelayed(timeRunnable, 500)
        }

        exoPlayer.addListener(object : Player.Listener {
            override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
                if (playWhenReady && playbackState == Player.STATE_READY) {
                    callback?.onDurationSettled(exoPlayer.duration.div(1000).toInt())
                    if (timeHandler == null) {
                        timeHandler = Handler()
                        timeHandler?.postDelayed(timeRunnable, 500)
                    }
                }
                if (playbackState == Player.STATE_ENDED) {
                    callback?.onVideoEnded()
                }
            }
        }
        )
    }

    fun prepare(uri: Uri, volume: Float = 0F) {

        val dataSourceFactory = DefaultHttpDataSource.Factory()
            .setUserAgent(userAgent)
            .setConnectTimeoutMs(CONNECT_TIME_OUT)
            .setReadTimeoutMs(READ_TIME_OUT)
            .setAllowCrossProtocolRedirects(true)

        val mediaItem: MediaItem = MediaItem.fromUri(uri)

        val mediaSource = when (inferContentType(uri)) {
            C.TYPE_DASH -> {
                DashMediaSource.Factory(dataSourceFactory)
            }
            C.TYPE_HLS -> {
                HlsMediaSource.Factory(dataSourceFactory)
            }
            C.TYPE_OTHER -> {
                ProgressiveMediaSource.Factory(dataSourceFactory)
            }
            C.TYPE_RTSP -> {
                RtspMediaSource.Factory()
            }
            C.TYPE_SS -> {
                SsMediaSource.Factory(dataSourceFactory)
            }
            else -> throw Exception("Unknown source")
        }.createMediaSource(mediaItem)

        exoPlayer.playWhenReady = false
        exoPlayer.setMediaSource(mediaSource)
        exoPlayer.volume = volume
        exoPlayer.prepare()
    }

    fun loadFileStorage(uri: Uri, volume: Float) {
        playInLoop = true
        exoPlayer.volume = volume
        exoPlayer.repeatMode = if (playInLoop) REPEAT_MODE_ALL else REPEAT_MODE_OFF
        exoPlayer.setMediaItem(MediaItem.fromUri(uri))
        exoPlayer.prepare()
    }

    fun play() {
        exoPlayer.repeatMode = if (playInLoop) REPEAT_MODE_ALL else REPEAT_MODE_OFF
        exoPlayer.playWhenReady = true
        pausedByUser = false
    }

    fun pause() {
        pausedByUser = true
        exoPlayer.playWhenReady = false
    }

    fun isPlaying(): Boolean = exoPlayer.isPlaying

    fun isPaused(): Boolean = pausedByUser

    fun release() {
        exoPlayer.release()
        timeHandler = null
    }

    fun stop() {
        exoPlayer.stop()
    }

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        play()
    }

    override fun onPause(owner: LifecycleOwner) {
        pause()
        super.onPause(owner)
    }

    override fun onStop(owner: LifecycleOwner) {
        stop()
        super.onStop(owner)
    }

    override fun onDestroy(owner: LifecycleOwner) {
        release()
        super.onDestroy(owner)
    }

    override fun onPlayerError(error: PlaybackException) {
        super.onPlayerError(error)
        Log.e("ExoPlayer", "Error: ", error)
    }

    companion object {
        private const val CONNECT_TIME_OUT = 10000
        private const val READ_TIME_OUT = 10000
    }
}

interface SpeedeVideoPlayerCallback {
    fun onTimeChanged(position: Int)
    fun onDurationSettled(duration: Int)
    fun onVideoEnded()
}
