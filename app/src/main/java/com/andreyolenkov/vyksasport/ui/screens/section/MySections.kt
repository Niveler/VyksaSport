package com.andreyolenkov.vyksasport.ui.screens.section

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.andreyolenkov.vyksasport.R

class MySections : Fragment() {

    companion object {
        fun newInstance() = MySections()
    }

    private lateinit var viewModel: MySectionsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_my_sections, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MySectionsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}