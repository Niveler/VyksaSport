package com.andreyolenkov.vyksasport.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.time.format.DateTimeFormatter

@Entity("events")
class EventModel (
    @PrimaryKey(autoGenerate = true)
    var id:Int=0,
    @ColumnInfo
    var name: String = "",
    @ColumnInfo
    var timedate: String = "",
    @ColumnInfo
    var place: String ="",
    @ColumnInfo
    var complexId: Int = 0,
    @ColumnInfo
    var description: String = ""
) : Serializable