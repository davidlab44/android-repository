package com.david.tot.ui.requirable

/*
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

import com.david.tot.domain.UpdateProductUseCase
import com.david.tot.domain.UpdateQuantityUseCase
import com.david.tot.domain.article.GetArticleByIdUseCase
import com.david.tot.domain.article.GetArticleListUseCase
import com.david.tot.domain.model.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleViewModel @Inject constructor(
    private val getArticleListUseCase: GetArticleListUseCase,
    private val updateProductUseCase: UpdateProductUseCase,
    private val updateQuantityUseCase: UpdateQuantityUseCase,
    private val getArticleByIdUseCase: GetArticleByIdUseCase,
    ) : ViewModel() {
    */

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.david.tot.domain.UpdateConsumedQuantityUseCase
import com.david.tot.domain.article.GetAllFromApiUseCase
import com.david.tot.domain.article.GetAllFromLocalDatabaseUseCase
import com.david.tot.domain.article.GetArticleByIdUseCase
import com.david.tot.domain.article.GetFilteredArticleListUseCase
import com.david.tot.domain.article.ReAddAllConsumibleToLocalDatabaseUseCase
import com.david.tot.domain.model.Article
import com.david.tot.domain.model.Sync
import com.david.tot.domain.model.SyncConsumible
import com.david.tot.domain.sync.AddOneSyncFromLocalDatabaseUseCase
import com.david.tot.domain.sync.consumible.AddManySyncConsumibleToLocalDatabaseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class RequirableViewModel @Inject constructor(
    /*
    private val getAllFromApiUseCase: GetAllFromApiUseCase,
    private val getArticleByIdUseCase: GetArticleByIdUseCase,
    private val updateConsumedQuantityUseCase: UpdateConsumedQuantityUseCase,
    private val getFilteredArticleListUseCase: GetFilteredArticleListUseCase,
    private val getAllFromLocalDatabaseUseCase: GetAllFromLocalDatabaseUseCase,
    private val addOneSyncToLocalDatabaseUseCase: AddOneSyncFromLocalDatabaseUseCase,
    //private val updateAllArticlesInLocalDatabaseUseCase: UpdateAllArticlesInLocalDatabaseUseCase,
    //private val addAllArticleToLocalDatabaseUseCase: AddAllArticleToLocalDatabaseUseCase,
    private val addManySyncConsumibleToLocalDatabaseUseCase: AddManySyncConsumibleToLocalDatabaseUseCase,
    private val reAddAllConsumibleToLocalDatabaseUseCase: ReAddAllConsumibleToLocalDatabaseUseCase

     */
) : ViewModel() {

    fun createReportable(descrition:String){
        CoroutineScope(Dispatchers.IO).launch {
            /*
            val reportable = Reportable(generatedId = time, photo= photoUrl,description = "")
            val reportableToSave = addOneReportableToLocalDatabaseUseCase.invoke(reportable)
            val reportableList = getAllReportablesFromLocalDatabaseUseCase.invoke()
            Log.e("TG",""+reportableList.size)
            //val reportableToSave = ReportableSaver().addOneReportableToLocalDatabase(reportable)
            val sync = Sync(objectId=Dates().dateAsInt(),dataType="Reportable", createdAt=Dates().geDateAsString())
            //val syncToSave = SyncSaver().addOneSyncToLOcalDatabase(sync)
            val syncToSave = addOneSyncFromLocalDatabaseUseCase.invoke(sync)
            val syncList = getAllSyncFromLocalDatabaseUseCase.invoke()
            Log.e("TG",""+syncList.size)
            */
        }
    }
    /*
    var articleList by mutableStateOf<List<Article>>(emptyList())
    var quantityToRestore by mutableStateOf<String>("")
    var toastSuccess by mutableStateOf<Boolean>(false)

    fun getAllFromApi() {
        Log.e("TAG","TAG")
        //viewModelScope.launch {
        CoroutineScope(Dispatchers.IO).launch {
            //val id = Calendar.getInstance().time
            val result = getAllFromApiUseCase.invoke()
            //val result = getAllFromLocalDatabaseUseCase.invoke()
            if (!result.isNullOrEmpty()) {
                articleList =result
            }
        }
    }

    fun getAllFromLocalDatabase() {
        Log.e("TAG","TAG")
        //viewModelScope.launch {
        CoroutineScope(Dispatchers.IO).launch {
            val result = getAllFromLocalDatabaseUseCase.invoke()
            if (!result.isNullOrEmpty()) {
                articleList =result
            }
        }
    }

    fun saveArticleListToSync(){
        var consumibleList = mutableListOf<SyncConsumible>()
        //TODO take another approach to create this pkey
        val sdf = SimpleDateFormat("MMdd hh:mm:ss")
        val currentDate = sdf.format(Date())
        val date = currentDate.filter {it in '0'..'9'}
        val objectId = date.toInt()
        var quantityAvailable = 0
        articleList.forEach { article ->
            quantityAvailable = article.quantityAvailable.toInt() - article.consumedQuantity.toInt()
            if (article.consumedQuantity> 0) {
                if (quantityAvailable > 0) {
                    article.quantityAvailable = quantityAvailable.toDouble()
                    consumibleList.add(SyncConsumible(objectId=objectId,consumptionId=0,articleCode=article.articleDescription,quantity=article.consumedQuantity,unitOfMeasure=article.unitOfMeasure,creationDate="2023-08-08T00:48:12.104Z",delivered=0))
                }
            }
        }
        CoroutineScope(Dispatchers.IO).launch {
            if (consumibleList.isNotEmpty()) {
                addOneSyncToLocalDatabaseUseCase.invoke(Sync(objectId=objectId, dataType = "Consumible",createdAt = "" + currentDate))
                addManySyncConsumibleToLocalDatabaseUseCase.invoke(consumibleList)
                articleList = reAddAllConsumibleToLocalDatabaseUseCase.invoke(articleList)
                toastSuccess=true

            }
        }
    }

    fun updateConsumedQuantity(idArticle:Int, consumibleNewQuantity:Int){
        CoroutineScope(Dispatchers.IO).launch {
            val xr = getArticleByIdUseCase(idArticle)
            val xf = xr.toString()
            val quantityToRestore = updateConsumedQuantityUseCase(idArticle,consumibleNewQuantity).toString()
            val article = getArticleByIdUseCase.invoke(idArticle)
            val article2 = article
            //update list from database
            //updateFilteredArticleList("")
            val rr = getArticleByIdUseCase(idArticle)
            val df = rr.toString()+" fffff"
            val df2 = df+" fffff"
        }
    }

    fun updateFilteredArticleList(hash:String){
        CoroutineScope(Dispatchers.IO).launch {
            val result = getFilteredArticleListUseCase.invoke("%$hash%")
            if (!result.isNullOrEmpty()) {
                articleList =result
            }
        }
    }
    //var productDescription by mutableStateOf<String>("")
    */
}