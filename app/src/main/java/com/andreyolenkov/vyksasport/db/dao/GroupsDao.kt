package com.andreyolenkov.vyksasport.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.andreyolenkov.vyksasport.models.GroupsModel
import com.andreyolenkov.vyksasport.models.tuples.GroupsModelTuple

@Dao
interface GroupsDao {
    @Insert
    fun insert(groupsModel: GroupsModel)

    @Delete
    fun delete(groupsModel: GroupsModel)

    @Query("SELECT * FROM groups")
    fun getAllGroups(): LiveData<List<GroupsModel>>
    //Список групп и пользователей с именами
    @Query("SELECT groups.id, groups.group_name, persons.famile ||' '|| persons.name as person_name, sections.name as section_name, groups.description FROM groups " +
            "INNER JOIN persons on groups.person_id=persons.id " +
            "INNER JOIN sections on groups.section_id=sections.id")
    fun getAllGroupsWithName():LiveData<List<GroupsModelTuple>>
    @Query("SELECT groups.id, groups.group_name, persons.famile ||' '|| persons.name as person_name, sections.name as section_name, groups.description FROM groups " +
            "INNER JOIN persons on groups.person_id=persons.id " +
            "INNER JOIN sections on groups.section_id=sections.id " +
            "WHERE groups.section_id= :idSection")
    fun getMyGroupWithName(idSection:Long): LiveData<List<GroupsModelTuple>>
}