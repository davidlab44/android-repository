package com.david.tot.ui.sync

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.david.tot.domain.authenticable.AddOneAuthenticableToLocalDbUseCase
import com.david.tot.domain.consumible.PostManyArticleUseCase
import com.david.tot.domain.authenticable.GetAnyAuthenticableUseCase
import com.david.tot.domain.authenticable.PostOneAuthenticableUseCase
import com.david.tot.domain.authenticable.PostOneReloadableHeaderUseCase
import com.david.tot.domain.authenticable.RetrieveAllAuthenticablesFromLocalDbUseCase
import com.david.tot.domain.consumible.GetAllConsumiblesFromApiUseCase
import com.david.tot.domain.model.Authenticable
import com.david.tot.domain.model.Consumible
import com.david.tot.domain.model.ConsumibleHeader
import com.david.tot.domain.model.ReloadableClean
import com.david.tot.domain.model.ReloadableHeader
import com.david.tot.domain.model.Sync
import com.david.tot.domain.model.SyncConsumible
import com.david.tot.domain.model.SyncReloadable
import com.david.tot.domain.reloadable.GetAllReloadablesFromApiUseCase
import com.david.tot.domain.reloadable.PostManyReloadableUseCase
import com.david.tot.domain.sync.GetAllSyncFromLocalDatabaseUseCase
import com.david.tot.domain.sync.RemoveOneSyncFromLocalDatabaseUseCase
import com.david.tot.domain.sync.consumible.RemoveManySyncConsumiblesFromLocalDatabaseUseCase
import com.david.tot.domain.sync.consumible.GetAllSyncConsumibleFromLocalDatabaseUseCase
import com.david.tot.domain.sync.reloadable.GetAllSyncReloadablesByDatatypeFromLocaDatabaseUseCase
import com.david.tot.domain.sync.reloadable.RemoveManySyncReloadablesByObjectIdFromLocalDatabaseUseCase
import com.google.gson.Gson
import com.google.gson.JsonArray
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SyncViewModel @Inject constructor(
    private val postOneConsumibleHeaderUseCase: PostOneAuthenticableUseCase,
    private val postManyArticleUseCase: PostManyArticleUseCase,
    private val postManyReloadableDetailUseCase: PostManyReloadableUseCase,
    private val getAllSyncFromLocalDatabaseUseCase: GetAllSyncFromLocalDatabaseUseCase,
    private val getAllSyncConsumibleFromLocalDatabaseUseCase: GetAllSyncConsumibleFromLocalDatabaseUseCase,
    private val getAllSyncReloadablesByDatatypeFromLocaDatabaseUseCase: GetAllSyncReloadablesByDatatypeFromLocaDatabaseUseCase,
    private val removeManySyncConsumiblesFromLocalDatabaseUseCase: RemoveManySyncConsumiblesFromLocalDatabaseUseCase,
    private val removeManySyncReloadablesByObjectIdFromLocalDatabaseUseCase: RemoveManySyncReloadablesByObjectIdFromLocalDatabaseUseCase,
    private val removeOneSyncFromLocalDatabaseUseCase: RemoveOneSyncFromLocalDatabaseUseCase,
    private val getAnyAuthenticableUseCase:GetAnyAuthenticableUseCase,
    private val getAllConsumiblesFromApiUseCase: GetAllConsumiblesFromApiUseCase,
    private val addOneAuthenticableToLocalDbUseCase: AddOneAuthenticableToLocalDbUseCase,
    private val retrieveAllAuthenticablesFromLocalDbUseCase: RetrieveAllAuthenticablesFromLocalDbUseCase,
    private val getAllReloadablesFromApiUseCase: GetAllReloadablesFromApiUseCase,
    private val postOneReloadableHeaderUseCase: PostOneReloadableHeaderUseCase,
) : ViewModel() {

    var syncList by mutableStateOf<List<Sync>>(emptyList())
    var quantityToRestore by mutableStateOf<String>("")
    var isSyncing by mutableStateOf<Boolean>(false)
    var toastTheresNotConsumiblesToSync by mutableStateOf<Boolean>(false)
    var toastConsumiblesSynced by mutableStateOf<Boolean>(false)
    var toastInsertedSuccessfully by mutableStateOf<Boolean>(false)

    fun getAllSyncsFromLocalDatabase() {
        //viewModelScope.launch {
        CoroutineScope(Dispatchers.IO).launch {
            syncList = getAllSyncFromLocalDatabaseUseCase.invoke()
        }
    }

    suspend fun postManyConsumibleToApi() {
        CoroutineScope(Dispatchers.IO).launch {
            var syncConsumibleList by mutableStateOf<List<SyncConsumible>>(emptyList())
            syncConsumibleList = getAllSyncConsumibleFromLocalDatabaseUseCase.invoke("Consumible")
            if(syncConsumibleList.isNotEmpty()){
                isSyncing=true
                //No confundir, este es header de la UI pero el tipo de dato ConsumibleHeader es el header del manifiesto
                val consumibleHeader=getAnyAuthenticableUseCase.invoke()
                var headerCons = ConsumibleHeader(0,consumibleHeader.consumer,consumibleHeader.vehicle,"PENDING","2023-08-10T01:42:45.655Z",0)
                var gson = Gson()
                var headerConsumible = gson.toJson(headerCons)
                val consumibleHeaderId =postOneConsumibleHeaderUseCase.invoke(headerConsumible)
                //La lista para el foreach
                var temp2ConsumibleList by mutableStateOf<List<Consumible>>(emptyList())
                var tempConsumibleMutableList =temp2ConsumibleList.toMutableList()
                var syncConsumibleToDeleteObjectId=0
                if (!syncConsumibleList.isNullOrEmpty()&&consumibleHeaderId.toInt()>0) {
                    syncConsumibleList.forEach { syncConsumible->
                        val consumible = Consumible(0,consumibleHeaderId,syncConsumible.articleCode,syncConsumible.quantity,"UND","2023-08-10T01:42:45.655Z",0)
                        tempConsumibleMutableList.add(consumible)
                        syncConsumibleToDeleteObjectId=syncConsumible.objectId
                    }
                    val jsonArray: JsonArray = Gson().toJsonTree(tempConsumibleMutableList).asJsonArray
                    val postManyArticleUseCase = postManyArticleUseCase.invoke(jsonArray)
                    if(postManyArticleUseCase in 200..300){
                        val numberofDeletedRows= removeManySyncConsumiblesFromLocalDatabaseUseCase.invoke(syncReloadableToDeleteId)
                        //Comprobar que elimino el elemento de la tabla sync antes de eliminar las filas del reloadable detalle
                        if(numberofDeletedRows>0){
                            val syncToRemove = removeOneSyncFromLocalDatabaseUseCase(syncConsumibleToDeleteObjectId)
                            postManyConsumibleToApi()
                        }else{
                            //Aqui Termino de syncronizar
                            getAllConsumiblesFromApiUseCase.invoke()
                        }
                    }
                }else{
                    Log.e("TAG","Sync de Consumibles  finalizada")
                }
            }
        }
    }

    suspend fun postAllPendingReloadablesToApi() {
        CoroutineScope(Dispatchers.IO).launch {
            var syncReloadableList by mutableStateOf<List<SyncReloadable>>(emptyList())
            syncReloadableList = getAllSyncReloadablesByDatatypeFromLocaDatabaseUseCase.invoke("Reloadable")
            if(syncReloadableList.isNotEmpty()){
                //No confundir, este es header de la UI pero el tipo de dato ConsumibleHeader es el header del manifiesto
                //Aqui en reloadable se puede usar el mismo header de manifiesto que el de los consumibles
                val reloadableHeader=getAnyAuthenticableUseCase.invoke()
                var headerCons = ReloadableHeader(0,reloadableHeader.consumer,reloadableHeader.vehicle,"PENDING","2023-08-10T01:42:45.655Z",0)
                var gson = Gson()
                var reloadableHeaderJsonObject = gson.toJson(headerCons)
                val reloadableHeaderId =postOneReloadableHeaderUseCase.invoke(reloadableHeaderJsonObject)
                /*
                //Verificar si todavia queda algun objeto Reloadable pendiente por sincronizar
                traer todos LOS ELEMENTOS DE LA TABLA SyncReloadable en esta lista
                recuerda que todos los Sync reloadables tienen un syncID que los identifica en la tabla SYncTable y los agrupa en este listado
                cuales corresponden a que objeto
                */
                //si esta vacio fue porque no consiguio nada en bd => no hace nada y sale
                var reloadableList by mutableStateOf<List<ReloadableClean>>(emptyList())
                var reloadableCleanMutableList = reloadableList.toMutableList()
                var syncReloadableToDeleteId=0
                if (!syncReloadableList.isNullOrEmpty()&&reloadableHeaderId.toInt()>0) {
                    syncReloadableList.forEach { syncReloadable->
                        val reloadableClean = ReloadableClean(0,reloadableHeaderId,syncReloadable.articleCode,syncReloadable.quantity,"UND","2023-08-10T01:42:45.655Z",0)
                        reloadableCleanMutableList.add(reloadableClean)
                        syncReloadableToDeleteId=syncReloadable.objectId
                    }
                    val jsonArray: JsonArray = Gson().toJsonTree(reloadableCleanMutableList).asJsonArray
                    //REEMPLAZAR POR CONSUMIBLE
                    val postManyReloadableDetail = postManyReloadableDetailUseCase.invoke(jsonArray)
                    if(postManyReloadableDetail in 200..300){
                        val numberofDeletedRows= removeManySyncReloadablesByObjectIdFromLocalDatabaseUseCase.invoke(syncReloadableToDeleteId)
                        //Comprobar que elimino el elemento de la tabla sync antes de eliminar las filas del reloadable detalle
                        if(numberofDeletedRows>0){
                            val syncToRemove = removeOneSyncFromLocalDatabaseUseCase(syncReloadableToDeleteId)
                            //if(getAllSyncReloadablesByDatatypeFromLocaDatabaseUseCase.invoke("Reloadable").isNotEmpty()){
                            postAllPendingReloadablesToApi()
                        }else{
                            //Aqui Termino de syncronizar
                            getAllReloadablesFromApiUseCase.invoke()
                        }
                    }
                }else{
                    Log.e("TAG","Sync de Reloadables  finalizada")
                }
            }
        }
    }


    fun addOneHardcodedAuthenticableToLocalDb(){
        CoroutineScope(Dispatchers.IO).launch {
            addOneAuthenticableToLocalDbUseCase.invoke(Authenticable(0,"CARLOS ORTEGA","1041545874","B","01/01/1900","HFQ753","","31/12/2018","","31/12/2018"))
            val authenticableList = retrieveAllAuthenticablesFromLocalDbUseCase.invoke()
            Log.e("TH",""+authenticableList)
        }
    }

    fun getAllAppDataFromApi(){
        CoroutineScope(Dispatchers.IO).launch {
            //Actualiza whole data en la base de datos local


            addOneHardcodedAuthenticableToLocalDb()
        }
    }
}