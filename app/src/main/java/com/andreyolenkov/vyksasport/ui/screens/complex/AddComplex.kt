package com.andreyolenkov.vyksasport.ui.screens.complex

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.text.format.DateFormat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.andreyolenkov.vyksasport.APP
import com.andreyolenkov.vyksasport.R
import com.andreyolenkov.vyksasport.adapter.ActionsOnImg
import com.andreyolenkov.vyksasport.databinding.FragmentAddComplexBinding
import com.andreyolenkov.vyksasport.models.ComplexModel
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat


class AddComplex : Fragment() {
    lateinit var binding: FragmentAddComplexBinding
    lateinit var imgComplex: Bitmap
    private var imgRequestCode = 1
    private var nameComplex = ""
    private var hourStart =0
    private var minuteStart =0
    private var hourEnd =0
    private var minuteEnd =0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddComplexBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        openImg()
        openDateTimePicker()
        init()
    }

    private fun init() {
        val viewModel = ViewModelProvider(this).get(AddComplexViewModel::class.java)
        val complexImg = ActionsOnImg()
        binding.saveComplex.setOnClickListener {
            nameComplex = binding.etComplexName.text.toString()
            val opermode = binding.tvTime.text.toString()
            val adress = binding.etComplexAdress.text.toString()
            val tel = binding.etComplexNumber.text.toString()
            val email = binding.etEmail.text.toString()
            viewModel.insert(ComplexModel(img=complexImg.writeImg(imgComplex),name = nameComplex, operationMode = opermode, adress = adress, number = tel, email = email)){}

            APP.navController.navigate(R.id.action_addComplex_to_listComplex)//переходим обратно на экран
        }

    }
    private fun openDateTimePicker() {
        val isSystem24Hour =
            DateFormat.is24HourFormat(requireContext())//Если устройство использует 24ч формат времени
        val clockFormat=if(isSystem24Hour) TimeFormat.CLOCK_24H else TimeFormat.CLOCK_12H
        binding.btComplexOperationMode.setOnClickListener{
            val timePicker = MaterialTimePicker.Builder()
                .setTimeFormat(clockFormat)
                .build()
            timePicker.show(childFragmentManager,"time")
            timePicker.addOnPositiveButtonClickListener {
                hourStart = timePicker.hour
                minuteStart = timePicker.minute
                val timePicker2 = MaterialTimePicker.Builder()
                    .setTimeFormat(clockFormat)
                    .build()
                timePicker2.show(childFragmentManager,"time2")
                timePicker2.addOnPositiveButtonClickListener {
                    hourEnd = timePicker2.hour
                    minuteEnd = timePicker2.minute
                    binding.tvTime.text = "с $hourStart:$minuteStart до $hourEnd:$minuteEnd"
                }
            }
        }

    }
     private fun openImg () {
        binding.imgComplexAdd.setOnClickListener {
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            intent.type = "image/*"
            startActivityForResult(intent, imgRequestCode) //registerForActivityResult()
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode==Activity.RESULT_OK && requestCode==imgRequestCode) {
            binding.imgComplexAdd.setImageURI(data?.data)
            binding.imgComplexAdd.buildDrawingCache()
            imgComplex=binding.imgComplexAdd.drawingCache
        }
    }
}