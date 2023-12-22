package com.example.kjaga.ui.profile.edit

import android.app.DatePickerDialog
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import com.example.kjaga.R
import com.example.kjaga.databinding.FragmentEditProfileBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class EditProfileFragment : Fragment(), DatePickerDialog.OnDateSetListener {

    private var _binding: FragmentEditProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: EditProfileViewModel

    private val calender = Calendar.getInstance()
    private val formatDate = SimpleDateFormat("dd MMM yyyy", Locale.US)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditProfileBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.epEtName.setText(arguments?.getString("name"))
        binding.epEtEmail.setText(arguments?.getString("email"))

        binding.epEtDate.setOnClickListener {
            DatePickerDialog(
                requireContext(),
                this,
                calender.get(Calendar.YEAR),
                calender.get(Calendar.MONTH),
                calender.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        calender.set(year, month, dayOfMonth)
        displayDate(calender.timeInMillis)
    }

    private fun displayDate(time: Long) {
        binding.epEtDate.setText(formatDate.format(time))

    }

    override fun onDestroy() {
        super.onDestroy()
    }


}