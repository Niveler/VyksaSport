package com.andreyolenkov.vyksasport.adapter

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andreyolenkov.vyksasport.R
import com.andreyolenkov.vyksasport.models.ComplexModel
import com.andreyolenkov.vyksasport.ui.screens.complex.ListComplex
import kotlinx.android.synthetic.main.item_complex.view.*

/**
 * Complex adapter
 *
 * @constructor Create empty Complex adapter
 */
class ComplexAdapter:RecyclerView.Adapter<ComplexAdapter.ComplexViewHolder>() {
    private  var complexImg= ActionsOnImg()
    private lateinit var imgByte : Bitmap
    private var listComplex = emptyList<ComplexModel>()

    /**
     * Complex view holder
     *
     * @constructor
     *
     * @param view
     */
    class ComplexViewHolder (view: View) :RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComplexViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.item_complex,parent,false)
        return ComplexViewHolder(view)
    }

    override fun onBindViewHolder(holder: ComplexViewHolder, position: Int) {
        imgByte = complexImg.getImg(listComplex[position].img)//собираем картинку из набора байт
        holder.itemView.title_complex.text = listComplex[position].name
        holder.itemView.img_complex.setImageBitmap(imgByte) //добалвяем картинку
    }

    override fun getItemCount(): Int {
        return listComplex.size
    }

    /**
     * Set list
     *Отслеживает изменения в списке
     * @param list
     */
    @SuppressLint("NotifyDataSetChanged")
    fun setList(list:List<ComplexModel>){
        listComplex=list
        notifyDataSetChanged()
    }

    override fun onViewAttachedToWindow(holder: ComplexViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.itemView.setOnClickListener {
            ListComplex.clickComplexItem(listComplex[holder.adapterPosition])
        }
    }

    override fun onViewDetachedFromWindow(holder: ComplexViewHolder) {
        holder.itemView.setOnClickListener (null)
    }
}