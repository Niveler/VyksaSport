package com.andreyolenkov.vyksasport.models

import androidx.room.*
import java.io.Serializable


@Entity("groups",
    indices = [Index("id")],
    foreignKeys = [
        ForeignKey(
            entity = PersonsModel::class,
            parentColumns = ["id"],
            childColumns = ["person_id"]
        ),
    ForeignKey (
        entity = SectionsModel::class,
        parentColumns = ["id"],
        childColumns = ["section_id"]
            )
    ]
    )
class GroupsModel (
    @PrimaryKey(autoGenerate = true)
    var id:Int=0,
    @ColumnInfo
    var group_name:String,
    @ColumnInfo
    var person_id: Int = 0,
    @ColumnInfo
    var section_id: Int = 0,
    @ColumnInfo
    var description:String
): Serializable