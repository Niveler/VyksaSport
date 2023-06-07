package com.andreyolenkov.vyksasport.models.tuples

import androidx.room.ColumnInfo
import java.io.Serializable

class SectionModuleTuple (
    val id: Long,
    val name:String,
    val img: ByteArray,
    @ColumnInfo(name="complex_name")val complexName:String,
    @ColumnInfo(name="person_name")val personName:String
        ) : Serializable