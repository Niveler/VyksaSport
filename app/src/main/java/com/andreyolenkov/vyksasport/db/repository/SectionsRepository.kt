package com.andreyolenkov.vyksasport.db.repository

import androidx.lifecycle.LiveData
import com.andreyolenkov.vyksasport.models.SectionsModel
import com.andreyolenkov.vyksasport.models.tuples.SectionModuleTuple
import io.reactivex.internal.operators.single.SingleDoAfterSuccess

interface SectionsRepository {
    val allSections: LiveData<List<SectionsModel>>
    val allSectionsByNameComplex: LiveData<List<SectionModuleTuple>>
    val allMySections: LiveData<List<SectionModuleTuple>>
    suspend fun insertSection(sectionsModel: SectionsModel, onSuccess:()->Unit) : Long
    suspend fun deleteSection(sectionsModel: SectionsModel, onSuccess:()->Unit)
}