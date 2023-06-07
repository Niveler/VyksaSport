package com.andreyolenkov.vyksasport.ui.screens.section

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andreyolenkov.vyksasport.REPOSITORY_SECTIONS
import com.andreyolenkov.vyksasport.models.SectionsModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddSectionViewModel: ViewModel() {
    fun insert(sectionModel: SectionsModel, onSuccess:()-> Unit) {
        viewModelScope.launch (Dispatchers.IO){
            REPOSITORY_SECTIONS.insertSection(sectionModel){
                onSuccess()
            }
        }
    }
}