package com.david.tot.data

import com.david.tot.data.database.dao.SpendableDao
import com.david.tot.domain.model.Article
import com.david.tot.domain.model.Spendable
import com.david.tot.domain.model.toDomain
import javax.inject.Inject

class SpotableRepository @Inject constructor(
    //private val reportableDao: SpendableDao
) {

    suspend fun getFiltered(hash: String): List<Article> {
        val response: List<Article> = consumibleDao.getFiltered(hash)
        response.map { it.toDomain() }
        return consumibleDao.getFiltered(hash)
    }


}
