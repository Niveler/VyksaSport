package com.andreyolenkov.vyksasport.db.repository

import androidx.lifecycle.LiveData
import com.andreyolenkov.vyksasport.db.dao.GroupsDao
import com.andreyolenkov.vyksasport.models.GroupsModel
import com.andreyolenkov.vyksasport.models.tuples.GroupsModelTuple

class GroupsRealization (private val groupsDao: GroupsDao): GroupsRepository{
    var idSection= 0L
    override val allGroups: LiveData<List<GroupsModel>>
        get() = groupsDao.getAllGroups()
    override val allGroupsWithName: LiveData<List<GroupsModelTuple>>
        get() = groupsDao.getAllGroupsWithName()

    override suspend fun insertGroups(groupsModel: GroupsModel, onSuccess: () -> Unit) {
        groupsDao.insert(groupsModel)
        onSuccess()
    }

    override suspend fun deleteGroups(groupsModel: GroupsModel, onSuccess: () -> Unit) {
        groupsDao.delete(groupsModel)
        onSuccess()
    }

    override val myGroupWithName: LiveData<List<GroupsModelTuple>>
        get() = groupsDao.getMyGroupWithName(idSection)
}