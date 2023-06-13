package com.andreyolenkov.vyksasport.db.repository

import androidx.lifecycle.LiveData
import com.andreyolenkov.vyksasport.db.dao.SectionsDao
import com.andreyolenkov.vyksasport.models.SectionsModel
import com.andreyolenkov.vyksasport.models.tuples.SectionModuleTuple
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SectionsRealization (private val sectionsDao: SectionsDao):SectionsRepository {

    private var id =0
    override val allSections: LiveData<List<SectionsModel>>
        get() = sectionsDao.getAllSections()
    override val allSectionsByNameComplex: LiveData<List<SectionModuleTuple>>
        get() = sectionsDao.getAllSectionByName()
    override val allMySections: LiveData<List<SectionModuleTuple>>
        get() = sectionsDao.getMySections(id.toLong())

    override suspend fun insertSection(sectionsModel: SectionsModel, onSuccess: () -> Unit): Long {
       return sectionsDao.insert(sectionsModel)
    }

    override suspend fun deleteSection(sectionsModel: SectionsModel, onSuccess: () -> Unit) {
        sectionsDao.delete(sectionsModel)
        onSuccess()
    }

}