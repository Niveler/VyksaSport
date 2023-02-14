package com.andreyolenkov.vyksasport.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.andreyolenkov.vyksasport.models.ComplexModel
import com.andreyolenkov.vyksasport.models.EventModel
import com.andreyolenkov.vyksasport.models.EventModelTuple

@Dao
interface EventsDao {
    @Insert
    fun insert(eventsModel: EventModel)

    @Delete
    fun delete(eventsModel: EventModel)

    @Query("SELECT * FROM events")
    fun getAllComplex(): LiveData<List<EventModel>>

    @Query("SELECT events.id,events.name,events.place,events.timedate, " +
            "complex.name as complex_name, events.description FROM events " +
            "INNER JOIN complex on events.complexId = complex.id")
    fun getAllEventsWithComplexName(): LiveData<List<EventModelTuple>>
}