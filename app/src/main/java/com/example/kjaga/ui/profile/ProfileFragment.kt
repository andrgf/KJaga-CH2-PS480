package com.example.kjaga.ui.profile

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.kjaga.R
import com.example.kjaga.databinding.FragmentProfileBinding
import com.example.kjaga.domain.api.UiState
import com.example.kjaga.domain.repo.ViewModelFactory
import com.example.kjaga.util.AuthPref

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProfileViewModel by viewModels {
        ViewModelFactory.getInstance(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val authPref = AuthPref(requireContext())
        val token = authPref.getToken()
        val bundle = Bundle()

        viewModel.userByToken("Bearer ${token.toString()}").observe(viewLifecycleOwner) {
            when(it) {
                is UiState.Loading -> {

                }

                is UiState.Success -> {
                    val name = it.data.userByToken.name
                    val email = it.data.userByToken.email
                    binding.profileTvName.text = name
                    binding.profileTvEmail.text = email

                    bundle.putString("id", it.data.userByToken.id)
                    bundle.putString("name", name)
                    bundle.putString("email", email)
                }
                is UiState.Error -> {
                }
            }
        }

        binding.profileBtnLogout.setOnClickListener {
            authPref.clearToken()

            val navigation = ProfileFragmentDirections.actionNavigationProfileToLoginFragment()
            findNavController().navigate(navigation)
        }

        binding.profileBtnProfile.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_profile_to_editProfileFragment, bundle)
        }

    }

    override fun onResume() {
        super.onResume()

    }

}