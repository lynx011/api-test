package com.example.movieapp.fragments

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentMessagesBinding
import java.util.*

class RegistrationFragment : Fragment() {
    private lateinit var binding: FragmentMessagesBinding

    private var gender: String = ""
    private var dropDownNo: String = ""
    override fun onResume() {
        super.onResume()
        val codes = resources.getStringArray(R.array.country_code)
        val adapter = ArrayAdapter(requireContext(), R.layout.drop_down_list, codes)
        binding.countryCode.setAdapter(adapter)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMessagesBinding.inflate(layoutInflater)

//        viewModel = ViewModelProvider(this)[FormViewModel::class.java]
        return binding.root

    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        binding.femaleItem.setOnClickListener {
            binding.femaleLayout.setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.purple
                )
            )
            binding.maleLayout.setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
            binding.femaleItem.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            binding.maleItem.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
            gender = "Female"
        }

        binding.countryCode.setOnItemClickListener { parent, _, position, _ ->
            dropDownNo = parent.getItemAtPosition(position).toString()
        }

        binding.showDate.setOnClickListener {
            val calendar = Calendar.getInstance()
            val dateSetListener =
                DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                    binding.showDate.setText("$dayOfMonth/$monthOfYear/$year")
                    calendar.set(Calendar.YEAR, year)
                    calendar.set(Calendar.MONTH, monthOfYear)
                    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                }
            val dialog = DatePickerDialog(
                requireContext(), dateSetListener,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
            dialog.datePicker.maxDate = calendar.timeInMillis
            dialog.show()

        }

        binding.maleItem.setOnClickListener {
            binding.maleLayout.setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.purple
                )
            )
            binding.femaleLayout.setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
            binding.femaleItem.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
            binding.maleItem.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            gender = "Male"
        }

//        binding.firstName.addTextChangedListener(
//            object : TextWatcher{
//                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//
//                }
//
//                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//
//                }
//
//                override fun afterTextChanged(p0: Editable?) {
//                    if(validateUI()) {
//                        enabledButton()
//                    }
//                    else{
//                        disabledButton()
//                    }
//                }
//            }
//        )
//
//        binding.lastName.addTextChangedListener(
//            object : TextWatcher{
//                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                }
//
//                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                }
//
//                override fun afterTextChanged(p0: Editable?) {
//                    if(validateUI()) {
//                        enabledButton()
//                    }
//                    else{
//                        disabledButton()
//                    }
//                }
//            }
//        )
//
//        binding.emailAddress.addTextChangedListener(
//            object : TextWatcher{
//                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                }
//
//                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                    if(!Patterns.EMAIL_ADDRESS.matcher(
//                            binding.emailAddress.text.toString()
//                        ).matches()){
//                        binding.emailAddress.error = "invalid email"
//                    }
//                }
//
//                override fun afterTextChanged(p0: Editable?) {
//                    if(validateUI()) {
//                        enabledButton()
//                    }
//                    else{
//                        disabledButton()
//                    }
//                }
//            }
//        )
//
//        binding.showDate.addTextChangedListener(
//            object : TextWatcher{
//                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                }
//
//                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                }
//
//                override fun afterTextChanged(p0: Editable?) {
//                    if(validateUI()) {
//                        enabledButton()
//                    }
//                    else{
//                        disabledButton()
//                    }
//                }
//            }
//        )
//
//        binding.couResidence.addTextChangedListener(
//            object : TextWatcher{
//                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                }
//
//                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                }
//
//                override fun afterTextChanged(p0: Editable?) {
//                    if(validateUI()) {
//                        enabledButton()
//                    }
//                    else{
//                        disabledButton()
//                    }
//                }
//            }
//        )
//
//        binding.nationality.addTextChangedListener(
//            object : TextWatcher{
//                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                }
//
//                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                }
//
//                override fun afterTextChanged(p0: Editable?) {
//                    if(validateUI()) {
//                        enabledButton()
//                    }
//                    else{
//                        disabledButton()
//                    }
//                }
//            }
//        )
//
//        binding.phNo.addTextChangedListener(
//            object : TextWatcher{
//                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                }
//
//                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//
//                }
//
//                override fun afterTextChanged(p0: Editable?) {
//                    if(validateUI()) {
//                        enabledButton()
//                    }
//                    else{
//                        disabledButton()
//                    }
//                }
//            }
//        )

//        binding.clickButton.setOnClickListener {
//
//            val firstNameInput = binding.firstName.text.toString()
//            val lastNameInput = binding.lastName.text.toString()
//            val emailInput = binding.emailAddress.text.toString()
//            val dateInput = binding.showDate.text.toString()
//            val nationalityInput = binding.nationality.text.toString()
//            val countryInput = binding.couResidence.text.toString()
//            val phoneInput = binding.phNo.text
//
//            val bundle = Bundle()
//            bundle.putString("first", firstNameInput)
//            bundle.putString("last", lastNameInput)
//            bundle.putString("email", emailInput)
//            bundle.putString("date", dateInput)
//            bundle.putString("nationality", nationalityInput)
//            bundle.putString("country", countryInput)
//            bundle.putString("phone", phoneInput.toString())
//            bundle.putString("gender", gender)
//            bundle.putString("code", dropDownNo)
//
//            findNavController().navigate(R.id.personalFragment, bundle)
//        }

    }

//    private fun enabledButton(){
//        binding.clickButton.isEnabled = true
//        binding.clickButton.setBackgroundColor(
//            ContextCompat.getColor(
//                requireContext(),
//                R.color.purple_200
//            )
//        )
//    }
//    private fun disabledButton(){
//        binding.clickButton.isEnabled = false
//        binding.clickButton.setBackgroundColor(
//            ContextCompat.getColor(
//                requireContext(),
//                R.color.grey_200
//            )
//        )
//    }
//    private fun validateUI(): Boolean {
//         return binding.firstName.text.toString().isNotEmpty() &&
//                binding.lastName.text.toString().isNotEmpty() &&
//                binding.emailAddress.text.toString().isNotEmpty() &&
//                dropDownNo.isNotEmpty() &&
//                binding.showDate.text.toString().isNotEmpty() &&
//                binding.nationality.text.toString().isNotEmpty() &&
//                binding.couResidence.text.toString().isNotEmpty() &&
//                gender.isNotEmpty() &&
//                 binding.phNo.text.toString().isNotEmpty() &&
//                Patterns.EMAIL_ADDRESS.matcher(
//                    binding.emailAddress.text.toString()
//                ).matches()
//    }
}
