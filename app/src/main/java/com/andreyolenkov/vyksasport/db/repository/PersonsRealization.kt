package com.andreyolenkov.vyksasport.db.repository

import androidx.lifecycle.LiveData
import com.andreyolenkov.vyksasport.db.dao.PersonsDao
import com.andreyolenkov.vyksasport.models.PersonsModel

class PersonsRealization (private val personsDao: PersonsDao):PersonsRepository{

    override val allUsers: LiveData<List<PersonsModel>>
        get() = personsDao.getAllUsers()

    override val allPersons: LiveData<List<PersonsModel>>
        get() = personsDao.getAllPersons()

    override val allTrainers: LiveData<List<PersonsModel>>
        get() = personsDao.getAllTrainer()

    override val allAdmins: LiveData<List<PersonsModel>>
        get() = personsDao.getAllAdmin()

    override suspend fun insertPerson(personsModel: PersonsModel, onSuccess: () -> Unit) {
        return personsDao.insert(personsModel)
    }

    override suspend fun deletePerson(personsModel: PersonsModel, onSuccess: () -> Unit) {
        personsDao.delete(personsModel)
        onSuccess()
    }

    override suspend fun updatePerson(personsModel: PersonsModel, onSuccess: () -> Unit) {
        personsDao.update(personsModel)
        onSuccess()
    }
}