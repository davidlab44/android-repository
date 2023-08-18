package com.david.tot.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.david.tot.domain.model.Article
import com.david.tot.domain.model.Consumible
import com.david.tot.domain.model.Sync
import com.david.tot.domain.model.SyncConsumible
import com.david.tot.domain.model.SyncReloadable

@Dao
interface SyncConsumibleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addOneSyncConsumibleToLocaDatabase(syncConsumible: SyncConsumible)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addManyArticleToLocalDatabase(recipes:List<SyncConsumible>)

    @Query("DELETE FROM SyncConsumibleTable WHERE objectId=:objectId")
    suspend fun removeManySyncConsumiblesFromLocalDatabase(objectId:Int):Int

    @Query("SELECT SyncConsumibleTable.localId AS localId," +
            "SyncConsumibleTable.objectId AS objectId," +
            "SyncConsumibleTable.consumptionDetailId AS consumptionDetailId," +
            "SyncConsumibleTable.consumptionId AS consumptionId," +
            "SyncConsumibleTable.articleCode AS articleCode," +
            "SyncConsumibleTable.quantity AS quantity," +
            "SyncConsumibleTable.unitOfMeasure AS unitOfMeasure," +
            "SyncConsumibleTable.creationDate AS creationDate," +
            "SyncConsumibleTable.delivered AS delivered " +
            "FROM SyncTable " +
            "INNER JOIN SyncConsumibleTable " +
            "WHERE SyncConsumibleTable.objectId=SyncTable.objectId AND SyncTable.dataType=:syncType")
    suspend fun getAllSyncConsumiblesByDatatypeFromLocaDatabase(syncType:String):List<SyncConsumible>

}