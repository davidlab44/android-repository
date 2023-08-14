package com.david.tot.data

import com.david.tot.data.database.dao.ArticleDao
import com.david.tot.data.database.dao.PreDao
import com.david.tot.data.database.dao.ReportableDao
import com.david.tot.data.network.article.ArticleService
import com.david.tot.domain.model.Article
import com.david.tot.domain.model.Dv
import com.david.tot.domain.model.Pre
import com.david.tot.domain.model.Reportable
import com.david.tot.domain.model.toDomain
import com.david.tot.util.IsImageFile
import com.google.gson.JsonArray
import okhttp3.MultipartBody
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
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
