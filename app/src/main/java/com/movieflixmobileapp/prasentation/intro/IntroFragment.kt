package com.movieflixmobileapp.prasentation.intro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.movieflixmobileapp.R
import com.movieflixmobileapp.databinding.FragmentHomeBinding
import com.movieflixmobileapp.databinding.FragmentIntroBinding
import com.movieflixmobileapp.databinding.FragmentSplashBinding


class IntroFragment : Fragment() {
    private var _binding: FragmentIntroBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_intro, container, false)\
        _binding = FragmentIntroBinding.inflate(inflater,container,false)

        return binding.root
    }

}