package com.example.kjaga.ui.history.food

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kjaga.databinding.FragmentHistoryFoodBinding
import com.example.kjaga.domain.api.UiState
import com.example.kjaga.domain.repo.ViewModelFactory
import com.example.kjaga.ui.adapter.HomeListAdapter
import com.example.kjaga.util.AuthPref
import com.example.kjaga.util.getCurrentDate

class HistoryFoodFragment : Fragment() {

    private var _binding: FragmentHistoryFoodBinding? = null
    private val binding get() = _binding!!
    private val bundle = Bundle()


    private val viewModel: HistoryFoodViewModel by viewModels {
        ViewModelFactory.getInstance(requireContext())

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHistoryFoodBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.historyFoodRv.layoutManager = layoutManager

        val type = arguments?.getString("type")
        binding.historyFoodTvFood.text = type
        if(type == "Sarapan"){
            breakfast()
        }
        if(type == "Makan Siang"){
            lunch()
        }
        if(type == "Makan Malam"){
            dinner()
        }
        if(type == "Cemilan"){
            snack()
        }


    }


    private fun breakfast() {
        val token = AuthPref(requireContext()).getToken()
        viewModel.historyFood(getCurrentDate(), "Bearer ${token.toString()}")
            .observe(viewLifecycleOwner) { uiState ->
                when (uiState) {
                    is UiState.Loading -> {
                        // Handle loading state
                    }

                    is UiState.Success -> {
                        // Handle success state
                        val response = uiState.data

                        val rvAdapter = HomeListAdapter(uiState.data.breakfast.items)
                        binding.historyFoodRv.adapter = rvAdapter

                    }

                    is UiState.Error -> {
                        // Handle error state
                        Log.d("ErrorHistory", uiState.error)
                    }
                }
            }
    }


    private fun lunch() {
        val token = AuthPref(requireContext()).getToken()
        viewModel.historyFood(getCurrentDate(), "Bearer ${token.toString()}")
            .observe(viewLifecycleOwner) { uiState ->
                when (uiState) {
                    is UiState.Loading -> {
                        // Handle loading state
                    }

                    is UiState.Success -> {
                        // Handle success state
                        val response = uiState.data
                        val rvAdapter = HomeListAdapter(uiState.data.lunch?.items)
                        binding.historyFoodRv.adapter = rvAdapter

                    }

                    is UiState.Error -> {
                        // Handle error state
                        Log.d("ErrorHistory", uiState.error)
                    }
                }
            }
    }

    private fun dinner() {
        val token = AuthPref(requireContext()).getToken()
        viewModel.historyFood(getCurrentDate(), "Bearer ${token.toString()}")
            .observe(viewLifecycleOwner) { uiState ->
                when (uiState) {
                    is UiState.Loading -> {
                        // Handle loading state
                    }

                    is UiState.Success -> {
                        // Handle success state
                        val response = uiState.data
                        val rvAdapter = HomeListAdapter(uiState.data.dinner?.items)
                        binding.historyFoodRv.adapter = rvAdapter

                    }

                    is UiState.Error -> {
                        // Handle error state
                        Log.d("ErrorHistory", uiState.error)
                    }
                }
            }
    }

    private fun snack() {
        val token = AuthPref(requireContext()).getToken()
        viewModel.historyFood(getCurrentDate(), "Bearer ${token.toString()}")
            .observe(viewLifecycleOwner) { uiState ->
                when (uiState) {
                    is UiState.Loading -> {
                        // Handle loading state
                    }

                    is UiState.Success -> {
                        // Handle success state
                        val response = uiState.data
                        val rvAdapter = HomeListAdapter(uiState.data.snack?.items)
                        binding.historyFoodRv.adapter = rvAdapter

                    }

                    is UiState.Error -> {
                        // Handle error state
                        Log.d("ErrorHistory", uiState.error)
                    }
                }
            }
    }

}