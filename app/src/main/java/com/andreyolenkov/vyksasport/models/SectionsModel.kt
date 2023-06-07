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
        entity = PersonsModel::class,
        parentColumns = ["id"],
        childColumns = ["person_id"]
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
    var person_id: Int = 0,
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    var img: ByteArray
): Serializable