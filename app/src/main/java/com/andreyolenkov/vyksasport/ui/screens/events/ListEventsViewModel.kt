package com.andreyolenkov.vyksasport.ui.screens.events

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.andreyolenkov.vyksasport.REPOSITORY_EVENTS
import com.andreyolenkov.vyksasport.db.ComplexDatabase
import com.andreyolenkov.vyksasport.db.repository.EventsRealization
import com.andreyolenkov.vyksasport.models.EventModel
import com.andreyolenkov.vyksasport.models.EventModelTuple

class ListEventsViewModel (application: Application):AndroidViewModel(application) {
    val context = application
    fun initdatabase(){
        val daoEvents=ComplexDatabase.getInstance(context).getEventsDao()
        REPOSITORY_EVENTS=EventsRealization(daoEvents)
    }
    fun getAllEvents():LiveData<List<EventModel>>{
        return REPOSITORY_EVENTS.allEvents
    }
    fun getAllAventsComplName():LiveData<List<EventModelTuple>> {
        return REPOSITORY_EVENTS.allEventsWithComplexName
    }
}