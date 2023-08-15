package com.david.tot.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.david.tot.domain.model.Authenticable

@Dao
interface AuthenticableDao {

    @Query("SELECT * FROM AuthenticableTable")
    suspend fun getAll():List<Authenticable>

    @Query("SELECT * FROM AuthenticableTable LIMIT 1")
    suspend fun getAny():Authenticable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(recipes:List<Authenticable>)

    @Query("DELETE FROM AuthenticableTable ")
    suspend fun deleteAll()

}