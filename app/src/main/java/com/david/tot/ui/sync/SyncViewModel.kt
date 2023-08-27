package com.david.tot.ui.sync

import android.content.Context
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
import com.david.tot.domain.spotable.GetAllSpotablesFromApiUseCase
import com.david.tot.domain.sync.GetAllSyncFromLocalDatabaseUseCase
import com.david.tot.domain.sync.RemoveOneSyncFromLocalDatabaseUseCase
import com.david.tot.domain.sync.consumible.RemoveManySyncConsumiblesFromLocalDatabaseUseCase
import com.david.tot.domain.sync.consumible.GetAllSyncConsumibleFromLocalDatabaseUseCase
import com.david.tot.domain.sync.reloadable.GetAllSyncReloadablesByDatatypeFromLocaDatabaseUseCase
import com.david.tot.domain.sync.reloadable.RemoveManySyncReloadablesByObjectIdFromLocalDatabaseUseCase
import com.david.tot.util.Dates
import com.david.tot.util.hasConnection
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
    private val getAllSpotablesFromApiUseCase: GetAllSpotablesFromApiUseCase,
) : ViewModel() {

    var syncList by mutableStateOf<List<Sync>>(emptyList())
    var quantityToRestore by mutableStateOf<String>("")
    var isSyncing by mutableStateOf<Boolean>(false)
    var toastTheresNotConsumiblesToSync by mutableStateOf<Boolean>(false)
    var toastConsumiblesSynced by mutableStateOf<Boolean>(false)
    var toastReloadablesSynced by mutableStateOf<Boolean>(false)
    var toastInsertedSuccessfully by mutableStateOf<Boolean>(false)
    var toastNotInternetConnection by mutableStateOf<Boolean>(false)

    fun sync(mContext:Context){
        if(!hasConnection(mContext)){
            toastNotInternetConnection=true
            return
        }
        CoroutineScope(Dispatchers.IO).launch {
            addOneHardcodedAuthenticableToLocalDb()
            postManyConsumibleToApi(mContext)
            postAllPendingReloadablesToApi()
            // Este espacio es para la syncronizacion rapida, por ejemplo de consumibles y demas cosas que requieran ser actualizadas rapidamente
            // Buscar un lugar diferente para la syncronizacion de los Sports y demas cosas no importantes
            // Este lugar es para la syncronizacion rapida
        }
    }

    fun sync2Spotables(mContext:Context){
        if(!hasConnection(mContext)){
            toastNotInternetConnection=true
            return
        }
        CoroutineScope(Dispatchers.IO).launch {
            getAllSpotablesFromApiUseCase.invoke()
        }
    }

    fun getAllSyncsFromLocalDatabase() {
        //viewModelScope.launch {
        CoroutineScope(Dispatchers.IO).launch {
            syncList = getAllSyncFromLocalDatabaseUseCase.invoke()
        }
    }

    suspend fun postManyConsumibleToApi(mContext: Context) {
        if(!hasConnection(mContext)){
            toastNotInternetConnection
            return
        }
        CoroutineScope(Dispatchers.IO).launch {
            var syncConsumibleList by mutableStateOf<List<SyncConsumible>>(emptyList())
            syncConsumibleList = getAllSyncConsumibleFromLocalDatabaseUseCase.invoke("Consumible")
            if(syncConsumibleList.isNotEmpty()){
                isSyncing=true
                //No confundir, este es header de la UI pero el tipo de dato ConsumibleHeader es el header del manifiesto
                //val consumibleHeader=getAnyAuthenticableUseCase.invoke()
                //var headerCons = ConsumibleHeader(0,consumibleHeader.consumer,consumibleHeader.vehicle,"PENDING",""+Dates().date(),0)
                //var gson = Gson()
                //var headerConsumible = gson.toJson(headerCons)
                //val consumibleHeaderId =postOneConsumibleHeaderUseCase.invoke(headerConsumible)
                //La lista para el foreach
                var temp2ConsumibleList by mutableStateOf<List<Consumible>>(emptyList())
                var tempConsumibleMutableList =temp2ConsumibleList.toMutableList()
                var syncConsumibleToDeleteObjectId=0
                //if (!syncConsumibleList.isNullOrEmpty()&&consumibleHeaderId.toInt()>0) {
                syncConsumibleList.forEach { syncConsumible->
                    //2023-08-10T01:42:45.655Z
                    val consumible = Consumible(0,0,syncConsumible.articleCode,syncConsumible.quantity,"UND",""+Dates().date(),0)
                    tempConsumibleMutableList.add(consumible)
                    syncConsumibleToDeleteObjectId=syncConsumible.objectId
                }
                val jsonArray: JsonArray = Gson().toJsonTree(tempConsumibleMutableList).asJsonArray
                val postManyArticleUseCase = postManyArticleUseCase.invoke(jsonArray)
                if(postManyArticleUseCase in 200..300){
                    val numberOfDeletedSync = removeOneSyncFromLocalDatabaseUseCase(syncConsumibleToDeleteObjectId)
                    //Si lo elimino devuelve 1 row affected
                    if(numberOfDeletedSync>0){
                        val numberofDeletedSyncConsumibles= removeManySyncConsumiblesFromLocalDatabaseUseCase.invoke(syncConsumibleToDeleteObjectId)
                        if(numberofDeletedSyncConsumibles>0){
                            //val isReloadableEmpty =getAllSyncReloadablesByDatatypeFromLocaDatabaseUseCase.invoke("Reloadable")
                            postManyConsumibleToApi(mContext)
                        }
                    }
                }
            }else{
                getAllConsumiblesFromApiUseCase.invoke()
                Log.e("TAG","Sync de Consumibles  finalizada")
                isSyncing=false
                toastConsumiblesSynced=true
                getAllSyncsFromLocalDatabase()
            }
        }
    }

    suspend fun postAllPendingReloadablesToApi() {
        CoroutineScope(Dispatchers.IO).launch {
            var syncReloadableList by mutableStateOf<List<SyncReloadable>>(emptyList())
            syncReloadableList = getAllSyncReloadablesByDatatypeFromLocaDatabaseUseCase.invoke("Reloadable")
            Log.e("TAGTAGTAG",""+syncReloadableList)
            if(syncReloadableList.isNotEmpty()){
                //No confundir, este es header de la UI pero el tipo de dato ConsumibleHeader es el header del manifiesto
                //Aqui en reloadable se puede usar el mismo header de manifiesto que el de los consumibles
                isSyncing=true
                //val reloadableHeader=getAnyAuthenticableUseCase.invoke()
                //var headerCons = ReloadableHeader(restockerUser=reloadableHeader.consumer,vehicle=reloadableHeader.vehicle,status="TO_DELIVER")
                //var gson = Gson()
                //var reloadableHeaderJsonObject = gson.toJson(headerCons)
                //val reloadableHeaderId =postOneReloadableHeaderUseCase.invoke(reloadableHeaderJsonObject)
                //Log.e("TAGreloadableHeaderId",""+reloadableHeaderId)
                var reloadableList by mutableStateOf<List<ReloadableClean>>(emptyList())
                var reloadableCleanMutableList = reloadableList.toMutableList()
                var syncReloadableToDeleteObjectId=0
                //if (!syncReloadableList.isNullOrEmpty()&&reloadableHeaderId.toInt()>0) {
                if (!syncReloadableList.isNullOrEmpty()) {
                    syncReloadableList.forEach { syncReloadable->
                        val reloadableClean = ReloadableClean(0,0,syncReloadable.articleCode,syncReloadable.quantity,"UND",""+Dates().date(),0)
                        reloadableCleanMutableList.add(reloadableClean)
                        syncReloadableToDeleteObjectId=syncReloadable.objectId
                    }
                    val jsonArray: JsonArray = Gson().toJsonTree(reloadableCleanMutableList).asJsonArray
                    val postManyReloadableDetailResponseCode = postManyReloadableDetailUseCase.invoke(jsonArray)
                    if(postManyReloadableDetailResponseCode in 200..300){
                        //Elimina el sync de este objeto
                        val numberOfDeletedSync = removeOneSyncFromLocalDatabaseUseCase(syncReloadableToDeleteObjectId)
                        //Si lo elimino devuelve 1 row affected
                        if(numberOfDeletedSync>0){
                            //Elimina los sync reloadables de este objeto
                            val numberofDeletedSyncReloadables= removeManySyncReloadablesByObjectIdFromLocalDatabaseUseCase.invoke(syncReloadableToDeleteObjectId)
                            if(numberofDeletedSyncReloadables>0){
                                val isReloadableEmpty =getAllSyncReloadablesByDatatypeFromLocaDatabaseUseCase.invoke("Reloadable")
                                postAllPendingReloadablesToApi()
                            }
                        }
                    }
                }
            }else{
                //Trae la informacion de la API
                getAllReloadablesFromApiUseCase.invoke()
                isSyncing=false
                toastReloadablesSynced=true
                Log.e("TAG","Sync de Restocks finalizada")
                getAllSyncsFromLocalDatabase()
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

    /*
    fun getAllAppDataFromApi(){
        CoroutineScope(Dispatchers.IO).launch {
            //Actualiza whole data en la base de datos local
            addOneHardcodedAuthenticableToLocalDb()
        }
    }
    */
}