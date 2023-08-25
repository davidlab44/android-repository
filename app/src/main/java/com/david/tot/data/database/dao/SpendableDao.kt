package com.david.tot.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.david.tot.domain.model.Article
import com.david.tot.domain.model.Dv
import com.david.tot.domain.model.Pre
import com.david.tot.domain.model.Reportable
import com.david.tot.domain.model.Spendable

@Dao
interface SpendableDao {

    @Query("SELECT * FROM SpendableTable")
    suspend fun getAllSpendableFromDatabase():List<Spendable>


    @Query("SELECT * FROM SpendableTable")
    suspend fun insertOneSpendableToLocalDatabase():List<Spendable>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addOneSpendableToLocalDatabase(reportable: Spendable)

}