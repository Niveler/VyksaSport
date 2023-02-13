package com.andreyolenkov.vyksasport.db.repository

import androidx.lifecycle.LiveData
import com.andreyolenkov.vyksasport.models.ComplexModel
import io.reactivex.Single

interface ComplexRepository {
    val allComplex: LiveData<List<ComplexModel>>
    val allComplexData : List<ComplexModel>
    suspend fun getComplexByName(name: String)
    suspend fun insertComplex(complexModel: ComplexModel,onSuccess:()->Unit): Long
    suspend fun deleteComplex(complexModel: ComplexModel,onSuccess:()->Unit)
}