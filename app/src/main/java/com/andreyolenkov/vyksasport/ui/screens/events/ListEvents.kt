package com.andreyolenkov.vyksasport.ui.screens.events

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.SearchView.OnQueryTextListener
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.andreyolenkov.vyksasport.APP
import com.andreyolenkov.vyksasport.R
import com.andreyolenkov.vyksasport.adapter.EventsAdapter
import com.andreyolenkov.vyksasport.databinding.FragmentListEventsBinding
import com.andreyolenkov.vyksasport.models.tuples.EventModelTuple


class ListEvents : Fragment() {
    lateinit var binding: FragmentListEventsBinding
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: EventsAdapter
    private lateinit var viewModel: ListEventsViewModel
    lateinit var searchView: SearchView
    lateinit var menuItem: MenuItem

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListEventsBinding.inflate(layoutInflater,container,false)
        return binding.root

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }
    //Инициализация меню поиска
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)
        menuItem=menu.findItem(R.id.app_bar_search)
        searchView = menuItem.actionView as SearchView
        searchView.queryHint
        searchView.setOnQueryTextListener(object : OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                //поиск по каждой букве
                //сюда дописать запрос в лист адаптер
                init()
                return true
            }

        })
        super.onCreateOptionsMenu(menu, inflater)

    }
    //Слушатель нажатий меню сверху
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId) {
            R.id.app_bar_search -> Toast.makeText(context,"Введите искомый текст", Toast.LENGTH_LONG).show()
        }
        return true
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
        viewModel.getAllAventsComplName().observe(viewLifecycleOwner) { listEvents ->
            adapter.setList(listEvents.asReversed())
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