package com.david.tot.data

import com.david.tot.data.database.dao.SpendableDao
import com.david.tot.data.database.dao.SpotableDao
import com.david.tot.domain.model.Article
import com.david.tot.domain.model.Reloadable
import com.david.tot.domain.model.Spendable
import com.david.tot.domain.model.Spotable
import com.david.tot.domain.model.toDomain
import javax.inject.Inject

class SpotableRepository @Inject constructor(
    private val spotableDao: SpotableDao
) {

    suspend fun getFiltered(hash: String): List<Spotable> {
        val response: List<Spotable> = spotableDao.getFiltered(hash)
        response.map { it.toDomain() }
        return response
    }

    suspend fun clearSpotables(){
        spotableDao.deleteAll()
    }

    suspend fun addAllSpotablesToLocalDb(reloadables:List<Spotable>){
        spotableDao.insertAll(reloadables)
    }

    suspend fun retrieveFilteredSpotablesFromLocalDataba(hash: String): List<Spotable> {
        val response: List<Spotable> = spotableDao.getFiltered(hash)
        response.map { it.toDomain() }
        return spotableDao.getFiltered(hash)
    }

    suspend fun getAllSpotablesFromDatabase():List<Spotable>{
        val response: List<Spotable> = spotableDao.getAllSpotableFromDatabase()
        return response.map { it.toDomain() }
    }

}
