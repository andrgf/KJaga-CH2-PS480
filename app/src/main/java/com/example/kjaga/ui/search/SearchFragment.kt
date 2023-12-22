package com.example.kjaga.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kjaga.databinding.FragmentSearchBinding
import com.example.kjaga.domain.api.UiState
import com.example.kjaga.domain.repo.ViewModelFactory
import com.example.kjaga.ui.adapter.SearchListAdapter
import com.example.kjaga.util.AuthPref

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SearchViewModel by viewModels {
        ViewModelFactory.getInstance(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.searchRv.layoutManager = layoutManager

        binding.searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.getSearch(query).observe(viewLifecycleOwner) {
                    binding.searchBar.clearFocus()
                    when(it) {
                        is UiState.Loading -> {

                        }
                        is UiState.Success -> {
                            val list = it.data.foods
                            val rvAdapter = SearchListAdapter(list)
                            binding.searchRv.adapter = rvAdapter
                        }
                        is UiState.Error -> {

                        }
                    }
                }
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}