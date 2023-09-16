package com.david.tot.data

import com.david.tot.data.database.dao.ReportableDao
import com.david.tot.domain.model.Reportable
import com.david.tot.domain.model.toDomain
import javax.inject.Inject

class ReportableRepository @Inject constructor(
    private val reportableDao: ReportableDao
) {


    suspend fun addOneReportableToLocalDatabase(reportable: Reportable){
        reportableDao.addOneReportableToLocalDatabase(reportable)
    }


    suspend fun getAllReportableFromDatabase():List<Reportable>{
        val response: List<Reportable> = reportableDao.getAllReportableFromDatabase()
        return response.map { it.toDomain() }
    }


    /*
    suspend fun getAllPreFromDatabase():List<Pre>{
        val response: List<Pre> = preDao.getAll()
        return response.map { it.toDomain() }
    }

    suspend fun getAllDvFromDatabase():List<Dv>{
        val response: List<Dv> = preDao.getAllDv()
        return response.map { it.toDomain() }
    }

    suspend fun addOnePreToLocalDatabase(pre:Pre){
        preDao.insertAll(pre)
    }

     */

}
