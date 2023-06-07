package com.andreyolenkov.vyksasport.ui.screens.section

import android.graphics.Bitmap
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.andreyolenkov.vyksasport.APP
import com.andreyolenkov.vyksasport.R
import com.andreyolenkov.vyksasport.adapter.ActionsOnImg
import com.andreyolenkov.vyksasport.databinding.FragmentSectionDetailBinding
import com.andreyolenkov.vyksasport.models.SectionsModel
import com.andreyolenkov.vyksasport.models.tuples.SectionModuleTuple
import kotlinx.android.synthetic.main.fragment_section_detail.*



class SectionDetail : Fragment() {
    lateinit var binding: FragmentSectionDetailBinding
    var sectionImg=ActionsOnImg()
    lateinit var imgByte:Bitmap
    lateinit var currentSection: SectionModuleTuple

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSectionDetailBinding.inflate(layoutInflater,container,false)
        currentSection = arguments?.getSerializable("section") as SectionModuleTuple
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        binding.timetable.setOnClickListener{
            APP.navController.navigate(R.id.action_section_detail_to_sectionCalendar)
        }
        binding.addrequest.setOnClickListener {
            APP.navController.navigate(R.id.action_section_detail_to_sectionRequestAdd)
        }
        val viewModel1 = ViewModelProvider(this)[ListSectionViewModel::class.java]
        viewModel1.initdatabase()
        val viewModel = ViewModelProvider(this).get(SectionDetailViewModel::class.java).apply {
           imgByte = sectionImg.getImg(currentSection.img)
           img_section_detail.setImageBitmap(imgByte)
           section_name.text = currentSection.name
           complex_name.text = currentSection.complexName
           chief_name.text = currentSection.personName
        }
    }
}