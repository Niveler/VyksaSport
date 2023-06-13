package com.andreyolenkov.vyksasport.ui.screens.section

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andreyolenkov.vyksasport.APP
import com.andreyolenkov.vyksasport.R
import com.andreyolenkov.vyksasport.adapter.SectionsAdapter
import com.andreyolenkov.vyksasport.databinding.FragmentAddGroupBinding
import com.andreyolenkov.vyksasport.databinding.FragmentMySectionsBinding
import com.andreyolenkov.vyksasport.models.SectionsModel
import com.andreyolenkov.vyksasport.models.tuples.SectionModuleTuple

class MySections : Fragment() {
    lateinit var binding: FragmentMySectionsBinding
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: SectionsAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentMySectionsBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        val viewModel = ViewModelProvider(this)[ListSectionViewModel::class.java]
        viewModel.initdatabase()
        recyclerView=binding.rvMySections
        adapter = SectionsAdapter()
        recyclerView.adapter=adapter
        viewModel.getAllMySections().observe(viewLifecycleOwner) {listMySections->
            adapter.setList(listMySections)
        }
    }
    companion object {
        fun clickOnItem(sectionsModel: SectionModuleTuple) {
            val bundle = Bundle()
            bundle.putSerializable("mysections",sectionsModel)
            APP.navController.navigate(R.id.action_mySections_to_timeTable)
        }
    }

}