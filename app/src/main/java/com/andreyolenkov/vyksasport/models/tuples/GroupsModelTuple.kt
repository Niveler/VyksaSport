package com.andreyolenkov.vyksasport.models.tuples

import androidx.room.ColumnInfo
import java.io.Serializable

class GroupsModelTuple (
    val id:Long,
    val group_name:String,
    val description:String,
    @ColumnInfo(name="person_name")
    val person_name:String,
    @ColumnInfo(name="section_name")
    val section_name:String
        ) : Serializable