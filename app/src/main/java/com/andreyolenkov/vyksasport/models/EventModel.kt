package com.andreyolenkov.vyksasport.models

import androidx.room.*
import java.io.Serializable
import java.time.format.DateTimeFormatter

@Entity("events",
    indices = [Index("id")],
    foreignKeys = [
        ForeignKey(
            entity = ComplexModel::class,
            parentColumns = ["id"],
            childColumns = ["complexId"]
        )
    ]
    )
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