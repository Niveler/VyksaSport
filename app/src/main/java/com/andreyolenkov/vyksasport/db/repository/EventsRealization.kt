package com.andreyolenkov.vyksasport.db.repository

import androidx.lifecycle.LiveData
import com.andreyolenkov.vyksasport.db.dao.EventsDao
import com.andreyolenkov.vyksasport.models.EventModel
import com.andreyolenkov.vyksasport.models.tuples.EventModelTuple

class EventsRealization (private val eventsDao: EventsDao):EventsRepository{
    override val allEvents: LiveData<List<EventModel>>
        get() = eventsDao.getAllComplex()

    override val allEventsWithComplexName: LiveData<List<EventModelTuple>>
        get() = eventsDao.getAllEventsWithComplexName()

    override suspend fun insertEvent(eventModel: EventModel, onSuccess: () -> Unit) {
        return eventsDao.insert(eventModel)
    }

    override suspend fun deleteEvent(eventModel: EventModel, onSuccess: () -> Unit) {
        eventsDao.delete(eventModel)
        onSuccess()
    }
}