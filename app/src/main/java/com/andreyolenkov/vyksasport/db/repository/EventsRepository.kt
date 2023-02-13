package com.andreyolenkov.vyksasport.db.repository

import androidx.lifecycle.LiveData
import com.andreyolenkov.vyksasport.models.EventModel

interface EventsRepository {
    val allEvents: LiveData<List<EventModel>>

    suspend fun insertEvent(eventModel: EventModel,onSuccess:()->Unit)
    suspend fun deleteEvent(eventModel: EventModel,onSuccess:()->Unit)

}