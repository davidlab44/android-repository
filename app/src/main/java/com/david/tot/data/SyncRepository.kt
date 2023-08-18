package com.david.tot.data

import com.david.tot.data.database.dao.SyncDao
import com.david.tot.domain.model.Sync
import com.david.tot.domain.model.toDomain
import javax.inject.Inject

class SyncRepository @Inject constructor(
    private val syncDao: SyncDao
) {
    suspend fun addOne(sync:Sync){
        return syncDao.addOneSyncToLOcalDatabase(sync)
    }

    suspend fun getAllSyncFromLocalDatabase():List<Sync>{
        val response: List<Sync> = syncDao.getAllSyncConsumibleFromLocalDatabase()
        return response.map { it.toDomain() }
    }

    /*
    suspend fun getAllSyncByDatatypeFromLocaDatabase(syncType:String):List<Sync>{
        val response: List<Sync> = syncDao.getAllSyncByDatatypeFromLocaDatabase(syncType)
        return response.map { it.toDomain() }
    }
    */

    suspend fun removeOneSyncFromLocalDatabase(objectId:Int){
        return syncDao.removeOneSyncFromLocalDatabase(objectId)
    }


}
