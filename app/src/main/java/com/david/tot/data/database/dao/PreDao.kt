package com.david.tot.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.david.tot.domain.model.Article
import com.david.tot.domain.model.Dv
import com.david.tot.domain.model.Pre

@Dao
interface PreDao {

    @Query("SELECT * FROM PreTable")
    suspend fun getAll():List<Pre>

    @Query("SELECT local_id as cod,name as description FROM PreTable")
    suspend fun getAllDv():List<Dv>



    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(pre:Pre)

}