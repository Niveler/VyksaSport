package com.andreyolenkov.vyksasport.adapter

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andreyolenkov.vyksasport.R
import com.andreyolenkov.vyksasport.models.PersonsModel
import com.andreyolenkov.vyksasport.ui.screens.persons.ListPersons
import kotlinx.android.synthetic.main.item_person.view.imgPerson
import kotlinx.android.synthetic.main.item_person.view.tvFIO
import kotlinx.android.synthetic.main.item_person.view.tvPhone
import kotlinx.android.synthetic.main.item_person.view.tvRole

class PersonsAdapter:RecyclerView.Adapter<PersonsAdapter.PersonsViewHolder>() {
    private var personImg = ActionsOnImg()
    private lateinit var imgByte: Bitmap
    private var listPersons = emptyList<PersonsModel>()
    class PersonsViewHolder(view: View):RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_person,parent,false)
        return PersonsViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: PersonsViewHolder, position: Int) {
        imgByte = personImg.getImg(listPersons[position].img)
        holder.itemView.imgPerson.setImageBitmap(imgByte)
        holder.itemView.tvFIO.text = listPersons[position].famile + " " + listPersons[position].name
        if (listPersons[position].role == 0) {
             holder.itemView.tvRole.text = "Пользователь"
        } else if  (listPersons[position].role == 1) {
            holder.itemView.tvRole.text = "Тренер"
        } else if  (listPersons[position].role == 2) {
            holder.itemView.tvRole.text = "Администратор"
        }
        holder.itemView.tvPhone.text = listPersons[position].phone
    }

    override fun getItemCount(): Int {
        return listPersons.size
    }
    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<PersonsModel>) {
        listPersons = list
        notifyDataSetChanged()
    }

    override fun onViewAttachedToWindow(holder: PersonsViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.itemView.setOnClickListener {
            ListPersons.clickOnItem(listPersons[holder.adapterPosition])
        }
    }

    override fun onViewDetachedFromWindow(holder: PersonsViewHolder) {
        holder.itemView.setOnClickListener  (null)
    }
}