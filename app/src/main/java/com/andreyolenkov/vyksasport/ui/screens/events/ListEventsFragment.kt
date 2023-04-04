package com.andreyolenkov.vyksasport.ui.screens.events

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andreyolenkov.vyksasport.APP
import com.andreyolenkov.vyksasport.R
import com.andreyolenkov.vyksasport.adapter.EventsAdapter
import com.andreyolenkov.vyksasport.databinding.FragmentListEventsBinding
import com.andreyolenkov.vyksasport.models.tuples.EventModelTuple

class ListEventsFragment : Fragment() {
    lateinit var binding: FragmentListEventsBinding
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: EventsAdapter
    private lateinit var viewModel: ListEventsViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListEventsBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ListEventsViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        val viewModel = ViewModelProvider(this).get(ListEventsViewModel::class.java)
        viewModel.initdatabase()
        recyclerView=binding.rvEvents
        adapter = EventsAdapter()
        recyclerView.adapter = adapter
        /*viewModel.getAllEvents().observe(viewLifecycleOwner,{listEvents->
            adapter.setList(listEvents.asReversed())
        })*/
        viewModel.getAllAventsComplName().observe(viewLifecycleOwner,{listEvents->
            adapter.setList(listEvents.asReversed())
        })
        binding.btnAddEvent.setOnClickListener {
            APP.navController.navigate(R.id.action_listEventsFragment_to_addEventFragment)
        }
    }

    companion object {
        fun clickEventItem(eventsModel: EventModelTuple) {
            val bundle = Bundle()
            bundle.putSerializable("event",eventsModel)
            APP.navController.navigate(R.id.action_listEventsFragment_to_detailEventFragment,bundle)
        }
    }
}