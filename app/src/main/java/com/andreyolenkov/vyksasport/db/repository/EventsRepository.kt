package com.andreyolenkov.vyksasport.db.repository

import androidx.lifecycle.LiveData
import com.andreyolenkov.vyksasport.models.EventModel
import com.andreyolenkov.vyksasport.models.EventModelTuple

interface EventsRepository {
    val allEvents: LiveData<List<EventModel>>
    val allEventsWithComplexName:LiveData<List<EventModelTuple>>
    suspend fun insertEvent(eventModel: EventModel,onSuccess:()->Unit)
    suspend fun deleteEvent(eventModel: EventModel,onSuccess:()->Unit)

}