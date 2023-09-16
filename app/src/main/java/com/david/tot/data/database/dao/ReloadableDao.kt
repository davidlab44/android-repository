package com.david.tot.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.david.tot.domain.model.Article
import com.david.tot.domain.model.Reloadable

@Dao
interface ReloadableDao {
    /*
    @Query("SELECT * FROM ReloadableTable ")
    suspend fun getAll():List<Reloadable>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(recipes:List<Reloadable>)

    @Query("DELETE FROM ReloadableTable")
    suspend fun deleteAll()

    @Query("DELETE FROM ReloadableTable")
    suspend fun removeAllReloadablesFromLocalDatabase():Int

    @Query("SELECT * FROM ReloadableTable WHERE localId = :localId")
    suspend fun getById(localId: Int): Reloadable


    @Query("SELECT * FROM ReloadableTable WHERE articleDescription LIKE :reloadableHash")
    fun getFiltered(reloadableHash: String): List<Reloadable>


     */
    /*
    @Query("UPDATE ReloadableTable SET quantityConsumed = :reloadableNewQuantity WHERE localId = :reloadableLocalId")
    suspend fun updateConsumedQuantity(reloadableLocalId:Int, reloadableNewQuantity:Int):Int

    */
}