package com.andreyolenkov.vyksasport.models

import androidx.room.*
import java.io.Serializable


@Entity("groups",
    indices = [Index("id")],
    foreignKeys = [
        ForeignKey(
            entity = ChiefsModel::class,
            parentColumns = ["id"],
            childColumns = ["chief_id"]
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
    var chief_id: Int = 0,
    @ColumnInfo
    var section_id: Int = 0
): Serializable