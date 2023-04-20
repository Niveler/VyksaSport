package com.andreyolenkov.vyksasport.adapter

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andreyolenkov.vyksasport.R
import com.andreyolenkov.vyksasport.models.tuples.SectionModuleTuple
import com.andreyolenkov.vyksasport.ui.screens.section.ListSections
import kotlinx.android.synthetic.main.item_section.view.*

class SectionsAdapter:RecyclerView.Adapter<SectionsAdapter.SectionsViewHolder>() {
    private  var sectionImg= ActionsOnImg()
    private lateinit var imgByte : Bitmap
    private var listSections = emptyList<SectionModuleTuple>()

    class SectionsViewHolder(view: View):RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SectionsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_section,parent,false)
        return SectionsViewHolder(view)
    }

    override fun onBindViewHolder(holder: SectionsViewHolder, position: Int) {
        imgByte = sectionImg.getImg(listSections[position].img)
        holder.itemView.sectionImg.setImageBitmap(imgByte)
        holder.itemView.sectionName.text = listSections[position].name
    }

    override fun getItemCount(): Int {
        return listSections.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<SectionModuleTuple>) {
        listSections = list
        notifyDataSetChanged()
    }

    override fun onViewAttachedToWindow(holder: SectionsViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.itemView.setOnClickListener {
            ListSections.clickOnItem(listSections[holder.adapterPosition])
        }
    }

    override fun onViewDetachedFromWindow(holder: SectionsViewHolder) {
        holder.itemView.setOnClickListener (null)
    }
}