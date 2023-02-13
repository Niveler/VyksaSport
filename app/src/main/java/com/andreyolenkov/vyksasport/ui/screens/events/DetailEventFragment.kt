package com.andreyolenkov.vyksasport.ui.screens.events

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.andreyolenkov.vyksasport.R
import com.andreyolenkov.vyksasport.databinding.FragmentAddEventBinding
import com.andreyolenkov.vyksasport.databinding.FragmentDetailEventBinding
import com.andreyolenkov.vyksasport.models.EventModel
import kotlinx.android.synthetic.main.fragment_detail_event.*

class DetailEventFragment : Fragment() {
    lateinit var binding: FragmentDetailEventBinding
    lateinit var currentEvent: EventModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailEventBinding.inflate(layoutInflater,container,false)
        currentEvent = arguments?.getSerializable("event") as EventModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        val viewModel = ViewModelProvider(this).get(DeleteEventViewModel::class.java).apply {
            tvName.text = currentEvent.name
            tvTimeDate.text = currentEvent.timedate
            tvPlace.text = currentEvent.place
            tvDescription.text = currentEvent.description
        }
    }


}