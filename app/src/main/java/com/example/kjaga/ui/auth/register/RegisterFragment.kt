package com.example.kjaga.ui.auth.register

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.kjaga.R
import com.example.kjaga.data.auth.RegisterResponse
import com.example.kjaga.databinding.FragmentRegisterBinding
import com.example.kjaga.domain.api.UiState
import com.example.kjaga.domain.repo.ViewModelFactory
import com.example.kjaga.ui.auth.login.LoginFragmentDirections
import com.example.kjaga.util.AuthPref

class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    private val viewModel: RegisterViewModel by viewModels {
        ViewModelFactory.getInstance(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.registerPb.visibility = View.GONE

        binding.apply {
            registerBtnDaftar.setOnClickListener {
                register()
            }

            registerTvMasuk.setOnClickListener {
                login()
            }
        }
    }

    private fun register() {
        val name = binding.registerEtName.text.toString()
        val email = binding.registerEtEmail.text.toString()
        val password = binding.registerEtPass.text.toString()
        val confirmPass = binding.registerEtPassConfirm.text.toString()

        viewModel.registerUser(email, name, password, confirmPass).observe(viewLifecycleOwner) {uiState ->
                when(uiState) {
                    is UiState.Loading -> {
                        binding.registerPb.visibility = View.VISIBLE
                    }
                    is UiState.Success -> {
                        binding.registerPb.visibility = View.GONE
                        Log.d("Register", "Success")
                        Toast.makeText(requireContext(), uiState.data.message, Toast.LENGTH_SHORT).show()
                        login()
                    }
                    is UiState.Error -> {
                        binding.registerPb.visibility = View.GONE
                        Log.d("Register", "Error")
                        Toast.makeText(requireContext(), uiState.error, Toast.LENGTH_SHORT).show()
                        Log.d("Data Register", "email: $email, name: $name, pass: $password, confirm: $confirmPass")
                    }
                }
        }
    }


    private fun login() {
        val navigation = RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
        findNavController().navigate(navigation)
    }

}