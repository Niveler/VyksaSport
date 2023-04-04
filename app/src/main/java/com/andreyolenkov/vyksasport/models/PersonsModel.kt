package com.andreyolenkov.vyksasport.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("persons")
class PersonsModel (
    @PrimaryKey(autoGenerate = true)
    var id:Int=0,
    @ColumnInfo
    var famile:String,
    @ColumnInfo
    var name:String,
    @ColumnInfo
    var email:String,
    @ColumnInfo
    var phone:String,
    /*@ColumnInfo
    var section_id:Int
    @columnInfo
    var complex_id:Int*/
        )