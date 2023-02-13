package com.andreyolenkov.vyksasport.ui.screens.complex

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.andreyolenkov.vyksasport.APP
import com.andreyolenkov.vyksasport.R
import com.andreyolenkov.vyksasport.adapter.ComplexAdapter
import com.andreyolenkov.vyksasport.databinding.FragmentListComplexBinding
import com.andreyolenkov.vyksasport.models.ComplexModel


class ListComplex : Fragment() {
    lateinit var binding: FragmentListComplexBinding
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: ComplexAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListComplexBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        val viewModel = ViewModelProvider(this).get(ListComplexViewModel::class.java)
        viewModel.initdatabase()
        recyclerView=binding.rvComplex
        adapter = ComplexAdapter()
        recyclerView.adapter=adapter
        viewModel.getAllComplex().observe(viewLifecycleOwner,{listComplex->
            adapter.setList(listComplex.asReversed()) // asReversed - все новые item будут сверху
        })
        binding.btnAddComplex.setOnClickListener{
            //Переходим по нажатию кнопки на экран добавления комплексов
            APP.navController.navigate(R.id.action_listComplex_to_addComplex)
        }
    }
    companion object {
        fun clickComplexItem(complexModel: ComplexModel) {
            val bundle= Bundle()
            bundle.putSerializable("complex", complexModel)
            APP.navController.navigate(R.id.action_listComplex_to_complexDetail,bundle)
        }
    }

}