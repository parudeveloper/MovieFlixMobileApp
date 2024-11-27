package com.movieflixmobileapp.prasentation.intro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.movieflixmobileapp.R
import com.movieflixmobileapp.core.DataStoreReference
import com.movieflixmobileapp.databinding.FragmentHomeBinding
import com.movieflixmobileapp.databinding.FragmentIntroBinding
import com.movieflixmobileapp.databinding.FragmentSplashBinding
import kotlinx.coroutines.launch


class IntroFragment : Fragment() {
    private var _binding: FragmentIntroBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentIntroBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleClickListeners()
    }


    private fun handleClickListeners() {
        binding.fragmentIntroCtnBtn.setOnClickListener(){
            lifecycleScope.launch {
                DataStoreReference.updateIntroCompleted(requireContext(),true)
            }
            findNavController().navigate(R.id.action_introFragment_to_homeFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }

}