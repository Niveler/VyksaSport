package com.andreyolenkov.vyksasport.db.repository

import androidx.lifecycle.LiveData
import com.andreyolenkov.vyksasport.models.GroupsModel
import io.reactivex.internal.operators.single.SingleDoOnSuccess

interface GroupsRepository {
    val allGroups: LiveData<List<GroupsModel>>
    suspend fun insertGroups(groupsModel: GroupsModel, onSuccess: ()->Unit)
    suspend fun deleteGroups(groupsModel: GroupsModel, onSuccess: ()->Unit)
}