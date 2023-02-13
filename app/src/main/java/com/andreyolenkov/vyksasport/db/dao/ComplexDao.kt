package com.andreyolenkov.vyksasport.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.andreyolenkov.vyksasport.models.ComplexModel
import io.reactivex.Single


@Dao
interface ComplexDao {
    @Insert
    fun insert(complexModel: ComplexModel) : Long

    @Delete
    fun delete(complexModel: ComplexModel)

    @Query("SELECT * FROM complex")
    fun getAllComplex():LiveData<List<ComplexModel>>

    @Query("SELECT * FROM complex")
    fun getAllComplexData():List<ComplexModel>

    @Query("SELECT * FROM complex WHERE name = :name ")
    fun getComplexByName(name: String):List<ComplexModel>
}