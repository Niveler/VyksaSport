package com.andreyolenkov.vyksasport.db.repository

import androidx.lifecycle.LiveData
import com.andreyolenkov.vyksasport.db.dao.SectionsDao
import com.andreyolenkov.vyksasport.models.SectionsModel
import com.andreyolenkov.vyksasport.models.tuples.SectionModuleTuple
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SectionsRealization (private val sectionsDao: SectionsDao):SectionsRepository {


    override val allSections: LiveData<List<SectionsModel>>
        get() = sectionsDao.getAllSections()
    override val allSectionsByNameComplex: LiveData<List<SectionModuleTuple>>
        get() = sectionsDao.getAllSectionByName()

    override suspend fun insertSection(sectionsModel: SectionsModel, onSuccess: () -> Unit) {
       return sectionsDao.insert(sectionsModel)
    }

    override suspend fun deleteSection(sectionsModel: SectionsModel, onSuccess: () -> Unit) {
        sectionsDao.delete(sectionsModel)
        onSuccess()
    }

}