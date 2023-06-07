package com.andreyolenkov.vyksasport.models.tuples

import androidx.room.ColumnInfo

class GroupsModelTuple (
    val id:Long,
    val group_name:String,
    @ColumnInfo(name="chief_name")
    val chief_name:String,
    @ColumnInfo(name="section_name")
    val section_name:String
        )