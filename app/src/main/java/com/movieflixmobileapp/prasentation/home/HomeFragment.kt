package com.movieflixmobileapp.prasentation.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.movieflix.domain.model.HomeFeedData
import com.movieflixmobileapp.core.utils.NetworkResults
import com.movieflixmobileapp.databinding.FragmentHomeBinding
import com.movieflixmobileapp.prasentation.viewmodels.HomeInfoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val homeInfoViewModel: HomeInfoViewModel by viewModels() // initializing viewmodel
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observer()


    }

    private fun observer() {
        homeInfoViewModel.homeFeedList.observe(viewLifecycleOwner) {
            when (it) {
                is NetworkResults.Error -> {
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }

                is NetworkResults.Loading -> {
                    Log.i("HomeFragment", "Loading State Only")
                }

                is NetworkResults.Success -> {
                    it.data?.let { homeFeedData: HomeFeedData ->
                        Log.i("HomeFragment", "${homeFeedData.bannerMovie.size}")
                        homeFeedData.bannerMovie.forEach() {

                            Log.i("HomeFragment", it.toString())
                        }
                    }
                }
            }
        }
        homeInfoViewModel.getMovieInfoData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}