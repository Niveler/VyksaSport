package com.andreyolenkov.vyksasport.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.andreyolenkov.vyksasport.models.PersonsModel

@Dao
interface PersonsDao {
    @Insert
    fun insert(personsModel: PersonsModel)

    @Delete
    fun delete(personsModel: PersonsModel)

    @Update
    fun update(personsModel: PersonsModel)

    @Query ("SELECT * FROM persons")
    fun getAllPersons():LiveData<List<PersonsModel>>

    @Query ("SELECT * FROM persons WHERE role = 0")
    fun getAllUsers():LiveData<List<PersonsModel>>

    @Query ("SELECT * FROM persons WHERE role = 1")
    fun getAllTrainer():LiveData<List<PersonsModel>>

    @Query ("SELECT * FROM persons WHERE role = 2")
    fun getAllAdmin():LiveData<List<PersonsModel>>
}