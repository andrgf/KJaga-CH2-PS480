package com.example.kjaga.ui.auth.login

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.kjaga.data.auth.LoginResponse
import com.example.kjaga.databinding.FragmentLoginBinding
import com.example.kjaga.domain.api.UiState
import com.example.kjaga.domain.repo.ViewModelFactory
import com.example.kjaga.util.AuthPref

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val viewModel: LoginViewModel by viewModels {
        ViewModelFactory.getInstance(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginPb.visibility= View.GONE
        binding.apply {

            loginBtnMasuk.setOnClickListener {
                login()
            }

            loginTvDaftar.setOnClickListener {
                register()
            }
        }

        onBackPressed()
    }

    private fun register() {
        val navigation = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
        findNavController().navigate(navigation)
    }

    private fun login() {
        val email = binding.loginEtEmail.text.toString()
        val password = binding.loginEtPass.text.toString()

        viewModel.loginUser(email, password).observe(requireActivity()) {
            when(it) {
                is UiState.Loading -> {
                    Log.d("Login", "Loading")
                    binding.loginPb.visibility = View.VISIBLE
                }
                is UiState.Success -> {
                    binding.loginPb.visibility= View.GONE
                    Log.d("Login", "Success")
                    val data = it.data
                    saveToken(data)
                }
                is UiState.Error -> {
                    Log.d("Login", "Error")
                }
                else -> {

                }
            }
        }
    }

    private fun saveToken(data: LoginResponse) {
        if (data.token.isNullOrEmpty()) {
            Toast.makeText(requireContext(), "User salah", Toast.LENGTH_SHORT).show()
        } else {
            val pref = AuthPref(requireContext())
            pref.setToken(data.token)

            val navigation = LoginFragmentDirections.actionLoginFragmentToNavigationHome()
            NavHostFragment.findNavController(this).navigate(navigation)
        }
    }

    private fun onBackPressed() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                requireActivity().finish()
            }
        })
    }


}