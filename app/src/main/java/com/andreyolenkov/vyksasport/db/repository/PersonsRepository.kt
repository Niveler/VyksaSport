package com.andreyolenkov.vyksasport.db.repository

import androidx.lifecycle.LiveData
import com.andreyolenkov.vyksasport.models.PersonsModel

interface PersonsRepository {
    val allPersons: LiveData<List<PersonsModel>>
    val allUsers: LiveData<List<PersonsModel>>
    val allTrainers: LiveData<List<PersonsModel>>
    val allAdmins: LiveData<List<PersonsModel>>
    suspend fun insertPerson(personsModel: PersonsModel, onSuccess:()->Unit)
    suspend fun deletePerson(personsModel: PersonsModel, onSuccess:()->Unit)
    suspend fun updatePerson(personsModel: PersonsModel, onSuccess: () -> Unit)
}