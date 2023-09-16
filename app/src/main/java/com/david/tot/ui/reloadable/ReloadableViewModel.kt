package com.david.tot.ui.reloadable

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.david.tot.domain.model.Reloadable
import com.david.tot.domain.model.Sync
import com.david.tot.domain.model.SyncReloadable
import com.david.tot.domain.reloadable.GetAllReloadablesFromApiUseCase
import com.david.tot.domain.reloadable.RetrieveAllReloadablesFromLocalDatabaseUseCase
import com.david.tot.domain.reloadable.GetFilteredReloadableListUseCase
import com.david.tot.domain.reloadable.ReAddAllReloadableToLocalDatabaseUseCase
import com.david.tot.domain.reloadable.RemoveAllReloadablesFromLocalDatabaseUseCase
import com.david.tot.domain.sync.AddOneSyncFromLocalDatabaseUseCase
import com.david.tot.domain.sync.reloadable.AddManySyncReloadableToLocalDatabaseUseCase
import com.david.tot.domain.sync.reloadable.RetrieveAllSyncReloadablesFromLocalDatabaseUseCase
import com.david.tot.ui.authenticable.fetchUser
import com.david.tot.util.Dates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class ReloadableViewModel @Inject constructor(

    private val getAllReloadablesFromApiUseCase: GetAllReloadablesFromApiUseCase,
    //private val getFilteredReloadableListUseCase: GetFilteredReloadableListUseCase,
    private val retrieveAllReloadablesFromLocalDatabaseUseCase: RetrieveAllReloadablesFromLocalDatabaseUseCase,
    private val addOneSyncToLocalDatabaseUseCase: AddOneSyncFromLocalDatabaseUseCase,
    private val addManySyncReloadableToLocalDatabaseUseCase: AddManySyncReloadableToLocalDatabaseUseCase,
    private val reAddAllReloadableToLocalDatabaseUseCase: ReAddAllReloadableToLocalDatabaseUseCase,
    private val removeAllReloadablesFromLocalDatabaseUseCase: RemoveAllReloadablesFromLocalDatabaseUseCase,
    private val retrieveAllSyncReloadablesFromLocalDatabaseUseCase: RetrieveAllSyncReloadablesFromLocalDatabaseUseCase
    /*
    private val getReloadableByIdUseCase: GetReloadableByIdUseCase,
    private val updateConsumedQuantityUseCase: UpdateConsumedQuantityUseCase,
    private val getFilteredArticleListUseCase: GetFilteredArticleListUseCase,
    private val getAllFromLocalDatabaseUseCase: GetAllFromLocalDatabaseUseCase,
    //private val updateAllArticlesInLocalDatabaseUseCase: UpdateAllArticlesInLocalDatabaseUseCase,
    //private val addAllArticleToLocalDatabaseUseCase: AddAllArticleToLocalDatabaseUseCase,
    private val reAddAllConsumibleToLocalDatabaseUseCase: ReAddAllConsumibleToLocalDatabaseUseCase
    */
) : ViewModel() {

    var reloadableList by mutableStateOf<List<Reloadable>>(emptyList())
    var quantityToRestore by mutableStateOf<String>("")
    var toastSuccess by mutableStateOf<Boolean>(false)
    var toastFaliledToSaveInLocalDatabase by mutableStateOf<Boolean>(false)

    fun getAllReloadablesFromApi(mContext: Context) {
        CoroutineScope(Dispatchers.IO).launch {
            reloadableList=getAllReloadablesFromApiUseCase.invoke(fetchUser(mContext),-1,"TO_DELIVER")
        }
    }


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


    /*
    fun getAllReloadablesFromApi() {
        Log.e("TAG","TAG")
        //viewModelScope.launch {
        CoroutineScope(Dispatchers.IO).launch {
            //val id = Calendar.getInstance().time
            val result = getAllReloadablesFromApiUseCase.invoke()
            //val result = getAllFromLocalDatabaseUseCase.invoke()
            if (!result.isNullOrEmpty()) {
                reloadableList =result
            }
        }
    }

    fun getAllRestocksFromApi() {
        Log.e("TAG","TAG")
        //viewModelScope.launch {
        CoroutineScope(Dispatchers.IO).launch {
            val result = getAllReloadablesFromApiUseCase.invoke()
            if (!result.isNullOrEmpty()) {
                reloadableList =result
            }
        }
    }
    */
    /*
        fun getAllFromLocalDatabase() {
            Log.e("TAG","TAG")
            //viewModelScope.launch {
            CoroutineScope(Dispatchers.IO).launch {
                val result = retrieveAllReloadablesFromLocalDatabaseUseCase.invoke()
                if (!result.isNullOrEmpty()) {
                    reloadableList =result
                }
            }
        }

        fun saveReloadableListToSync(){
            var syncReloadableList = mutableListOf<SyncReloadable>()
            //TODO take another approach to create this pkey
            val sdf = SimpleDateFormat("MMdd hh:mm:ss")
            val currentDate = sdf.format(Date())
            val date = currentDate.filter {it in '0'..'9'}
            val objectId = date.toInt()

            CoroutineScope(Dispatchers.IO).launch {
                if (reloadableList.isNotEmpty()) {
                    addOneSyncToLocalDatabaseUseCase.invoke(Sync(objectId=objectId, dataType = "Reloadable",createdAt = "" + Dates().date()))
                    reloadableList.forEach { reloadable ->
                        syncReloadableList.add(SyncReloadable(objectId=objectId,consumptionId=0,articleCode=reloadable.articleCode,quantity= reloadable.quantityToStock.toInt() ,unitOfMeasure=reloadable.unitOfMeasure,creationDate=""+ Dates().date(),delivered=0))
                    }
                    addManySyncReloadableToLocalDatabaseUseCase.invoke(syncReloadableList)
                    val syncReloadablesCant = retrieveAllSyncReloadablesFromLocalDatabaseUseCase.invoke()
                    if(syncReloadablesCant.size==reloadableList.size){
                        if(removeAllReloadablesFromLocalDatabaseUseCase.invoke()==reloadableList.size){
                            toastSuccess=true
                        }
                    }else{
                        toastFaliledToSaveInLocalDatabase=true
                    }
                }
            }
        }
    */
    /*
    //NO BORRAR
    //Asi es como estaba funcionando antes que permitia editar la cantidad igual que en los consumibles
    //y solo sincronizaba lo que hubieran pedido
    fun saveReloadableListToSync(){
        var syncReloadableList = mutableListOf<SyncReloadable>()
        //TODO take another approach to create this pkey
        val sdf = SimpleDateFormat("MMdd hh:mm:ss")
        val currentDate = sdf.format(Date())
        val date = currentDate.filter {it in '0'..'9'}
        val objectId = date.toInt()
        //var quantityAvailable = 0.0
        reloadableList.forEach { reloadable ->
            //quantityAvailable = reloadable.quantityAvailable - reloadable.quantityConsumed
            //if (reloadable.quantityConsumed> 0.0) {
                //if (quantityAvailable > 0) {
                    //reloadable.quantityAvailable = quantityAvailable
                    syncReloadableList.add(SyncReloadable(objectId=objectId,consumptionId=0,articleCode=reloadable.articleCode,quantity= reloadable.quantityToStock.toInt() ,unitOfMeasure=reloadable.unitOfMeasure,creationDate=""+ Dates().date(),delivered=0))
                //}
            //}
        }
        CoroutineScope(Dispatchers.IO).launch {
            if (syncReloadableList.isNotEmpty()) {

                addOneSyncToLocalDatabaseUseCase.invoke(Sync(objectId=objectId, dataType = "Reloadable",createdAt = "" + currentDate))
                //Eliminar de la base de datos
                addManySyncReloadableToLocalDatabaseUseCase.invoke(syncReloadableList)
                //Actualizar listado con lo que quedo en la base de datos
                reloadableList = reAddAllReloadableToLocalDatabaseUseCase.invoke(reloadableList)
                toastSuccess=true
            }
        }
    }

     */

    /*
    fun saveReloadableListToSync(){
        var syncReloadableList = mutableListOf<SyncReloadable>()
        //TODO take another approach to create this pkey
        val sdf = SimpleDateFormat("MMdd hh:mm:ss")
        val currentDate = sdf.format(Date())
        val date = currentDate.filter {it in '0'..'9'}
        val objectId = date.toInt()
        var quantityAvailable = 0.0
        reloadableList.forEach { reloadable ->
            quantityAvailable = reloadable.quantityAvailable - reloadable.quantityConsumed
            if (reloadable.quantityConsumed> 0.0) {
                if (quantityAvailable > 0) {
                    reloadable.quantityAvailable = quantityAvailable
                    syncReloadableList.add(SyncReloadable(objectId=objectId,consumptionId=0,articleCode=reloadable.articleCode,quantity= reloadable.quantityConsumed ,unitOfMeasure=reloadable.unitOfMeasure,creationDate=""+ Dates().date(),delivered=0))
                }
            }
        }
        CoroutineScope(Dispatchers.IO).launch {
            if (syncReloadableList.isNotEmpty()) {
                addOneSyncToLocalDatabaseUseCase.invoke(Sync(objectId=objectId, dataType = "Reloadable",createdAt = "" + currentDate))
                addManySyncReloadableToLocalDatabaseUseCase.invoke(syncReloadableList)
                reloadableList = reAddAllReloadableToLocalDatabaseUseCase.invoke(reloadableList)
                toastSuccess=true
            }
        }
    }
    */



     /*


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
               val xr = getReloadableByIdUseCase(idArticle)
               val xf = xr.toString()
               val quantityToRestore = updateConsumedQuantityUseCase(idArticle,consumibleNewQuantity).toString()
               val article = getReloadableByIdUseCase.invoke(idArticle)
               val article2 = article
               //update list from database
               //updateFilteredArticleList("")
               val rr = getReloadableByIdUseCase(idArticle)
               val df = rr.toString()+" fffff"
               val df2 = df+" fffff"
           }
       }
    */

    /*
    fun updateFilteredArticleList(hash:String){
        CoroutineScope(Dispatchers.IO).launch {
            val result = getFilteredReloadableListUseCase.invoke("%$hash%")
            if (!result.isNullOrEmpty()) {
                reloadableList =result
            }
        }
    }
    //var productDescription by mutableStateOf<String>("")

     */

}