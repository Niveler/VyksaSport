package com.andreyolenkov.vyksasport.ui.screens.calendar

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.andreyolenkov.vyksasport.REPOSITORY_CALENDAR
import com.andreyolenkov.vyksasport.REPOSITORY_GROUPS
import com.andreyolenkov.vyksasport.models.tuples.CalendarModelTuple
import com.andreyolenkov.vyksasport.models.tuples.GroupsModelTuple

class TimeTableViewModel : ViewModel() {
    //1-тут сначало запрос в группы с айди моей секции
    //Вернется название группы и её айди
    //2-По айди группы вызовем календарь
    fun getMyGroup():LiveData<List<GroupsModelTuple>> {
        return REPOSITORY_GROUPS.myGroupWithName
    }
    fun getAllCalendarByGroup():LiveData<List<CalendarModelTuple>> {
        return REPOSITORY_CALENDAR.allCalendarByGroup
    }
}