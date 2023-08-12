package com.david.tot.data

import com.david.tot.data.database.dao.ArticleDao
import com.david.tot.data.database.dao.SyncConsumibleDao
import com.david.tot.data.database.dao.SyncDao
import com.david.tot.data.network.article.ArticleService
import com.david.tot.domain.model.Article
import com.david.tot.domain.model.Consumible
import com.david.tot.domain.model.DrugsDeliveryConsumerViewHeader
import com.david.tot.domain.model.Sync
import com.david.tot.domain.model.SyncConsumible
import com.david.tot.domain.model.toDomain
import com.david.tot.util.IsImageFile
import okhttp3.MultipartBody
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import javax.inject.Inject

class SyncConsumibleRepository @Inject constructor(
    private val syncConsumibleDao: SyncConsumibleDao
) {

    suspend fun addOne(syncConsumible: SyncConsumible){
        return syncConsumibleDao.addOneSyncConsumibleToLocaDatabase (syncConsumible)
    }

    suspend fun getAllConsumibleFromLocalDatabaseUseCase():List<Consumible>{
        val response: List<Consumible> = syncConsumibleDao.getAllConsumibleFromLocalDatabaseUseCase()
        return response.map { it.toDomain() }
    }

    suspend fun getAll():List<SyncConsumible>{
        val response: List<SyncConsumible> = syncConsumibleDao.getAllSyncConsumibleFromLocaDatabase()
        return response.map { it.toDomain() }
    }

    suspend fun addManyArticleToLocalDatabase(recipes:List<SyncConsumible>){
        syncConsumibleDao.addManyArticleToLocalDatabase(recipes)
    }


}
