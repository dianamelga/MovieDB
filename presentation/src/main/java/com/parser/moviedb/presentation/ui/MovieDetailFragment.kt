package com.parser.moviedb.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.parser.moviedb.domain.entities.MediaItem
import com.parser.moviedb.presentation.databinding.FragmentMovieDetailBinding
import com.parser.moviedb.presentation.ui.base.BaseFragment
import com.parser.moviedb.presentation.viewmodels.MainViewModel
import com.parser.moviedb.presentation.viewmodels.MovieDetailViewModel
import com.parser.moviedb.presentation.viewmodels.MovieDetailViewState

class MovieDetailFragment : BaseFragment() {
    private lateinit var binding: FragmentMovieDetailBinding
    private lateinit var mediaItem: MediaItem
    private val rootViewModel: MainViewModel by activityViewModels()
    private val viewModel: MovieDetailViewModel by viewModels()
    private var youtubeId = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        (arguments?.getParcelable(MEDIA_ITEM) as? MediaItem)?.let {
            mediaItem = it
        }
        binding.mediaItem = mediaItem
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.clBackTouchArea.setOnClickListener {
            requireActivity().onBackPressed()
        }

        binding.btnWatchTrailer.isVisible = false
        binding.btnWatchTrailer.setOnClickListener {
            rootViewModel.openVideoPlayer(youtubeId)
        }

        viewModel.getMovieVideos(mediaItem.id, mediaItem.originalLanguage)

        viewModel.viewState.observe(viewLifecycleOwner) {
            when(it) {
                is MovieDetailViewState.GetMovieVideos.Failure -> {
                    // TODO: show error state
                }
                MovieDetailViewState.GetMovieVideos.Processing -> {
                    // TODO: show loading state
                }
                is MovieDetailViewState.GetMovieVideos.Success -> {
                    youtubeId = it.youtubeId
                    binding.btnWatchTrailer.isVisible = true
                }
            }
        }
    }

    companion object {
        val TAG = MovieDetailFragment::class.java.simpleName
        const val MEDIA_ITEM = "media_item"
        @JvmStatic
        fun newInstance() = MovieDetailFragment()
    }
}