package com.david.tot.ui.consumible

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
import com.david.tot.domain.consumible.GetAllConsumiblesFromApiUseCase
import com.david.tot.domain.consumible.GetAllFromLocalDatabaseUseCase
import com.david.tot.domain.consumible.GetArticleByIdUseCase
import com.david.tot.domain.consumible.GetFilteredArticleListUseCase
import com.david.tot.domain.consumible.ReAddAllConsumibleToLocalDatabaseUseCase
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
class ConsumibleViewModel @Inject constructor(
    private val getAllConsumiblesFromApiUseCase: GetAllConsumiblesFromApiUseCase,
    private val getArticleByIdUseCase: GetArticleByIdUseCase,
    private val updateConsumedQuantityUseCase: UpdateConsumedQuantityUseCase,
    private val getFilteredArticleListUseCase: GetFilteredArticleListUseCase,
    private val getAllFromLocalDatabaseUseCase: GetAllFromLocalDatabaseUseCase,
    private val addOneSyncToLocalDatabaseUseCase: AddOneSyncFromLocalDatabaseUseCase,
    //private val updateAllArticlesInLocalDatabaseUseCase: UpdateAllArticlesInLocalDatabaseUseCase,
    //private val addAllArticleToLocalDatabaseUseCase: AddAllArticleToLocalDatabaseUseCase,
    private val addManySyncConsumibleToLocalDatabaseUseCase: AddManySyncConsumibleToLocalDatabaseUseCase,
    private val reAddAllConsumibleToLocalDatabaseUseCase: ReAddAllConsumibleToLocalDatabaseUseCase
) : ViewModel() {
    var articleList by mutableStateOf<List<Article>>(emptyList())
    var quantityToRestore by mutableStateOf<String>("")
    var toastSuccess by mutableStateOf<Boolean>(false)

    //var invepastoList by mutableStateOf<List<Asset>>(emptyList())

    /*
    fun actualizarLista(hash:String) {
        val list: MutableList<Asset> = mutableListOf()        // or, use `arrayListOf`
        assetList.forEach {
            if (it.name.contains(hash)
            ) {
                list.add(it)
            }
        }
        invepastoList= list.toList()
    }
     */


    fun getAllConsumiblesFromApi() {
        Log.e("TAG","TAG")
        //viewModelScope.launch {
        CoroutineScope(Dispatchers.IO).launch {
            //val id = Calendar.getInstance().time
            val result = getAllConsumiblesFromApiUseCase.invoke()
            //val result = getAllFromLocalDatabaseUseCase.invoke()
            if (!result.isNullOrEmpty()) {
                articleList =result
            }
        }
    }


    fun getAllRestocksFromApi() {
        Log.e("TAG","TAG")
        //viewModelScope.launch {
        CoroutineScope(Dispatchers.IO).launch {
            val result = getAllConsumiblesFromApiUseCase.invoke()
            if (!result.isNullOrEmpty()) {
                articleList =result
            }
        }
    }

    fun getAllConsumiblesFromLocalDatabase() {
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
                    consumibleList.add(SyncConsumible(objectId=objectId,consumptionId=0,articleCode=article.articleCode,quantity=article.consumedQuantity,unitOfMeasure=article.unitOfMeasure,creationDate="2023-08-08T00:48:12.104Z",delivered=0))
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

    /*
    fun saveArticleListToSync(context: Context):Int {
        var dataList = mutableListOf<Consumible>()
        //val prefs = FastPrefs(context)
        //val pattern = remember { Regex("^\\d+\$") }
        //var pattern by mutableStateOf<Regex>(Regex("^\\d+\$"))
        val failedList = mutableListOf<Int>()
        var quantityAvailable = 0
        articleList.forEach { article ->
            quantityAvailable = article.quantityAvailable.toInt() - article.consumedQuantity.toInt()

            if (article.consumedQuantity> 0) {
                if (quantityAvailable > 0) {
                    article.quantityAvailable = quantityAvailable.toDouble()
                    dataList.add(
                        Consumible(0, 1,
                            article.articleDescription,
                            article.consumedQuantity,
                            article.unitOfMeasure,
                            "2023-08-08T00:48:12.104Z",
                            0
                        )
                    )
                } else {
                    failedList.add(article.local_id)
                }
            }
        }
        if (failedList.isEmpty()) {
            //TODO virtualassembler
            /*
            prefs.set("mula", dataList)
            val gette = prefs.get("mula", dataList)
            Log.e("gette",""+gette)
            Log.e("gette",""+gette)
            */



            CoroutineScope(Dispatchers.IO).launch {
                addOneSyncFromLocalDatabaseUseCase.invoke(
                    Sync(1,1,"david", "Consumible",currentDate)
                )
                val syncPendingList = getAllSyncFromLocalDatabaseUseCase.invoke()
                val syncListToString = syncPendingList.toString()
                val result = addAllArticleToLocalDatabaseUseCase.invoke(articleList)
                if (!result.isNullOrEmpty()) {
                    articleList =result
                }
            }
            return 1
        } else {
            return 2
        }
    }

     */

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
}