package com.example.kjaga.ui.history

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.kjaga.R
import com.example.kjaga.databinding.FragmentHistoryBinding
import com.example.kjaga.domain.api.UiState
import com.example.kjaga.domain.repo.ViewModelFactory
import com.example.kjaga.util.AuthPref
import com.example.kjaga.util.getCurrentDate

class HistoryFragment : Fragment() {

    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HistoryViewModel by viewModels {
        ViewModelFactory.getInstance(requireContext())

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = Bundle()

        binding.apply {
            historyBtnSarapan.setOnClickListener {
                bundle.putString("type", "Sarapan")
                findNavController().navigate(R.id.historyFoodFragment, bundle)
            }
            historyBtnMakanSiang.setOnClickListener {
                bundle.putString("type", "Makan Siang")
                findNavController().navigate(R.id.historyFoodFragment, bundle)
            }
            historyBtnMakanMalam.setOnClickListener {
                bundle.putString("type", "Makan Malam")
                findNavController().navigate(R.id.historyFoodFragment, bundle)
            }
            historyBtnCemilan.setOnClickListener {
                bundle.putString("type", "Cemilan")
                findNavController().navigate(R.id.historyFoodFragment, bundle)
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()

        binding.historyTvDate.text = getCurrentDate()
        val token = AuthPref(requireContext()).getToken()


        viewModel.getHistory(getCurrentDate(), "Bearer ${token.toString()}").observe(viewLifecycleOwner){ uiState ->
            when (uiState) {
                is UiState.Loading -> {
                    // Handle loading state
                }
                is UiState.Success -> {
                    // Handle success state
                    val response = uiState.data
                    if(response.totalNutrition.energyKkal>2000){
                        binding.homeTvKkalCompleted.text = response.totalNutrition.energyKkal.toString()
                    }else{
                        binding.homeTvKkalCompleted.text = response.totalNutrition.energyKkal.toString()
                    }

                    val currentKkal = response.totalNutrition.energyKkal
                    val maxKkal = response.akg?.energyKkal

                    binding.homeTvKkalCompleted.text = currentKkal.toString()
                    binding.homeTvKkalUnCompleted.text = "/" +maxKkal.toString()
                    binding.homePbStatus.progress = calculateProgress(currentKkal, maxKkal)

                    val kalori = response.totalNutrition.cholesterolMg
                    val maxKalori = response.akg?.cholesterolMg
                    if (kalori != null) {
                        binding.homePbCalories.progress = calculateProgressVertical(kalori.toInt(), maxKalori)
                    }

                    val karbo = response.totalNutrition.carbohydratesG
                    val maxKarbo = response.akg?.carbohydratesG
                    if (karbo != null) {
                        binding.homePbKarbo.progress = calculateProgressVertical(karbo.toInt(), maxKarbo)
                    }

                    val lemak = response.totalNutrition.fatG
                    val maxLemak = response.akg?.fatG
                    if (lemak != null) {
                        binding.homePbLemak.progress = calculateProgressVertical(lemak.toInt(), maxLemak)
                    }

                    val protein = response.totalNutrition.proteinG
                    val maxProtein = response.akg?.proteinG
                    if (protein != null) {
                        binding.homePbProtein.progress = calculateProgressVertical(protein.toInt(), maxProtein)
                    }
                    Log.d( "History",response.toString())
                    Log.d("History Data", getCurrentDate())

                }
                is UiState.Error -> {
                    // Handle error state
                    Log.d("ErrorHistory", uiState.error)
                }
            }
        }

    }

    private fun calculateProgress(currentValue: Int, maxKkal: Int?): Int {
        val progress = (currentValue.toDouble() / maxKkal!!) * 100
        return progress.toInt()
    }

    private fun calculateProgressVertical(currentValue: Int?, max: Int?): Int {
        val progress = (currentValue!! / max!!) * 100
        return progress.toInt()
    }

}