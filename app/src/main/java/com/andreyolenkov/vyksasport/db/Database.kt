package com.andreyolenkov.vyksasport.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.andreyolenkov.vyksasport.db.dao.CalendarDao
import com.andreyolenkov.vyksasport.db.dao.ComplexDao
import com.andreyolenkov.vyksasport.db.dao.EventsDao
import com.andreyolenkov.vyksasport.db.dao.GroupsDao
import com.andreyolenkov.vyksasport.db.dao.PersonGroupDao
import com.andreyolenkov.vyksasport.db.dao.PersonsDao
import com.andreyolenkov.vyksasport.db.dao.SectionsDao
import com.andreyolenkov.vyksasport.db.dao.WriteSectionDao
import com.andreyolenkov.vyksasport.models.*

@Database(entities = [
    ComplexModel::class,
    EventModel::class,
    SectionsModel::class,
    CalendarModel::class,
    GroupsModel::class,
    PersonsModel::class,
    PersonGroupModel::class,
    WritSectionModel::class
                     ], version = 1)
abstract class ComplexDatabase: RoomDatabase() {
    abstract fun getCalendarDa0(): CalendarDao
    abstract fun getComplexDao(): ComplexDao
    abstract fun getEventsDao():EventsDao
    abstract fun getGroupsDao():GroupsDao
    abstract fun getPersonGroupDao():PersonGroupDao
    abstract fun getSectionsDao():SectionsDao
    abstract fun getPersonsDao(): PersonsDao
    abstract fun getWritSection():WriteSectionDao

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