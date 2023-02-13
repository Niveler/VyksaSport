package com.andreyolenkov.vyksasport.ui.screens.complex

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andreyolenkov.vyksasport.REPOSITORY_COMPLEX
import com.andreyolenkov.vyksasport.models.ComplexModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddComplexViewModel: ViewModel() {
    fun insert(complexModel: ComplexModel, onSuccess:()-> Unit) {
        viewModelScope.launch (Dispatchers.IO){
            REPOSITORY_COMPLEX.insertComplex(complexModel){
                onSuccess()
            }
        }
    }

}