package com.andreyolenkov.vyksasport.models

import androidx.room.*

@Entity("calendar",
    indices = [Index("id")],
    foreignKeys = [
        ForeignKey(
            entity = SectionsModel::class,
            parentColumns = ["id"],
            childColumns = ["section_id"]
        )
    ]
    )
class CalendarModel (
    @PrimaryKey(autoGenerate = true)
    var id:Int=0,
    @ColumnInfo
    var section_id: Int = 0,
    @ColumnInfo
    var timedate: String = "",
    @ColumnInfo
    var place: String =""
)
