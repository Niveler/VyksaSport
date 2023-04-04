package com.andreyolenkov.vyksasport.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.andreyolenkov.vyksasport.db.dao.ComplexDao
import com.andreyolenkov.vyksasport.db.dao.EventsDao
import com.andreyolenkov.vyksasport.db.dao.SectionsDao
import com.andreyolenkov.vyksasport.models.*

@Database(entities = [
    ComplexModel::class,
    EventModel::class,
    SectionsModel::class,
    CalendarModel::class,
    ChiefsModel::class,
    GroupsModel::class,
    PersonsModel::class
                     ], version = 1)
abstract class ComplexDatabase: RoomDatabase() {
    abstract fun getComplexDao(): ComplexDao
    abstract fun getEventsDao():EventsDao
    abstract fun getSectionsDao():SectionsDao

    companion object {
        private var database:ComplexDatabase ?=null
        @Synchronized
        fun getInstance(context: Context):ComplexDatabase {
            return if(database==null) {
                database = Room.databaseBuilder(context,ComplexDatabase::class.java,"db").build()
                database as ComplexDatabase
            } else {
                database as ComplexDatabase
            }
        }
    }
}