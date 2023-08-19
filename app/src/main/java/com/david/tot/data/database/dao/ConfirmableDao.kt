package com.david.tot.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.david.tot.domain.model.Confirmable

@Dao
interface ConfirmableDao {
    @Query("SELECT * FROM ConfirmableTable ")
    suspend fun getAll():List<Confirmable>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(recipes:List<Confirmable>)

    @Query("DELETE FROM ConfirmableTable")
    suspend fun deleteAll()

    @Query("SELECT * FROM ConfirmableTable WHERE localId = :localId")
    suspend fun getById(localId: Int): Confirmable

    /*
    @Query("SELECT * FROM ConfirmableTable WHERE articleDescription LIKE :confirmableHash")
    fun getFiltered(confirmableHash: String): List<Confirmable>
    */

    /*
    @Query("UPDATE ConfirmableTable SET quantityConsumed = :reloadableNewQuantity WHERE localId = :reloadableLocalId")
    suspend fun updateConsumedQuantity(reloadableLocalId:Int, reloadableNewQuantity:Int):Int
    */
}