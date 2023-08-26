package com.david.tot.ui.spendable

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

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SpendableViewModel @Inject constructor(
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

    //var toastNot by mutableStateOf<Boolean>(false)
    var visibleBoolean by mutableStateOf<Boolean>(false)

    fun createSpendable(descrition:String){
        CoroutineScope(Dispatchers.IO).launch {
            /*
            val spendable = Spendable(generatedId = time, photo= photoUrl,description = "")
            val spendableToSave = addOneSpendableToLocalDatabaseUseCase.invoke(spendable)
            val spendableList = getAllSpendablesFromLocalDatabaseUseCase.invoke()
            Log.e("TG",""+spendableList.size)
            //val spendableToSave = SpendableSaver().addOneSpendableToLocalDatabase(spendable)
            val sync = Sync(objectId=Dates().dateAsInt(),dataType="Spendable", createdAt=Dates().geDateAsString())
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