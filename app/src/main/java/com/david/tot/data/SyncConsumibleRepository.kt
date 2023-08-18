package com.david.tot.data

import com.david.tot.data.database.dao.SyncConsumibleDao
import com.david.tot.domain.model.Consumible
import com.david.tot.domain.model.SyncConsumible
import com.david.tot.domain.model.SyncReloadable
import com.david.tot.domain.model.toDomain
import javax.inject.Inject

class SyncConsumibleRepository @Inject constructor(
    private val syncConsumibleDao: SyncConsumibleDao
) {

    suspend fun addOne(syncConsumible: SyncConsumible){
        return syncConsumibleDao.addOneSyncConsumibleToLocaDatabase (syncConsumible)
    }

    /*
    suspend fun getAllConsumibleFromLocalDatabaseUseCase():List<Consumible>{
        val response: List<Consumible> = syncConsumibleDao.getAllConsumibleFromLocalDatabaseUseCase()
        return response.map { it.toDomain() }
    }
    */


    /*
    suspend fun getAll():List<SyncConsumible>{
        val response: List<SyncConsumible> = syncConsumibleDao.getAllSyncConsumibleFromLocaDatabase()
        return response.map { it.toDomain() }
    }
    */

    suspend fun addManyArticleToLocalDatabase(recipes:List<SyncConsumible>){
        syncConsumibleDao.addManyArticleToLocalDatabase(recipes)
    }

    suspend fun removeManySyncConsumiblesFromLocalDatabase(syncConsumibleId:Int){
        syncConsumibleDao.removeManySyncConsumiblesFromLocalDatabase(syncConsumibleId)
    }


    suspend fun getAllSyncConsumiblesByDatatypeFromLocaDatabase(dataType:String):List<SyncConsumible>{
        val response: List<SyncConsumible> = syncConsumibleDao.getAllSyncConsumiblesByDatatypeFromLocaDatabase(dataType)
        return response.map { it.toDomain() }
    }


}
