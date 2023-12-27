package com.example.kjaga.ui.home

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kjaga.R
import com.example.kjaga.databinding.FragmentHomeBinding
import com.example.kjaga.domain.api.UiState
import com.example.kjaga.domain.repo.ViewModelFactory
import com.example.kjaga.ui.adapter.HomeListAdapter
import com.example.kjaga.util.AuthPref
import com.example.kjaga.util.getCurrentDate
import com.example.kjaga.util.getImageUri
import com.example.kjaga.util.uriToFile
import java.io.File

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private var currentImageUri: Uri? = null
    private var url: String = ""
    private var predictionId: String = ""
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels {
        ViewModelFactory.getInstance(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.homeRv.layoutManager = layoutManager

        val authPref = AuthPref(requireContext())
        val token = authPref.getToken()

        binding.homeBtnScan.setOnClickListener {
            viewModel.getSignedLink("Bearer ${token.toString()}", "image/jpeg")
                .observe(viewLifecycleOwner) { uiState ->
                    when (uiState) {
                        is UiState.Loading -> {}
                        is UiState.Success -> {
                            val response = uiState.data
                            url = response.url
                            predictionId = response.predictionId
                        }

                        is UiState.Error -> {}
                    }
                }
            startCamera()

        }

        onBackPressed()
    }

    private fun onBackPressed() {
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    requireActivity().finish()
                }
            })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()

        val authPref = AuthPref(requireContext())
        val token = authPref.getToken()

        viewModel.userByToken("Bearer ${token.toString()}").observe(viewLifecycleOwner) {
            when(it) {
                is UiState.Loading -> {

                }

                is UiState.Success -> {
                    val name = it.data.userByToken.name
                    binding.homeTvName.text = name.uppercase()
                }
                is UiState.Error -> {
                }
            }
        }

        viewModel.getHistory(getCurrentDate(), "Bearer ${token.toString()}").observe(viewLifecycleOwner){
            when(it){
                is UiState.Loading->{
                    Log.d("DATAHOME", "loading")
                }
                is UiState.Success->{
                    Log.d("DATAHOME", it.data.toString())
                    val rvAdapter = HomeListAdapter(it.data.breakfast.items)
                    binding.homeRv.adapter=rvAdapter

                    val currentKkal = it.data.totalNutrition.energyKkal
                    val maxKkal = it.data.akg?.energyKkal

                    binding.homeTvKkalCompleted.text = currentKkal.toString()
                    binding.homeTvKkalUnCompleted.text = "/" +maxKkal.toString()
                    binding.homePbStatus.progress = calculateProgress(currentKkal, maxKkal)

                    val gula = it.data.totalNutrition.cholesterolMg
                    val maxGula = it.data.akg?.cholesterolMg
                    if (gula != null) {
                        binding.homePbCalories.progress = calculateProgressVertical(gula.toInt(), maxGula)
                    }

                    val karbo = it.data.totalNutrition.carbohydratesG
                    val maxKarbo = it.data.akg?.carbohydratesG
                    if (karbo != null) {
                        binding.homePbKarbo.progress = calculateProgressVertical(karbo.toInt(), maxKarbo)
                    }

                    val lemak = it.data.totalNutrition.fatG
                    val maxLemak = it.data.akg?.fatG
                    if (lemak != null) {
                        binding.homePbLemak.progress = calculateProgressVertical(lemak.toInt(), maxLemak)
                    }

                    val protein = it.data.totalNutrition.proteinG
                    val maxProtein = it.data.akg?.proteinG
                    if (protein != null) {
                        binding.homePbProtein.progress = calculateProgressVertical(protein.toInt(), maxProtein)
                    }
                }
                is UiState.Error->{
                    Log.d("DATAHOME", it.error)
                }
            }
        }
    }


    private fun startCamera() {
        currentImageUri = getImageUri(requireContext())
        launcherIntentCamera.launch(currentImageUri)
    }

    private val launcherIntentCamera = registerForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { isSuccess ->
        if (isSuccess) {
            val file = uriToFile(currentImageUri!!,requireContext())
            uploadFile(file, url)
            val bundle = Bundle()
            bundle.putParcelable("imageUri", currentImageUri)
            bundle.putString("predictionId", predictionId)
            findNavController().navigate(R.id.resultScanFragment, bundle)

        }
    }

    private fun uploadFile(file: File, url: String) {
        viewModel.uploadFile(url, file).observe(viewLifecycleOwner){uiState->
            when(uiState){
                is UiState.Loading->{}
                is UiState.Success->{
                    val bundle = Bundle()
                    bundle.putParcelable("imageUri", currentImageUri)
                    bundle.putString("predictionId", predictionId)
                    findNavController().navigate(R.id.resultScanFragment, bundle)
                }
                is UiState.Error->{}
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