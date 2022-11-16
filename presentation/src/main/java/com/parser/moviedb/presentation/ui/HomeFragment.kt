package com.parser.moviedb.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.parser.moviedb.domain.entities.MediaItem
import com.parser.moviedb.presentation.R
import com.parser.moviedb.presentation.databinding.FragmentHomeBinding
import com.parser.moviedb.presentation.ui.adapters.MediaItemWrapper
import com.parser.moviedb.presentation.ui.adapters.MovieFilterAdapter
import com.parser.moviedb.presentation.ui.adapters.MovieFilterWrapper
import com.parser.moviedb.presentation.ui.adapters.MovieItemAdapter
import com.parser.moviedb.presentation.ui.base.BaseFragment
import com.parser.moviedb.presentation.viewmodels.HomeViewModel
import com.parser.moviedb.presentation.viewmodels.HomeViewState
import com.parser.moviedb.presentation.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

class HomeFragment : BaseFragment() {

    private val rootViewModel: MainViewModel by activityViewModels()
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding

    private val upcomingAdapter by lazy {
        MovieItemAdapter {
            rootViewModel.goToMovieDetail(it)
        }
    }
    private val topRatedAdapter by lazy {
        MovieItemAdapter {
            rootViewModel.goToMovieDetail(it)
        }
    }
    private val recommendedAdapter by lazy {
        MovieItemAdapter {
            rootViewModel.goToMovieDetail(it)
        }
    }

    private val movieFilterAdapter by lazy {
        MovieFilterAdapter(requireActivity()) { filter ->
            viewModel.filterRecommendedMovies(filter)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvUpcoming.adapter = upcomingAdapter
        binding.rvTopRated.adapter = topRatedAdapter
        binding.rvRecommended.adapter = recommendedAdapter
        binding.rvRecommendedFilters.adapter = movieFilterAdapter

        viewModel.viewState.observe(viewLifecycleOwner) {
            when(it) {
                is HomeViewState.LoadData.Failure -> {
                    // TODO: show error state
                }
                HomeViewState.LoadData.Processing -> {
                    loadingState()
                }
                is HomeViewState.LoadData.Success -> {
                    clearLoadingState()
                    upcomingAdapter.submitList(it.upcomingMovies.map { MediaItemWrapper(it, MovieItemAdapter.ViewType.MOVIE_ITEM_CARD) })
                    topRatedAdapter.submitList(it.topRatedMovies.map { MediaItemWrapper(it, MovieItemAdapter.ViewType.MOVIE_ITEM_CARD) })
                    recommendedAdapter.submitList(it.recommendedMovies.map { MediaItemWrapper(it, MovieItemAdapter.ViewType.MOVIE_ITEM_RECOMMENDED_CARD) })
                    movieFilterAdapter.submitList(it.filters.map { MovieFilterWrapper(it, false) })
                }
                is HomeViewState.FilterRecommendedMovies.Failure -> {
                    // TODO: show error state
                }
                HomeViewState.FilterRecommendedMovies.Processing -> {
                    loadingState(onlyRecommended = true)
                }
                is HomeViewState.FilterRecommendedMovies.Success -> {
                    clearLoadingState(onlyRecommended = true)
                    recommendedAdapter.submitList(it.recommendedMoviesFiltered.map { MediaItemWrapper(it, MovieItemAdapter.ViewType.MOVIE_ITEM_RECOMMENDED_CARD) })
                }
            }
        }

        viewModel.loadData()
    }

    private fun clearLoadingState(onlyRecommended: Boolean = false) {
        if (!onlyRecommended) {
            upcomingAdapter.submitList(null)
            topRatedAdapter.submitList(null)
        }
        recommendedAdapter.submitList(null)
    }

    private fun loadingState(onlyRecommended: Boolean = false) {
        val loadingList = ArrayList<MediaItemWrapper>()
        for (i in 0..5) {
            loadingList.add(
                MediaItemWrapper(
                    MediaItem.dummy(),
                    MovieItemAdapter.ViewType.MOVIE_ITEM_CARD
                )
            )
        }
        if (!onlyRecommended) {
            upcomingAdapter.submitList(loadingList)
            topRatedAdapter.submitList(loadingList)
        }
        recommendedAdapter.submitList(loadingList.map { it.viewType = MovieItemAdapter.ViewType.MOVIE_ITEM_RECOMMENDED_CARD; it })
    }

    companion object {
        val TAG = HomeFragment::class.java.simpleName
        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}