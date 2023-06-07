package com.andreyolenkov.vyksasport.ui.screens.persons

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andreyolenkov.vyksasport.REPOSITORY_PERSONS
import com.andreyolenkov.vyksasport.models.PersonsModel
import io.reactivex.internal.operators.single.SingleDoOnSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PersonDetailViewModel:ViewModel() {
    fun update(personsModel: PersonsModel, onSuccess:() -> Unit) {
        viewModelScope.launch (Dispatchers.IO){
            REPOSITORY_PERSONS!!.updatePerson(personsModel) {
                onSuccess()
            }
        }
    }
}