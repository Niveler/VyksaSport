package com.andreyolenkov.vyksasport.db.repository

import androidx.lifecycle.LiveData
import com.andreyolenkov.vyksasport.db.dao.GroupsDao
import com.andreyolenkov.vyksasport.models.GroupsModel

class GroupsRealization (private val groupsDao: GroupsDao): GroupsRepository{
    override val allGroups: LiveData<List<GroupsModel>>
        get() = groupsDao.getAllGroups()

    override suspend fun insertGroups(groupsModel: GroupsModel, onSuccess: () -> Unit) {
        groupsDao.insert(groupsModel)
        onSuccess()
    }

    override suspend fun deleteGroups(groupsModel: GroupsModel, onSuccess: () -> Unit) {
        groupsDao.delete(groupsModel)
        onSuccess()
    }
}