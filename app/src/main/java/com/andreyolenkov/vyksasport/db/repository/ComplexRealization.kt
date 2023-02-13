package com.andreyolenkov.vyksasport.db.repository

import androidx.lifecycle.LiveData
import com.andreyolenkov.vyksasport.db.dao.ComplexDao
import com.andreyolenkov.vyksasport.models.ComplexModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ComplexRealization (private val complexDao: ComplexDao): ComplexRepository{

    override val allComplex: LiveData<List<ComplexModel>>
        get() = complexDao.getAllComplex()

    override val allComplexData: List<ComplexModel>
        get() = complexDao.getAllComplexData()

    override suspend fun getComplexByName(name: String) {
        withContext(Dispatchers.IO) {
            complexDao.getComplexByName(name)
        }
    }

    override suspend fun insertComplex(complexModel: ComplexModel, onSuccess: () -> Unit): Long {
        return  complexDao.insert(complexModel)
      }

    override suspend fun deleteComplex(complexModel: ComplexModel, onSuccess: () -> Unit) {
        complexDao.delete(complexModel)
        onSuccess()
    }
}