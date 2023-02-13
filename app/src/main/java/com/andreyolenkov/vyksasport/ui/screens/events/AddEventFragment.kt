package com.andreyolenkov.vyksasport.ui.screens.events

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.format.DateFormat.is24HourFormat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.andreyolenkov.vyksasport.APP
import com.andreyolenkov.vyksasport.R
import com.andreyolenkov.vyksasport.databinding.FragmentAddEventBinding
import com.andreyolenkov.vyksasport.models.EventModel
import com.andreyolenkov.vyksasport.ui.screens.complex.ListComplexViewModel
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import kotlinx.android.synthetic.main.fragment_add_event.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class AddEventFragment : Fragment(){
    private lateinit var binding: FragmentAddEventBinding
    private var listComplexName = ArrayList<String>()
    private var complexName = ""
    private var complexId = 0
    private var hour =0
    private var minute =0
    private var place = ""
    //Формат даты
    @SuppressLint("WeekBasedYear")
    private val outputDateFormat = SimpleDateFormat("dd MMM YYYY",Locale.getDefault()).apply {
        timeZone = TimeZone.getTimeZone("UTC+3")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddEventBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        spinComplex()
    }

    private fun init() {
        val viewModel = ViewModelProvider(this).get(AddEventViewModel::class.java)
        //Обработка нажатия на кнопку добавления мероприятия
        binding.saveEvent.setOnClickListener {
            val nameEvent = binding.etEventName.text.toString()
            place = "$complexName, " + binding.etEventAdress.text.toString()
            val dateTime = binding.tvDateTime.text.toString()
            val compId = complexId
            val descr = binding.etEventDescription.text.toString()
            viewModel.insert(EventModel(name = nameEvent, place = place, timedate = dateTime, complexId = compId, description = descr)){}
            APP.navController.navigate(R.id.action_addEventFragment_to_listEventsFragment)
        }
        //Обработка нажатия на кнопку выбора времени
        binding.getDateTime.setOnClickListener {
            openDateTimePicker()
        }
    }
    //Метод выбора времени и даты
    @SuppressLint("SetTextI18n")
    private fun openDateTimePicker() {
        val isSystem24Hour = is24HourFormat(requireContext())
        //Если устройство использует 24ч формат времени
        val clockFormat=if(isSystem24Hour) TimeFormat.CLOCK_24H else TimeFormat.CLOCK_12H

        val timePicker = MaterialTimePicker.Builder()
            .setTimeFormat(clockFormat)
            .build()
        timePicker.show(childFragmentManager,"time")
        timePicker.addOnPositiveButtonClickListener {
            hour = timePicker.hour
            minute = timePicker.minute
            val datePicker = MaterialDatePicker.Builder.datePicker()
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .build()
            datePicker.show(childFragmentManager,"date")
            datePicker.addOnPositiveButtonClickListener {
                val dateSelected = outputDateFormat.format(it).toString()
                this.binding.tvDateTime.text = "$hour:$minute | $dateSelected"
            }
        }
    }
    //Компонент spinner
    private fun spinComplex() {
        val viewModel = ViewModelProvider(this)[ListComplexViewModel::class.java]
        viewModel.getAllComplex().observe(viewLifecycleOwner) { listComplex ->
            //созданный список приобразуем в список названий
            listComplex.forEach { item->listComplexName.add(item.name) }
            val arrayAdapter= ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item,listComplexName)
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            arrayAdapter.notifyDataSetChanged()
            binding.spinner.adapter = arrayAdapter
            //Обработчик выбора элемента спиннера
            binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    //Берем название комплекса
                    complexName = parent?.getItemAtPosition(position).toString()
                    place = "$complexName"
                    //Находим его в списке экземпляров комплексов
                    val tempListComplex = listComplex.find { it.name==complexName }
                    //Забираем его идентификатор
                    if (tempListComplex != null) {
                        complexId = tempListComplex.id
                    }
                }
                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }
        }
    }
}

