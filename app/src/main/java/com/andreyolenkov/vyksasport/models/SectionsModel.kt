package com.andreyolenkov.vyksasport.models

import androidx.room.*
import java.io.Serializable


@Entity ("sections",
        indices = [Index("id")],
    foreignKeys = [
        ForeignKey(
            entity = ComplexModel::class,
            parentColumns = ["id"],
            childColumns = ["complexId"]
        ),
    ForeignKey(
        entity = ChiefsModel::class,
        parentColumns = ["id"],
        childColumns = ["chief_id"]
    ),
    ForeignKey(
        entity = GroupsModel::class,
        parentColumns = ["id"],
        childColumns = ["group_id"]
    )
    ]
    )
class SectionsModel (
    @PrimaryKey(autoGenerate = true)
    var id:Int=0,
    @ColumnInfo
    var name: String="",
    @ColumnInfo
    var complexId: Int = 0,
    @ColumnInfo
    var chief_id: Int = 0,
    @ColumnInfo
    var group_id: Int = 0,
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    var img: ByteArray
): Serializable