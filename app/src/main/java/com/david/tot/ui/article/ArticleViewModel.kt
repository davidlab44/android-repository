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
class ArticleViewModel @Inject constructor(
    private val getAllFromApiUseCase: GetAllFromApiUseCase,
    private val getArticleByIdUseCase: GetArticleByIdUseCase,
    private val updateConsumedQuantityUseCase: UpdateConsumedQuantityUseCase,
    private val getFilteredArticleListUseCase: GetFilteredArticleListUseCase,
    private val getAllFromLocalDatabaseUseCase: GetAllFromLocalDatabaseUseCase,
    private val addOneSyncFromLocalDatabaseUseCase: AddOneSyncFromLocalDatabaseUseCase,
    private val getAllSyncFromLocalDatabaseUseCase: GetAllSyncFromLocalDatabaseUseCase,
) : ViewModel() {
    var articleList by mutableStateOf<List<Article>>(emptyList())
    var quantityToRestore by mutableStateOf<String>("")
    var key by mutableStateOf<Int>(20230808)

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
            var pattern by mutableStateOf<Regex>(Regex("^\\d+\$"))
        val failedList = mutableListOf<Int>()

            var quantityAvailable = 0

            articleList.forEach { article -> quantityAvailable = article.quantityAvailable.toInt()-article.consumedQuantity.toInt()
                if(article.consumedQuantity.toInt()>0){
                    if(quantityAvailable>0){
                        article.quantityAvailable = quantityAvailable.toDouble()
                        dataList.add(Consumible(article.local_id,key,article.articleDescription,article.consumedQuantity,article.unitOfMeasure,"2023-08-08T00:48:12.104Z",0))
                        //updateConsumedQuantity(article.local_id.toInt(),requiredQuantity)
                        //updateFilteredArticleList("")

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
            /*
            articleList.forEach {article->
                if(article.consumedQuantity.toInt()>0){
                    if(requiredQuantity>0 && requiredQuantity<=article.quantityAvailable.toInt()){
                        articleViewModel.updateConsumedQuantity(article.local_id.toInt(),requiredQuantity)
                        articleViewModel.updateFilteredArticleList("")
                    }else{
                        Toast.makeText(mContext,"No hay suficiente cantidad en inventario ", Toast.LENGTH_SHORT).show()
                    }
                }else{
                    Toast.makeText(mContext,"La cantidad ingresada debe ser un NUMERO ENTERO sin puntos ni espacios"+it.trim().toInt(), Toast.LENGTH_LONG).show()
                }
            }
            */



    }
    /*
    fun getAllFromLocalDatabase() {
        CoroutineScope(Dispatchers.IO).launch {
            val result = getAllFromLocalDatabaseUseCase.invoke()
            val rt = result.toString()
            if (!result.isNullOrEmpty()) {
                articleModel =result
            }
            else{
                Log.e("TAG","TAG")
            }
        }
    }
    */

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

    /*
    fun getArticleByIdUseCase(idArticle:Int){
        CoroutineScope(Dispatchers.IO).launch {
        }
    }
    */

    fun updateFilteredArticleList(hash:String){
        CoroutineScope(Dispatchers.IO).launch {
            val result = getFilteredArticleListUseCase.invoke("%$hash%")
            if (!result.isNullOrEmpty()) {
                articleList =result
            }
        }
    }

    /*
    fun addProduct(product:Product){
        //TODO mostrar lo que retorna el producto creado  o por lo menos un aconfirmacion visual para  el usuario de que si se creo el producto
        CoroutineScope(Dispatchers.IO).launch {
            addProductUseCase.invoke(product)
        }
    }
    */

    //Edit Product
    //the purpose of this group of variables is jus pass the necesary information to Screen Detail in order to create bundle
    var productLocalId by mutableStateOf<Int>(0)
    var productRemoteId by mutableStateOf<Int>(0)
    var productName by mutableStateOf<String>("")
    var productDescription by mutableStateOf<String>("")
    var productImage by mutableStateOf<String>("")
    var productPrice by mutableStateOf<Int>(0)
}