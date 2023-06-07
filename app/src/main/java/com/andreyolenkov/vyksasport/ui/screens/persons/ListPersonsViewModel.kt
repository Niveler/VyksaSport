package com.andreyolenkov.vyksasport.ui.screens.persons

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.andreyolenkov.vyksasport.REPOSITORY_PERSONS
import com.andreyolenkov.vyksasport.db.ComplexDatabase
import com.andreyolenkov.vyksasport.db.repository.PersonsRealization
import com.andreyolenkov.vyksasport.models.PersonsModel

class ListPersonsViewModel(application: Application):AndroidViewModel(application) {
    val context = application
    fun initdatabase() {
        val daoPersons = ComplexDatabase.getInstance(context).getPersonsDao()
        REPOSITORY_PERSONS = PersonsRealization(daoPersons)
    }
    fun getAllPersons(): LiveData<List<PersonsModel>> {
        return REPOSITORY_PERSONS!!.allPersons
    }
    fun getAllUsers():LiveData<List<PersonsModel>> {
        return REPOSITORY_PERSONS!!.allUsers
    }
    fun getAllTrainers():LiveData<List<PersonsModel>> {
        return REPOSITORY_PERSONS!!.allTrainers
    }
    fun getAllAdmins():LiveData<List<PersonsModel>> {
        return REPOSITORY_PERSONS!!.allAdmins
    }
}