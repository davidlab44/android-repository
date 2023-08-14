package com.david.tot.util

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.david.tot.data.database.dao.ReportableDao
import com.david.tot.domain.model.Reportable

class ReportableSaver: ReportableDao {
    override suspend fun getAllReportableFromDatabase(): List<Reportable> {
        TODO("Not yet implemented")
    }

    override suspend fun addOneReportableToLocalDatabase(reportable: Reportable) {
        @Insert(onConflict = OnConflictStrategy.REPLACE){}
    }

}