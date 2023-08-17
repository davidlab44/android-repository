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
import com.david.tot.domain.authenticable.RetrieveAllAuthenticablesFromLocalDbUseCase
import com.david.tot.domain.consumible.GetAllFromApiUseCase
import com.david.tot.domain.model.Authenticable
import com.david.tot.domain.model.Consumible
import com.david.tot.domain.model.ConsumibleHeader
import com.david.tot.domain.model.Sync
import com.david.tot.domain.model.SyncConsumible
import com.david.tot.domain.sync.GetAllSyncFromLocalDatabaseUseCase
import com.david.tot.domain.sync.RemoveOneSyncFromLocalDatabaseUseCase
import com.david.tot.domain.sync.consumible.RemoveManySyncConsumiblesFromLocalDatabaseUseCase
import com.david.tot.domain.sync.consumible.GetAllSyncConsumibleFromLocalDatabaseUseCase
import com.david.tot.ui.consumible.ConsumibleViewModel
import com.google.gson.Gson
import com.google.gson.JsonArray
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SyncViewModel @Inject constructor(
    private val postOneAuthenticableUseCase: PostOneAuthenticableUseCase,
    private val postManyArticleUseCase: PostManyArticleUseCase,
    private val getAllSyncFromLocalDatabaseUseCase: GetAllSyncFromLocalDatabaseUseCase,
    private val getAllSyncConsumibleFromLocalDatabaseUseCase: GetAllSyncConsumibleFromLocalDatabaseUseCase,
    private val removeManySyncConsumiblesFromLocalDatabaseUseCase: RemoveManySyncConsumiblesFromLocalDatabaseUseCase,
    private val removeOneSyncFromLocalDatabaseUseCase: RemoveOneSyncFromLocalDatabaseUseCase,
    private val getAnyAuthenticableUseCase:GetAnyAuthenticableUseCase,
    private val getAllFromApiUseCase: GetAllFromApiUseCase,
    private val addOneAuthenticableToLocalDbUseCase: AddOneAuthenticableToLocalDbUseCase,
    private val retrieveAllAuthenticablesFromLocalDbUseCase: RetrieveAllAuthenticablesFromLocalDbUseCase
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
            isSyncing=true
            //No confundir, este es header de la UI pero el tipo de dato ConsumibleHeader es el header del manifiesto
            val consumibleHeader=getAnyAuthenticableUseCase.invoke()
            var headerCons = ConsumibleHeader(0,consumibleHeader.consumer,consumibleHeader.vehicle,"PENDING","2023-08-10T01:42:45.655Z",0)
            var gson = Gson()
            var headerConsumible = gson.toJson(headerCons)
            val consumibleHeaderId =postOneAuthenticableUseCase.invoke(headerConsumible)
            var syncConsumibleList by mutableStateOf<List<SyncConsumible>>(emptyList())
            syncConsumibleList = getAllSyncConsumibleFromLocalDatabaseUseCase.invoke()
            var consumibleList by mutableStateOf<List<Consumible>>(emptyList())
            var consumibleMutableList = consumibleList.toMutableList()
            var syncConsumibleToDeleteId=0
            if (!syncConsumibleList.isNullOrEmpty()&&consumibleHeaderId.toInt()>0) {
                syncConsumibleList.forEach { syncConsumible->
                    val consumible = Consumible(0,consumibleHeaderId,syncConsumible.articleCode,syncConsumible.quantity,"UND","2023-08-10T01:42:45.655Z",0)
                    consumibleMutableList.add(consumible)
                    syncConsumibleToDeleteId=syncConsumible.objectId
                }
                val jsonArray: JsonArray = Gson().toJsonTree(consumibleMutableList).asJsonArray
                val postManyArticleUseCase = postManyArticleUseCase.invoke(jsonArray)
                if(postManyArticleUseCase in 200..300){
                    val consumibleDeleted = removeManySyncConsumiblesFromLocalDatabaseUseCase.invoke(syncConsumibleToDeleteId)
                    val syncToRemove = removeOneSyncFromLocalDatabaseUseCase(syncConsumibleToDeleteId)
                    if(getAllSyncConsumibleFromLocalDatabaseUseCase.invoke().isNotEmpty()){
                        postManyConsumibleToApi()
                    }else{
                        toastConsumiblesSynced=true
                        isSyncing=false
                        //actualiza whole data en la base de datos local
                        getAllFromApiUseCase.invoke()
                        addOneHardcodedAuthenticableToLocalDb()
                    }
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
}