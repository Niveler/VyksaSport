package com.andreyolenkov.vyksasport.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity("complex")
class ComplexModel (
    @PrimaryKey(autoGenerate = true)
    var id:Int=0,
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    var img: ByteArray,
    @ColumnInfo
    var name:String,
    @ColumnInfo
    var operationMode:String,
    @ColumnInfo
    var adress: String,
    @ColumnInfo
    var number: String,
    @ColumnInfo
    var email: String
) : Serializable