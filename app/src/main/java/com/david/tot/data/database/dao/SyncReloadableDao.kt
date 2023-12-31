package com.david.tot.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.david.tot.domain.model.SyncReloadable

@Dao
interface SyncReloadableDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addOneSyncReloadableToLocaDatabase(syncReloadable: SyncReloadable)

    @Query("SELECT * FROM SyncReloadableTable")
    suspend fun retrieveAllSyncReloadablesFromLocalDatabase():List<SyncReloadable>

    /*
    @Query("SELECT * FROM SyncTable WHERE dataType=:syncType")
    suspend fun getAllSyncReloadableFromLocaDatabase(syncType:String):List<SyncReloadable>
    */
    @Query("SELECT SyncReloadableTable.localId AS localId," +
            "SyncReloadableTable.objectId AS objectId," +
            "SyncReloadableTable.consumptionDetailId AS consumptionDetailId," +
            "SyncReloadableTable.consumptionId AS consumptionId," +
            "SyncReloadableTable.articleCode AS articleCode," +
            "SyncReloadableTable.quantity AS quantity," +
            "SyncReloadableTable.unitOfMeasure AS unitOfMeasure," +
            "SyncReloadableTable.creationDate AS creationDate," +
            "SyncReloadableTable.delivered AS delivered " +
            "FROM SyncTable " +
            "INNER JOIN SyncReloadableTable " +
            "WHERE SyncReloadableTable.objectId=SyncTable.objectId AND SyncTable.dataType=:syncType")
    suspend fun getAllSyncReloadablesByDatatypeFromLocaDatabase(syncType:String):List<SyncReloadable>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addManyReloadableToLocalDatabase(recipes:List<SyncReloadable>)

    /*
    // TODO virtualassembler poner el WHERE filtrar por el id del header
    @Query("SELECT consumptionDetailId AS consumptionDetailId, consumptionId as consumptionId, articleCode as articleCode, quantity as quantity, unitOfMeasure as unitOfMeasure, creationDate as creationDate,delivered AS delivered FROM SyncReloadableTable")
    suspend fun getAllReloadablesFromLocalDatabase():List<ReloadableClean>
    */

    @Query("DELETE FROM SyncReloadableTable WHERE objectId=:objectId")
    suspend fun removeManySyncReloadablesByObjectIdFromLocalDatabase(objectId:Int):Int

    @Query("DELETE FROM SyncReloadableTable")
    suspend fun removeAllSyncReloadablesFromLocalDatabaseUseCase():Int


    /*
    @Query("SELECT * FROM syncTable ORDER BY createdAt ASC")
    suspend fun getAll():List<Article>

    @Query("DELETE FROM article_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM article_table WHERE local_id = :local_id")
    suspend fun getById(local_id: Int): Article

    @Query("SELECT * FROM article_table WHERE articleDescription LIKE :recipeHash ORDER BY articleDescription ASC")
    fun getFiltered(recipeHash: String): List<Article>

    @Query("UPDATE article_table SET quantityToRestore = :quantityToRestoreValue WHERE local_id = :localIdArticle")
    suspend fun updateQuantity(localIdArticle:Int,quantityToRestoreValue:Int)
    */

}