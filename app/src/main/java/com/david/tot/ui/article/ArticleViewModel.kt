package com.david.tot.ui.article

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
import com.david.tot.domain.model.Sync
import com.david.tot.domain.sync.AddOneSyncFromLocalDatabaseUseCase
import com.david.tot.domain.sync.GetAllSyncFromLocalDatabaseUseCase
import com.yeslab.fastprefs.FastPrefs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class ArticleViewModel @Inject constructor(
    private val getAllFromApiUseCase: GetAllFromApiUseCase,
    private val getArticleByIdUseCase: GetArticleByIdUseCase,
    private val updateConsumedQuantityUseCase: UpdateConsumedQuantityUseCase,
    private val getFilteredArticleListUseCase: GetFilteredArticleListUseCase,
    private val getAllFromLocalDatabaseUseCase: GetAllFromLocalDatabaseUseCase,
    private val addOneSyncFromLocalDatabaseUseCase: AddOneSyncFromLocalDatabaseUseCase,
    private val getAllSyncFromLocalDatabaseUseCase: GetAllSyncFromLocalDatabaseUseCase,
    private val updateAllArticlesInLocalDatabaseUseCase: UpdateAllArticlesInLocalDatabaseUseCase,
    private val addAllArticleToLocalDatabaseUseCase: AddAllArticleToLocalDatabaseUseCase
) : ViewModel() {
    var articleList by mutableStateOf<List<Article>>(emptyList())
    var quantityToRestore by mutableStateOf<String>("")
    //TODO take another approach to create this pkey
    val sdf = SimpleDateFormat("MMdd hh:mm:ss")
    val currentDate = sdf.format(Date())
    val date = currentDate.filter {it in '0'..'9'}
    val key = date.toInt()

    fun onCreate() {
        Log.e("TAG","TAG")
        //viewModelScope.launch {
        CoroutineScope(Dispatchers.IO).launch {
            //val id = Calendar.getInstance().time
            val result = getAllFromApiUseCase.invoke()
            if (!result.isNullOrEmpty()) {
                articleList =result
            }
        }
    }

    fun saveArticleListToSync(context: Context):Int {
        var dataList = mutableListOf<Consumible>()
        val prefs = FastPrefs(context)
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
                        Consumible(
                            article.local_id,
                            key,
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
            prefs.set("" + key, dataList)
            CoroutineScope(Dispatchers.IO).launch {
                addOneSyncFromLocalDatabaseUseCase.invoke(
                    Sync(
                        key,
                        key,
                        "Consumible",
                        Date().toString(),
                        currentDate
                    )
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