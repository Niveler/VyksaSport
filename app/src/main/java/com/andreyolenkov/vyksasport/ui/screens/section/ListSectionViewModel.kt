package com.andreyolenkov.vyksasport.ui.screens.section

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.andreyolenkov.vyksasport.REPOSITORY_EVENTS
import com.andreyolenkov.vyksasport.REPOSITORY_SECTIONS
import com.andreyolenkov.vyksasport.db.ComplexDatabase
import com.andreyolenkov.vyksasport.db.repository.EventsRealization
import com.andreyolenkov.vyksasport.db.repository.SectionsRealization
import com.andreyolenkov.vyksasport.models.SectionsModel
import com.andreyolenkov.vyksasport.models.tuples.SectionModuleTuple

class ListSectionViewModel(application: Application): AndroidViewModel(application) {
    val context = application
    fun initdatabase(){
        val daoSections= ComplexDatabase.getInstance(context).getSectionsDao()
        REPOSITORY_SECTIONS = SectionsRealization(daoSections)
    }
    fun getAllSections():LiveData<List<SectionsModel>>{
        return REPOSITORY_SECTIONS.allSections
    }
    fun getAllSectionsWithNames():LiveData<List<SectionModuleTuple>> {
        return REPOSITORY_SECTIONS.allSectionsByNameComplex
    }
}