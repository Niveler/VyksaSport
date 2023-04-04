package com.andreyolenkov.vyksasport.ui.screens.complex

import android.graphics.Bitmap
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.andreyolenkov.vyksasport.adapter.ActionsOnImg
import com.andreyolenkov.vyksasport.databinding.FragmentComplexDetailBinding
import com.andreyolenkov.vyksasport.models.ComplexModel
import kotlinx.android.synthetic.main.fragment_complex_detail.*


class ComplexDetail : Fragment() {
    private  var complexImg= ActionsOnImg()
    private lateinit var imgByte : Bitmap
    lateinit var binding:FragmentComplexDetailBinding
    lateinit var curentComplex: ComplexModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentComplexDetailBinding.inflate(layoutInflater,container,false)
        curentComplex=arguments?.getSerializable("complex") as ComplexModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        val viewModel = ViewModelProvider(this).get(DetailComplexViewModel::class.java).apply{
            imgByte = complexImg.getImg(curentComplex.img)
            img_complex_detail.setImageBitmap(imgByte)
            complex_name.text = curentComplex.name
            complex_operation_mode.text = curentComplex.operationMode
            complex_adress.text = curentComplex.adress
            complex_phone.text = curentComplex.number
            complex_email.text = curentComplex.email
        }
    }
}