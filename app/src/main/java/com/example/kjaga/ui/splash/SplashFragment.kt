package com.example.kjaga.ui.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.kjaga.R
import com.example.kjaga.databinding.FragmentSplashBinding
import com.example.kjaga.ui.home.HomeFragmentDirections
import com.example.kjaga.util.AuthPref

class SplashFragment : Fragment() {

    private var _binding : FragmentSplashBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Handler(Looper.getMainLooper()).postDelayed({
            val authPref = AuthPref(requireContext())
            val token = authPref.getToken()
            if (token == null) {
                findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
            } else {
                val navigation = SplashFragmentDirections.actionSplashFragmentToNavigationHome()
                findNavController().navigate(R.id.action_splashFragment_to_navigation_home)
            }
        }, 2000)
    }

}