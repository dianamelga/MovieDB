package com.parser.moviedb.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.parser.moviedb.presentation.R
import com.parser.moviedb.presentation.databinding.FragmentMovieDetailBinding
import com.parser.moviedb.presentation.viewmodels.MovieDetailViewModel

class MovieDetailFragment : Fragment() {
    private val viewModel: MovieDetailViewModel by viewModels()
    private lateinit var binding: FragmentMovieDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = MovieDetailFragment()
    }
}