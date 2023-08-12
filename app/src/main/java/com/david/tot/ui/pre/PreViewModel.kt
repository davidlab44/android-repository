package com.david.tot.ui.pre

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

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.david.tot.domain.UpdateConsumedQuantityUseCase
import com.david.tot.domain.article.AddAllArticleToLocalDatabaseUseCase
import com.david.tot.domain.article.GetAllFromApiUseCase
import com.david.tot.domain.article.GetAllFromLocalDatabaseUseCase
import com.david.tot.domain.article.GetArticleByIdUseCase
import com.david.tot.domain.article.GetFilteredArticleListUseCase
import com.david.tot.domain.article.UpdateAllArticlesInLocalDatabaseUseCase
import com.david.tot.domain.model.Article
import com.david.tot.domain.model.Consumible
import com.david.tot.domain.model.Pre
import com.david.tot.domain.model.Sync
import com.david.tot.domain.pre.AddOnePreFromLocalDatabaseUseCase
import com.david.tot.domain.pre.GetAllDvFromLocalDatabaseUseCase
import com.david.tot.domain.pre.GetAllPreFromLocalDatabaseUseCase
import com.david.tot.domain.sync.AddOneSyncFromLocalDatabaseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class PreViewModel @Inject constructor(
    private val addOnePreFromLocalDatabaseUseCase: AddOnePreFromLocalDatabaseUseCase,
    private val getAllPreFromLocalDatabaseUseCase: GetAllPreFromLocalDatabaseUseCase,
    private val getAllDvFromLocalDatabaseUseCase: GetAllDvFromLocalDatabaseUseCase,
    /*
    private val getAllFromApiUseCase: GetAllFromApiUseCase,
    private val getArticleByIdUseCase: GetArticleByIdUseCase,
    private val updateConsumedQuantityUseCase: UpdateConsumedQuantityUseCase,
    private val getFilteredArticleListUseCase: GetFilteredArticleListUseCase,
    private val getAllFromLocalDatabaseUseCase: GetAllFromLocalDatabaseUseCase,
    private val addOneSyncFromLocalDatabaseUseCase: AddOneSyncFromLocalDatabaseUseCase,
    private val getAllSyncFromLocalDatabaseUseCase: GetAllSyncFromLocalDatabaseUseCase,
    private val updateAllArticlesInLocalDatabaseUseCase: UpdateAllArticlesInLocalDatabaseUseCase,
    private val addAllArticleToLocalDatabaseUseCase: AddAllArticleToLocalDatabaseUseCase
     */
) : ViewModel() {
    var preList by mutableStateOf<List<Article>>(emptyList())
    /*
    var quantityToRestore by mutableStateOf<String>("")

   */

    val pres = addOnePreFromLocalDatabase()



    fun addOnePreFromLocalDatabase(){
        CoroutineScope(Dispatchers.IO).launch {
            addOnePreFromLocalDatabaseUseCase.invoke(Pre(0,"gasolina",0))
        }
    }


    fun getAllPreFromLocalDatabase(){
        CoroutineScope(Dispatchers.IO).launch {
            val muchasPre = getAllPreFromLocalDatabaseUseCase.invoke()
            Log.e("TAG","PRA"+muchasPre)
            val muchasDv = getAllDvFromLocalDatabaseUseCase.invoke()
            Log.e("TAG","PRA"+muchasDv)
            Log.e("TAG","PRA"+muchasDv.toString())
            Log.e("TAG","PRA"+muchasDv.size)

        }
    }

    /*

        fun getAllFromApi() {
            Log.e("TAG","TAG")
            //viewModelScope.launch {
            CoroutineScope(Dispatchers.IO).launch {
                //val id = Calendar.getInstance().time
                val result = getAllFromApiUseCase.invoke()
                //val result = getAllFromLocalDatabaseUseCase.invoke()
                if (!result.isNullOrEmpty()) {
                    preList =result
                }
            }
        }
        */
/*
    fun getAllFromLocalDatabase() {
        Log.e("TAG","TAG")
        //viewModelScope.launch {
        CoroutineScope(Dispatchers.IO).launch {
            //val id = Calendar.getInstance().time
            //val result = getAllFromApiUseCase.invoke()
            val result = getAllFromLocalDatabaseUseCase.invoke()
            if (!result.isNullOrEmpty()) {
                preList =result
            }
        }
    }

    fun saveArticleListToSync(context: Context):Int {
        var dataList = mutableListOf<Consumible>()
        //val prefs = FastPrefs(context)
        //val pattern = remember { Regex("^\\d+\$") }
        //var pattern by mutableStateOf<Regex>(Regex("^\\d+\$"))
        val failedList = mutableListOf<Int>()
        var quantityAvailable = 0
        preList.forEach { article ->
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
                val result = addAllArticleToLocalDatabaseUseCase.invoke(preList)
                if (!result.isNullOrEmpty()) {
                    preList =result
                }
            }
            return 1
        } else {
            return 2
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
                preList =result
            }
        }
    }
    //var productDescription by mutableStateOf<String>("")

 */
}