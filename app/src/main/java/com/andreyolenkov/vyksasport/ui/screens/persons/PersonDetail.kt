package com.andreyolenkov.vyksasport.ui.screens.persons

import android.graphics.Bitmap
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.andreyolenkov.vyksasport.R
import com.andreyolenkov.vyksasport.adapter.ActionsOnImg
import com.andreyolenkov.vyksasport.databinding.FragmentPersonDetailBinding
import com.andreyolenkov.vyksasport.models.PersonsModel
import kotlinx.android.synthetic.main.fragment_person_detail.cbAdmin
import kotlinx.android.synthetic.main.fragment_person_detail.cbTrainer
import kotlinx.android.synthetic.main.fragment_person_detail.etEmail
import kotlinx.android.synthetic.main.fragment_person_detail.etFamile
import kotlinx.android.synthetic.main.fragment_person_detail.etName
import kotlinx.android.synthetic.main.fragment_person_detail.etPhone
import kotlinx.android.synthetic.main.fragment_person_detail.imgPerson

class PersonDetail : Fragment() {
    lateinit var binding: FragmentPersonDetailBinding
    private var personImg=ActionsOnImg()
    private lateinit var imgByte:Bitmap
    lateinit var currentPerson: PersonsModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentPersonDetailBinding.inflate(layoutInflater,container,false)
        currentPerson=arguments?.getSerializable("persons") as PersonsModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        val viewModel = ViewModelProvider(this).get(PersonDetailViewModel::class.java).apply {
            imgByte = personImg.getImg(currentPerson.img)
            imgPerson.setImageBitmap(imgByte)
            etFamile.text = currentPerson.famile
            etName.text = currentPerson.name
            etEmail.text = currentPerson.email
            etPhone.text = currentPerson.phone
            if (currentPerson.role == 1) {
                cbTrainer.isChecked = true
            } else if (currentPerson.role ==2) {
                cbAdmin.isChecked = true
            }
        }
        binding.btnEditPerson.setOnClickListener {
            if (cbTrainer.isChecked) {
                viewModel.update(PersonsModel(famile = currentPerson.famile, name = currentPerson.name,
                    email = currentPerson.email, phone = currentPerson.phone,
                    role = 1, img = personImg.writeImg(imgByte))){}
            } else if (cbAdmin.isChecked) {
                viewModel.update(PersonsModel(famile = currentPerson.famile, name = currentPerson.name,
                    email = currentPerson.email, phone = currentPerson.phone,
                    role = 2, img = personImg.writeImg(imgByte))){}
            }
        }
    }

}