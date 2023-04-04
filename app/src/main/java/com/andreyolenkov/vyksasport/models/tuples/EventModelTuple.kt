package com.andreyolenkov.vyksasport.models.tuples

import androidx.room.ColumnInfo
import java.io.Serializable

class EventModelTuple (
    val id:Long,
    val name:String,
    val timedate:String,
    val place:String,
    @ColumnInfo(name="complex_name")val complexName:String,
    val description:String
        ) : Serializable