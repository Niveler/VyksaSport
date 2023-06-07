package com.andreyolenkov.vyksasport.ui.screens.section

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.andreyolenkov.vyksasport.APP
import com.andreyolenkov.vyksasport.R
import com.andreyolenkov.vyksasport.adapter.SectionsAdapter
import com.andreyolenkov.vyksasport.databinding.FragmentListSectionBinding
import com.andreyolenkov.vyksasport.models.tuples.SectionModuleTuple


class ListSections : Fragment() {
    lateinit var binding: FragmentListSectionBinding
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: SectionsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentListSectionBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        /*val sourse:ImageDecoder.Source = if (Build.VERSION.SDK_INT >= P) {
            //Задаем источник изображения
            ImageDecoder.createSource(
                resources, R.drawable.badminton
            )
        } else {
            TODO("VERSION.SDK_INT < $P")
        }
        val drawable:Drawable = ImageDecoder.decodeDrawable(sourse)
        binding.imageView.setImageDrawable(drawable)
        (drawable as? AnimatedImageDrawable)?.start()// анимирует гиф изображение*/
    }

    private fun init() {
        val viewModel = ViewModelProvider(this).get(ListSectionViewModel::class.java)
        viewModel.initdatabase()
        recyclerView=binding.rvSections
        adapter = SectionsAdapter()
        recyclerView.adapter=adapter
        viewModel.getAllSectionsWithNames().observe(viewLifecycleOwner) { listSections ->
            adapter.setList(listSections)
        }
    }
    companion object {
        fun clickOnItem(sectionModel:SectionModuleTuple) {
            val bundle = Bundle()
            bundle.putSerializable("section",sectionModel)
            APP.navController.navigate(R.id.action_listSectionFragment_to_section_detail,bundle)
        }
    }

}