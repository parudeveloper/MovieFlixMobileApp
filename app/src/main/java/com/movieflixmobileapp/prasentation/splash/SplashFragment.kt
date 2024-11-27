package com.movieflixmobileapp.prasentation.splash

import android.animation.Animator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.movieflixmobileapp.R
import com.movieflixmobileapp.core.DataStoreReference
import com.movieflixmobileapp.databinding.FragmentSplashBinding
import kotlinx.coroutines.launch


class SplashFragment : Fragment() {
    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSplashBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        lifecycleScope.launch {
            DataStoreReference.isIntroCompleted(requireContext()).collect{ completed->
                if(completed){
                    navigateTo(R.id.action_splashFragment_to_homeFragment)
                }else{
                    navigateTo(R.id.action_splashFragment_to_introFragment)
                }
            }
        }
    }

    private fun navigateTo(id: Int) {

        binding.lottieAnimation.addAnimatorListener(object: Animator.AnimatorListener{
            override fun onAnimationStart(p0: Animator) {
//                    lifecycleScope.launch {
//                        delay(3000)
//                        findNavController().navigate(id)
//                    }
            }
            override fun onAnimationEnd(p0: Animator) {
                lifecycleScope.launch {
                    findNavController().navigate(id)
                }
            }

            override fun onAnimationCancel(p0: Animator) {
            }

            override fun onAnimationRepeat(p0: Animator) {
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Clean up the binding reference to avoid memory leaks
        _binding = null
    }

}