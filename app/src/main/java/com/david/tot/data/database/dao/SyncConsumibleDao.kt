package com.david.tot.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.david.tot.domain.model.Article
import com.david.tot.domain.model.Consumible
import com.david.tot.domain.model.Sync
import com.david.tot.domain.model.SyncConsumible

@Dao
interface SyncConsumibleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addOneSyncConsumibleToLocaDatabase(syncConsumible: SyncConsumible)

    /*
    @Query("SELECT * FROM SyncTable WHERE dataType=:syncType")
    suspend fun getAllSyncConsumibleFromLocaDatabase(syncType:String):List<Sync>
     */

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addManyArticleToLocalDatabase(recipes:List<SyncConsumible>)

    // TODO virtualassembler poner el WHERE filtrar por el id del header
    @Query("SELECT consumptionDetailId AS consumptionDetailId, consumptionId as consumptionId, articleCode as articleCode, quantity as quantity, unitOfMeasure as unitOfMeasure, creationDate as creationDate,delivered AS delivered FROM SyncConsumibleTable")
    suspend fun getAllConsumibleFromLocalDatabaseUseCase():List<Consumible>

    @Query("DELETE FROM SyncConsumibleTable WHERE objectId=:objectId")
    suspend fun removeManySyncConsumiblesFromLocalDatabase(objectId:Int)

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