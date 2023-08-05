package com.david.tot.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.david.tot.domain.model.Article
import com.david.tot.domain.model.DrugsDeliveryConsumerViewHeader

@Dao
interface DrugsDeliveryConsumerViewHeaderDao {

    @Query("SELECT * FROM DrugsDeliveryConsumerViewHeaderTable")
    suspend fun getAll():List<DrugsDeliveryConsumerViewHeader>

    @Query("SELECT * FROM DrugsDeliveryConsumerViewHeaderTable LIMIT 1")
    suspend fun getAny():DrugsDeliveryConsumerViewHeader

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(recipes:List<DrugsDeliveryConsumerViewHeader>)

    @Query("DELETE FROM DrugsDeliveryConsumerViewHeaderTable")
    suspend fun deleteAll()

}