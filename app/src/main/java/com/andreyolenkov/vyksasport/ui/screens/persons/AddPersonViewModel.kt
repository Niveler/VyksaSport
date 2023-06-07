package com.andreyolenkov.vyksasport.ui.screens.persons

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andreyolenkov.vyksasport.REPOSITORY_PERSONS
import com.andreyolenkov.vyksasport.models.PersonsModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddPersonViewModel:ViewModel() {
    fun insert(personsModel: PersonsModel,onSuccess:()->Unit) {
        viewModelScope.launch (Dispatchers.IO){
            REPOSITORY_PERSONS!!.insertPerson(personsModel) {
                onSuccess()
            }
        }
    }
}