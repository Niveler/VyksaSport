package com.andreyolenkov.vyksasport.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity("persons")
class PersonsModel(
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
    @ColumnInfo
    var role: Int,
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    var img: ByteArray
        ) : Serializable