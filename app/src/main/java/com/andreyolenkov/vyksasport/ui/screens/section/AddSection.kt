package com.andreyolenkov.vyksasport.ui.screens.section

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import com.andreyolenkov.vyksasport.APP
import com.andreyolenkov.vyksasport.R
import com.andreyolenkov.vyksasport.adapter.ActionsOnImg
import com.andreyolenkov.vyksasport.databinding.FragmentAddSectionBinding
import com.andreyolenkov.vyksasport.models.SectionsModel
import com.andreyolenkov.vyksasport.ui.screens.complex.ListComplexViewModel
import com.andreyolenkov.vyksasport.ui.screens.persons.ListPersonsViewModel


class AddSection : Fragment() {
    lateinit var binding: FragmentAddSectionBinding
    lateinit var imgSection: Bitmap
    private var imgRequestCode = 1
    private var listComplexName = ArrayList<String>()
    private var listTrainerFio = ArrayList<String>()
    private var complexName = ""
    private var complexId = 0
    private var personName = ""
    private var personId = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentAddSectionBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        openImg()
        spinComplex()
        spinChiefs()
        init()
    }

    private fun init() {
        val viewModel = ViewModelProvider(this)[AddSectionViewModel::class.java]
        val viewModel1 = ViewModelProvider(this)[ListSectionViewModel::class.java]
        viewModel1.initdatabase()
        val sectionImg = ActionsOnImg()
        binding.btnAddSection.setOnClickListener {
            val sectionName = binding.sectionName.text.toString()
            val compId = complexId
            val persId = personId
            viewModel.insert(SectionsModel(img=sectionImg.writeImg(imgSection),name = sectionName, complexId = compId, person_id = persId)){}
            APP.navController.navigate(R.id.action_addSection_to_listSectionFragment)
        }
    }


    private fun openImg() {
        binding.imgSectionAdd.setOnClickListener {
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            intent.type = "image/*"
            startActivityForResult(intent, imgRequestCode)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode==Activity.RESULT_OK && requestCode==imgRequestCode) {
            binding.imgSectionAdd.setImageURI(data?.data)
            binding.imgSectionAdd.buildDrawingCache()
            imgSection=binding.imgSectionAdd.drawingCache
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
            binding.spinnerComplex.adapter = arrayAdapter
            //Обработчик выбора элемента спиннера
            binding.spinnerComplex.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    //Берем название комплекса
                    complexName = parent?.getItemAtPosition(position).toString()

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
    private fun spinChiefs() {
        val viewModel = ViewModelProvider(this)[ListPersonsViewModel::class.java]
        viewModel.initdatabase()
        viewModel.getAllTrainers().observe(viewLifecycleOwner) {listTrainer ->
            listTrainer.forEach {item -> listTrainerFio.add(item.famile + " " + item.name) }
            val arrayAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, listTrainerFio)
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            arrayAdapter.notifyDataSetChanged()
            binding.spinnerChiefs.adapter = arrayAdapter
            binding.spinnerChiefs.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    personName = parent?.getItemAtPosition(position).toString()
                    val tempListPerson = listTrainer.find { it.famile + " " + it.name==personName}
                    if (tempListPerson != null) {
                        personId = tempListPerson.id
                    }
                }
                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }
        }
    }
}