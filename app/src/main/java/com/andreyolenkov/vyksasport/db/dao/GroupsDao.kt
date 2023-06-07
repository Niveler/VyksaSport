package com.andreyolenkov.vyksasport.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.andreyolenkov.vyksasport.models.GroupsModel

@Dao
interface GroupsDao {
    @Insert
    fun insert(groupsModel: GroupsModel)

    @Delete
    fun delete(groupsModel: GroupsModel)

    @Query("SELECT * FROM groups")
    fun getAllGroups(): LiveData<List<GroupsModel>>
}