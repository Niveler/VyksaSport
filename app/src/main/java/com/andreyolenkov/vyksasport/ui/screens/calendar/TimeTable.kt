package com.andreyolenkov.vyksasport.ui.screens.calendar


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import androidx.lifecycle.ViewModelProvider
import com.andreyolenkov.vyksasport.databinding.FragmentTimeTableBinding
import com.andreyolenkov.vyksasport.models.SectionsModel
import com.andreyolenkov.vyksasport.models.tuples.SectionModuleTuple

class TimeTable : Fragment() {
    lateinit var binding: FragmentTimeTableBinding
    lateinit var calendar:CalendarView
    lateinit var currentSection: SectionsModel
    private var tmpSectionId=0L
    private var tmpPersonId=0L
    private var tmpGroupId=0L

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentTimeTableBinding.inflate(layoutInflater,container,false)
        currentSection = arguments?.getSerializable("mysections") as SectionsModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        calendar = binding.calendarView
        //Получим Id текущей выбранной секции и Id пользователя
        tmpSectionId=currentSection.id.toLong()
        tmpPersonId=currentSection.person_id.toLong()
        val viewModelTimeTable = ViewModelProvider(this)[TimeTableViewModel::class.java]

    }

}