package com.andreyolenkov.vyksasport.ui.screens.events

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.andreyolenkov.vyksasport.R

class DeleteEventFragment : Fragment() {

    companion object {
        fun newInstance() = DeleteEventFragment()
    }

    private lateinit var viewModel: DeleteEventViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_delete_event, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DeleteEventViewModel::class.java)
        // TODO: Use the ViewModel
    }

}