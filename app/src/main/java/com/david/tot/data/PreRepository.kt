package com.david.tot.data

import com.david.tot.data.database.dao.PreDao
import com.david.tot.domain.model.Dv
import com.david.tot.domain.model.Pre
import com.david.tot.domain.model.toDomain
import javax.inject.Inject

class PreRepository @Inject constructor(
    private val preDao: PreDao
) {

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

}
