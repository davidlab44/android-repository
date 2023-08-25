package com.david.tot.data

import com.david.tot.data.database.dao.SpendableDao
import com.david.tot.domain.model.Spendable
import com.david.tot.domain.model.toDomain
import javax.inject.Inject

class SpendableRepository @Inject constructor(
    private val reportableDao: SpendableDao
) {


    suspend fun addOneSpendableToLocalDatabase(reportable: Spendable){
        reportableDao.addOneSpendableToLocalDatabase(reportable)
    }


    suspend fun getAllSpendableFromDatabase():List<Spendable>{
        val response: List<Spendable> = reportableDao.getAllSpendableFromDatabase()
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
