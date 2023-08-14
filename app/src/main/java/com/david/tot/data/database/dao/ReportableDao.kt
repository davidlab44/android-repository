package com.david.tot.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.david.tot.domain.model.Article
import com.david.tot.domain.model.Dv
import com.david.tot.domain.model.Pre
import com.david.tot.domain.model.Reportable

@Dao
interface ReportableDao {

    @Query("SELECT * FROM ReportableTable")
    suspend fun getAllReportableFromDatabase():List<Reportable>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addOneReportableToLocalDatabase(reportable: Reportable)

}