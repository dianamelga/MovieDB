package com.parser.moviedb.presentation.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.parser.moviedb.presentation.databinding.FragmentSplashBinding
import com.parser.moviedb.presentation.viewmodels.MainViewModel

class SplashFragment : Fragment() {

    private lateinit var binding: FragmentSplashBinding
    private val rootViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Handler(Looper.getMainLooper()).postDelayed(
            {
                rootViewModel.goToHome()
            },
            2000
        )
    }

    companion object {
        val TAG = SplashFragment::class.java.simpleName
        @JvmStatic
        fun newInstance() = SplashFragment()
    }
}