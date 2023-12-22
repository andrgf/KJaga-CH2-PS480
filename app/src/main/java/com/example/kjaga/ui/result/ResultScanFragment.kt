package com.example.kjaga.ui.result

import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.kjaga.R
import com.example.kjaga.databinding.FragmentResultScanBinding
import com.example.kjaga.domain.api.UiState
import com.example.kjaga.domain.repo.ViewModelFactory
import com.example.kjaga.util.AuthPref

class ResultScanFragment : Fragment() {

    private var _binding: FragmentResultScanBinding? = null
    private val binding get() = _binding!!
    private val bundle = Bundle()
    private val viewModel: ResultScanViewModel by viewModels {
        ViewModelFactory.getInstance(requireContext())
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentResultScanBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val currentImageUri = arguments?.getParcelable<Uri>("imageUri")
        val predictId = arguments?.getString("predictionId")
        val handler = Handler()

        handler.postDelayed({
            viewModel.getScanResult(predictId.toString()).observe(viewLifecycleOwner) { uiState ->
                when (uiState) {
                    is UiState.Loading -> {
                        binding.contentContainer.visibility = View.GONE
                        binding.progressBar.visibility = View.VISIBLE
                    }

                    is UiState.Success -> {
                        binding.contentContainer.visibility = View.VISIBLE
                        val result = uiState.data.foods?.get(0)?.food?.name
                        bundle.putString("foodName", result)
                        val totalEnergy = uiState.data.foods?.get(0)?.portion?.nutrition?.energyKkal.toString()
                        bundle.putString("totalEnergy", totalEnergy)
                        val foodId = uiState.data.foods?.get(0)?.food?.id?.toInt()
                        val foodPortion = uiState.data.foods?.get(0)?.portion?.id?.toInt()
                        if (foodId != null) {
                            bundle.putInt("foodId", foodId)
                        }
                        if (foodPortion != null) {
                            bundle.putInt("foodPortion", foodPortion)
                        }


                        binding.resultTvFood.text = result
                        binding.addTvTotalNutrisi.text =
                            "$totalEnergy Kkal"
                        binding.progressBar.visibility = View.GONE
                    }

                    is UiState.Error -> {
                        binding.contentContainer.visibility = View.VISIBLE
                        binding.progressBar.visibility = View.GONE
                    }
                }
            }
        }, 1000)

        val authPref = AuthPref(requireContext())
        val token = authPref.getToken()

        binding.resultBtnTambahkan.setOnClickListener {

            bundle.putParcelable("imageUri", currentImageUri)
            findNavController().navigate(R.id.addFoodFragment, bundle)
        }

        binding.resultImg.setImageURI(currentImageUri)
    }

}