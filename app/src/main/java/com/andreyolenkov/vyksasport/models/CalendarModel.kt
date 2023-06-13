package com.andreyolenkov.vyksasport.models

import androidx.room.*

@Entity("calendar",
    indices = [Index("id")],
    foreignKeys = [
        ForeignKey(
            entity = SectionsModel::class,
            parentColumns = ["id"],
            childColumns = ["group_id"]
        )
    ]
)
class CalendarModel (
    @PrimaryKey(autoGenerate = true)
    var id:Int=0,
    @ColumnInfo
    var group_id: Int = 0,
    @ColumnInfo
    var timedate: String = "",
    @ColumnInfo
    var place: String =""
)
