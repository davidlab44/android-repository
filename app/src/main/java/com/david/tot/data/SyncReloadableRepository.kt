package com.david.tot.data

import com.david.tot.data.database.dao.SyncReloadableDao
import com.david.tot.domain.model.Reloadable
import com.david.tot.domain.model.ReloadableClean
import com.david.tot.domain.model.SyncReloadable
import com.david.tot.domain.model.toDomain
import javax.inject.Inject

class SyncReloadableRepository @Inject constructor(
    private val syncReloadableDao: SyncReloadableDao
) {

    suspend fun addOne(syncReloadable: SyncReloadable){
        return syncReloadableDao.addOneSyncReloadableToLocaDatabase (syncReloadable)
    }

    suspend fun getAllSyncReloadableFromLocalDatabase(syncType:String):List<SyncReloadable>{
        val response: List<SyncReloadable> = syncReloadableDao.getAllSyncReloadableFromLocaDatabase(syncType)
        return response.map { it.toDomain() }
    }

    suspend fun getAllReloadableCleanFromLocalDatabaseUseCase():List<ReloadableClean>{
        val response: List<ReloadableClean> = syncReloadableDao.getAllReloadablesFromLocalDatabase()
        return response.map { it.toDomain() }
    }

    suspend fun getAll():List<SyncReloadable>{
        val response: List<SyncReloadable> = syncReloadableDao.getAllSyncReloadableFromLocaDatabase()
        return response.map { it.toDomain() }
    }

    suspend fun addManyReloadablesToLocalDatabase(recipes:List<SyncReloadable>){
        syncReloadableDao.addManyReloadableToLocalDatabase(recipes)
    }

    suspend fun removeManySyncConsumiblesFromLocalDatabase(syncReloadableId:Int){
        syncReloadableDao.removeManySyncReloadablesFromLocalDatabase(syncReloadableId)
    }

}
