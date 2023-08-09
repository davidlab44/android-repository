package com.david.tot.ui.sync

import android.content.Context
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
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class SyncViewModel @Inject constructor(
    private val getAllSyncFromLocalDatabaseUseCase: GetAllSyncFromLocalDatabaseUseCase,
) : ViewModel() {
    var articleList by mutableStateOf<List<Sync>>(emptyList())
    var quantityToRestore by mutableStateOf<String>("")
    var key by mutableStateOf<Int>(20230808)

    fun onCreate() {
        Log.e("TAG","TAG")
        //viewModelScope.launch {
        CoroutineScope(Dispatchers.IO).launch {
            //val id = Calendar.getInstance().time
            val result = getAllSyncFromLocalDatabaseUseCase.invoke()
            if (!result.isNullOrEmpty()) {
                articleList =result
            }
        }
    }


    /*
    fun saveArticleListToSync(context: Context):Int {
        var dataList = mutableListOf<Consumible>()
        val prefs = FastPrefs(context)
        //val pattern = remember { Regex("^\\d+\$") }
        var pattern by mutableStateOf<Regex>(Regex("^\\d+\$"))
        val failedList = mutableListOf<Int>()
        var quantityAvailable = 0
        articleList.forEach { article -> quantityAvailable = article.quantityAvailable.toInt()-article.consumedQuantity.toInt()
            if(article.consumedQuantity.toInt()>0){
                if(quantityAvailable>0){
                    article.quantityAvailable = quantityAvailable.toDouble()
                    dataList.add(Consumible(article.local_id,key,article.articleDescription,article.consumedQuantity,article.unitOfMeasure,"2023-08-08T00:48:12.104Z",0))
                }else{
                    failedList.add(article.local_id)
                }
            }
        }
        if(failedList.isEmpty()){
            //Guardar en shared preferences
            //Guardar en la base de datos
            prefs.set(""+key,dataList)
            CoroutineScope(Dispatchers.IO).launch {
                addOneSyncFromLocalDatabaseUseCase.invoke(Sync(key, "Consumible", Date().toString(),key))
                val syncPendingList = getAllSyncFromLocalDatabaseUseCase.invoke()
                val syncListToString = syncPendingList.toString()
            }

            return 1
        }else{
            //retorna un aviso que faltan
            return 2
        }
    }

    fun updateConsumedQuantity(idArticle:Int, consumibleNewQuantity:Int){
        CoroutineScope(Dispatchers.IO).launch {
            val xr = getArticleByIdUseCase(idArticle)
            val xf = xr.toString()
            //Thread.sleep(1000)
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
    */


}