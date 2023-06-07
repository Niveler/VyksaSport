package com.andreyolenkov.vyksasport.ui.screens.persons


import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.andreyolenkov.vyksasport.APP
import com.andreyolenkov.vyksasport.R
import com.andreyolenkov.vyksasport.adapter.PersonsAdapter
import com.andreyolenkov.vyksasport.databinding.FragmentListPersonsBinding
import com.andreyolenkov.vyksasport.models.PersonsModel


class ListPersons : Fragment() {
    lateinit var binding: FragmentListPersonsBinding
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: PersonsAdapter
    private lateinit var viewModel: ListPersonsViewModel
    lateinit var searchView: SearchView
    lateinit var menuItem: MenuItem

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentListPersonsBinding.inflate(layoutInflater,container,false)
        return binding.root
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu,menu)
        menuItem=menu.findItem(R.id.app_bar_search)
        searchView = menuItem.actionView as SearchView
        searchView.queryHint
        searchView.setOnQueryTextListener(object :OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                TODO("Not yet implemented")
            }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[ListPersonsViewModel::class.java]
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }
    private fun init() {
        val viewModel = ViewModelProvider(this)[ListPersonsViewModel::class.java]
        viewModel.initdatabase()
        recyclerView=binding.rvPersons
        adapter = PersonsAdapter()
        recyclerView.adapter = adapter
        viewModel.getAllPersons().observe(viewLifecycleOwner,{listPersons->
            adapter.setList(listPersons)
        })
        binding.btnAddPerson.setOnClickListener {
            APP.navController.navigate(R.id.action_listPersons_to_addPerson)
        }
    }
    companion object {
        fun clickOnItem(personsModel:PersonsModel) {
            val bundle = Bundle()
            bundle.putSerializable("persons",personsModel)
            APP.navController.navigate(R.id.action_listPersons_to_personDetail, bundle)
        }
    }
}