package com.andreyolenkov.vyksasport.db.dao

import androidx.core.location.LocationRequestCompat.Quality
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.andreyolenkov.vyksasport.models.ComplexModel
import com.andreyolenkov.vyksasport.models.SectionsModel
import com.andreyolenkov.vyksasport.models.tuples.SectionModuleTuple

@Dao
interface SectionsDao {
    @Insert
    fun  insert(sectionsModel: SectionsModel) : Long

    @Delete
    fun delete(sectionsModel: SectionsModel)

    @Query("SELECT * FROM sections")
    fun getAllSections():LiveData<List<SectionsModel>>

    @Query("SELECT sections.id,sections.name,sections.img,complex.name as complex_name,  persons.name as person_name FROM sections " +
            "INNER JOIN complex on sections.complexId=complex.id " +
            "INNER JOIN persons on sections.person_id=persons.id ")
    fun getAllSectionByName(): LiveData<List<SectionModuleTuple>>

    @Query("SELECT sections.id,sections.name,sections.img,complex.name as complex_name,  persons.name as person_name FROM sections " +
            "INNER JOIN complex on sections.complexId=complex.id "  +
            "INNER JOIN persons on sections.person_id=persons.id " +
            "WHERE sections.person_id = :idPerson")
    fun getMySections(idPerson:Long) : LiveData<List<SectionModuleTuple>>
}