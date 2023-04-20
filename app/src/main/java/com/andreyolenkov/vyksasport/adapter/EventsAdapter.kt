package com.andreyolenkov.vyksasport.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andreyolenkov.vyksasport.R
import com.andreyolenkov.vyksasport.models.tuples.EventModelTuple
import com.andreyolenkov.vyksasport.ui.screens.events.ListEvents
import kotlinx.android.synthetic.main.item_events.view.*

class EventsAdapter:RecyclerView.Adapter<EventsAdapter.EventsViewHolder>() {
    private var listEvents = emptyList<EventModelTuple>()
    class EventsViewHolder(view:View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_events,parent,false)
        return EventsViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventsViewHolder, position: Int) {
        holder.itemView.event_name.text = listEvents[position].name
        holder.itemView.event_timedate.text = listEvents[position].timedate
        holder.itemView.event_place.text = listEvents[position].place
        holder.itemView.event_description.text = listEvents[position].description
        holder.itemView.event_complex.text = listEvents[position].complexName
    }

    override fun getItemCount(): Int {
        return listEvents.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<EventModelTuple>) {
        listEvents = list
        notifyDataSetChanged()
    }

    override fun onViewAttachedToWindow(holder: EventsViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.itemView.setOnClickListener {
            ListEvents.clickEventItem(listEvents[holder.adapterPosition])
        }
    }

    override fun onViewDetachedFromWindow(holder: EventsViewHolder) {
        holder.itemView.setOnClickListener (null)
    }
}