package com.parser.moviedb.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.parser.moviedb.presentation.R
import com.parser.moviedb.presentation.viewmodels.MovieDetailViewModel

class MovieDetailFragment : Fragment() {
    private val viewModel: MovieDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_detail, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() = MovieDetailFragment()
    }
}