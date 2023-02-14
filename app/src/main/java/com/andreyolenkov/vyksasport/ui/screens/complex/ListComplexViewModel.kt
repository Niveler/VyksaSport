package com.andreyolenkov.vyksasport.ui.screens.complex

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.andreyolenkov.vyksasport.REPOSITORY_COMPLEX
import com.andreyolenkov.vyksasport.db.ComplexDatabase
import com.andreyolenkov.vyksasport.db.repository.ComplexRealization
import com.andreyolenkov.vyksasport.models.ComplexModel
import kotlinx.coroutines.launch

class ListComplexViewModel (application: Application):AndroidViewModel(application){
    val context = application

    fun initdatabase(){
        val daoComplex=ComplexDatabase.getInstance(context).getComplexDao()
        REPOSITORY_COMPLEX=ComplexRealization(daoComplex)
    }
    fun getAllComplex():LiveData<List<ComplexModel>>{
        return REPOSITORY_COMPLEX.allComplex
    }
}