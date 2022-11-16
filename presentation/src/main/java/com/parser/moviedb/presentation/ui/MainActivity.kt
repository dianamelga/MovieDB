package com.parser.moviedb.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.parser.moviedb.presentation.R
import com.parser.moviedb.presentation.utils.addFragmentToActivity
import com.parser.moviedb.presentation.viewmodels.MainViewModel
import com.parser.moviedb.presentation.viewmodels.NavigationState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addFragmentToActivity(SplashFragment.newInstance(), R.id.root, SplashFragment.TAG, false)

        viewModel.navigationState.observe(this) {
            when(it) {
                NavigationState.HomeScreen -> {
                    addFragmentToActivity(
                        HomeFragment.newInstance(),
                        R.id.root,
                        HomeFragment.TAG,
                        false
                    )
                }
                NavigationState.MovieDetailScreen -> {
                    addFragmentToActivity(
                        MovieDetailFragment.newInstance(),
                        R.id.root,
                        MovieDetailFragment.TAG,
                        false
                    )
                }
            }
        }
    }
}