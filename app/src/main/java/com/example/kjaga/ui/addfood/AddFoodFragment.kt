package com.example.kjaga.ui.addfood

import android.R
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.kjaga.databinding.FragmentAddFoodBinding
import com.example.kjaga.domain.api.UiState
import com.example.kjaga.domain.repo.ViewModelFactory
import com.example.kjaga.util.AuthPref
import com.example.kjaga.util.getCurrentDate

class AddFoodFragment : Fragment() {

    private var _binding: FragmentAddFoodBinding? = null
    private val binding get() = _binding!!
    private lateinit var jenisMakanan: String
    private val viewModel: AddFoodViewModel by viewModels {
        ViewModelFactory.getInstance(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddFoodBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val token = AuthPref(requireContext()).getToken()

        val currentImageUri = arguments?.getParcelable<Uri>("imageUri")
        val totalEnery = arguments?.getString("totalEnergy")
        val foodName = arguments?.getString("foodName")
        val foodId = arguments?.getInt("foodId")
        val foodPortion = arguments?.getInt("foodPortion")
        binding.addImg.setImageURI(currentImageUri)
        binding.addTvTotalNutrisi.text = "$totalEnery Kkal"
        binding.resultTvFood.text = foodName


        //Dropdown jenis makanan
        val items = arrayOf("breakfast", "lunch", "dinner", "snack")
        val adapter = ArrayAdapter(requireContext(), R.layout.simple_spinner_dropdown_item, items)
        binding.addSpinner.adapter= adapter
        binding.addSpinner.onItemSelectedListener= object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                val selectedItem = items[position]
                jenisMakanan= selectedItem
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }


        binding.addBtnTambahkan.setOnClickListener {
            val porsi: String = binding.addEtPorsi.text.toString()
            viewModel.uploadMealsDiaries("Bearer ${token.toString()}", getCurrentDate(), jenisMakanan, foodId,foodPortion,porsi.toInt()).observe(viewLifecycleOwner){ uiState->
                when(uiState){
                    is UiState.Loading->{

                    }
                    is UiState.Success->{
                      //Navigasi ke page selanjutnya
                        Toast.makeText(requireContext(), "Sukses ditambhkan", Toast.LENGTH_LONG).show()
                        val navigation = AddFoodFragmentDirections.actionAddFoodFragmentToNavigationHome()
                        findNavController().navigate(navigation)
                    }
                    is UiState.Error->{}
                }
            }
        }




    }



}