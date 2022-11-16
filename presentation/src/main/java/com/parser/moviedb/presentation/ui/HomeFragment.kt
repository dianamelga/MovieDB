package com.parser.moviedb.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.parser.moviedb.presentation.R
import com.parser.moviedb.presentation.databinding.FragmentHomeBinding
import com.parser.moviedb.presentation.viewmodels.HomeViewModel
import com.parser.moviedb.presentation.viewmodels.HomeViewState
import com.parser.moviedb.presentation.viewmodels.MainViewModel

class HomeFragment : Fragment() {

    private val rootViewModel: MainViewModel by activityViewModels()
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding

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
        viewModel.viewState.observe(viewLifecycleOwner) {
            when(it) {
                is HomeViewState.LoadData.Failure -> TODO()
                HomeViewState.LoadData.Processing -> TODO()
                is HomeViewState.LoadData.Success -> TODO()
            }
        }

        viewModel.loadData()
    }

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}