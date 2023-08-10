package com.david.tot.data

import com.david.tot.data.database.dao.ArticleDao
import com.david.tot.data.database.dao.SyncDao
import com.david.tot.data.network.article.ArticleService
import com.david.tot.domain.model.Article
import com.david.tot.domain.model.DrugsDeliveryConsumerViewHeader
import com.david.tot.domain.model.Sync
import com.david.tot.domain.model.toDomain
import com.david.tot.util.IsImageFile
import okhttp3.MultipartBody
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import javax.inject.Inject

class SyncRepository @Inject constructor(
    private val syncDao: SyncDao
) {
    suspend fun addOne(sync:Sync){
        return syncDao.addOne(sync)
    }

    suspend fun getAll():List<Sync>{
        val response: List<Sync> = syncDao.getAll()
        return response.map { it.toDomain() }
    }

}
