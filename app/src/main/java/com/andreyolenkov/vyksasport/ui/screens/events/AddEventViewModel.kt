package com.andreyolenkov.vyksasport.ui.screens.events

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andreyolenkov.vyksasport.REPOSITORY_COMPLEX
import com.andreyolenkov.vyksasport.REPOSITORY_EVENTS
import com.andreyolenkov.vyksasport.models.ComplexModel
import com.andreyolenkov.vyksasport.models.EventModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddEventViewModel : ViewModel() {
    fun insert(eventModel: EventModel, onSuccess:()->Unit) {
        viewModelScope.launch (Dispatchers.IO){
            REPOSITORY_EVENTS.insertEvent(eventModel) {
                onSuccess()
            }
        }
    }
}