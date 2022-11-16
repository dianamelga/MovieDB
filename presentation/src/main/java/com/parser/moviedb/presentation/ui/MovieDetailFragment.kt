package com.parser.moviedb.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.parser.moviedb.domain.entities.MediaItem
import com.parser.moviedb.presentation.R
import com.parser.moviedb.presentation.databinding.FragmentMovieDetailBinding
import com.parser.moviedb.presentation.ui.base.BaseFragment
import com.parser.moviedb.presentation.viewmodels.MovieDetailViewModel

class MovieDetailFragment : BaseFragment() {
    private val viewModel: MovieDetailViewModel by viewModels()
    private lateinit var binding: FragmentMovieDetailBinding
    private lateinit var mediaItem: MediaItem

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        (arguments?.getParcelable(MEDIA_ITEM) as? MediaItem)?.let {
            mediaItem = it
        }
        return binding.root
    }

    companion object {
        val TAG = MovieDetailFragment::class.java.simpleName
        const val MEDIA_ITEM = "media_item"
        @JvmStatic
        fun newInstance() = MovieDetailFragment()
    }
}