package com.andreyolenkov.vyksasport.ui.screens.persons

import android.app.Activity
import android.content.Intent
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
import com.andreyolenkov.vyksasport.databinding.FragmentAddPersonBinding
import com.andreyolenkov.vyksasport.models.PersonsModel


class AddPerson : Fragment() {
    private lateinit var binding: FragmentAddPersonBinding
    private lateinit var imgPerson: Bitmap
    private var imgRequestCode = 1
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentAddPersonBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        openImg()
    }

    private fun init() {
        val vmPerson = ViewModelProvider(this).get(AddPersonViewModel::class.java)
        binding.btnAddPerson.setOnClickListener{
            val personFamile = binding.etAddFamile.text.toString()
            val personName = binding.etAddName.text.toString()
            val personEmail = binding.etAddEmail.text.toString()
            val personPhone = binding.etAddPhone.text.toString()
            val personRole: Int
            val personImg = ActionsOnImg()
            if (binding.cbTrainer.isChecked) {
                personRole = 1
                vmPerson.insert(PersonsModel(famile = personFamile, name = personName,
                    email = personEmail, phone = personPhone, role = personRole, img = personImg.writeImg(imgPerson))){}
            } else if(binding.cbAdmin.isChecked) {
                personRole = 2
                vmPerson.insert(PersonsModel(
                    famile = personFamile, name = personName,
                    email = personEmail, phone = personPhone, role = personRole, img = personImg.writeImg(imgPerson))){}
            } else if (!binding.cbTrainer.isChecked && !binding.cbAdmin.isChecked) {
                personRole = 0
                vmPerson.insert(PersonsModel(famile = personFamile, name = personName,
                    email = personEmail, phone = personPhone, role = personRole, img = personImg.writeImg(imgPerson))){}
            }
            APP.navController.navigate(R.id.action_addPerson_to_listPersons)
        }
    }
    private fun openImg() {
        binding.imgAddPerson.setOnClickListener {
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            intent.type = "image/*"
            startActivityForResult(intent,imgRequestCode)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == imgRequestCode) {
            binding.imgAddPerson.setImageURI(data?.data)
            binding.imgAddPerson.buildDrawingCache()
            imgPerson=binding.imgAddPerson.drawingCache
        }
    }
}