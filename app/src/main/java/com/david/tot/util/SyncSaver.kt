package com.david.tot.util

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.david.tot.data.database.dao.SyncDao
import com.david.tot.domain.model.Sync

class SyncSaver: SyncDao {
    override suspend fun addOneSyncToLOcalDatabase(sync: Sync) {
        @Insert(onConflict = OnConflictStrategy.REPLACE){}
    }

    override suspend fun getAllSyncConsumibleFromLocalDatabase(): List<Sync> {
        TODO("Not yet implemented")
    }

    override suspend fun getAllSyncReportableFromLocalDatabase(): List<Sync> {
        TODO("Not yet implemented")
    }

    override suspend fun removeOneSyncFromLocalDatabase(objectId: Int) {
        TODO("Not yet implemented")
    }

}