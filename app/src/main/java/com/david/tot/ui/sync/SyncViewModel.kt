package com.david.tot.ui.sync

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.david.tot.domain.article.PostManyArticleUseCase
import com.david.tot.domain.drugs_delivery_consumer_view_header.GetAnyDrugsDeliveryConsumerViewHeaderUseCase
import com.david.tot.domain.drugs_delivery_consumer_view_header.PostOneDrugsDeliveryConsumerViewHeaderUseCase
import com.david.tot.domain.model.Consumible
import com.david.tot.domain.model.ConsumibleHeader
import com.david.tot.domain.model.Sync
import com.david.tot.domain.sync.GetAllSyncFromLocalDatabaseUseCase
import com.yeslab.fastprefs.FastPrefs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import java.text.SimpleDateFormat
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class SyncViewModel @Inject constructor(
    private val getAllSyncFromLocalDatabaseUseCase: GetAllSyncFromLocalDatabaseUseCase,
    private val postOneDrugsDeliveryConsumerViewHeaderUseCase: PostOneDrugsDeliveryConsumerViewHeaderUseCase,
    private val getAnyDrugsDeliveryConsumerViewHeaderUseCase: GetAnyDrugsDeliveryConsumerViewHeaderUseCase,
    private val postManyArticleUseCase: PostManyArticleUseCase
) : ViewModel() {

    var articleList by mutableStateOf<List<Sync>>(emptyList())
    var quantityToRestore by mutableStateOf<String>("")
    var toastTheresNotConsumiblesToSync by mutableStateOf<Boolean>(false)
    var toastInsertedSuccessfully by mutableStateOf<Boolean>(false)
    var key by mutableStateOf<Int>(0)

    fun onCreate() {
        //viewModelScope.launch {
        CoroutineScope(Dispatchers.IO).launch {
            //val id = Calendar.getInstance().time
            val result = getAllSyncFromLocalDatabaseUseCase.invoke()
            if (!result.isNullOrEmpty()) {
                articleList =result
            }
        }
    }

    fun syncConsumible(mContext:Context){
        //Crear el header
        CoroutineScope(Dispatchers.IO).launch {
            //-1.val id = Calendar.getInstance().time
            //0.validar si hay consumibles pendientes por enviar
            val consumibleList = getAllSyncFromLocalDatabaseUseCase.invoke()
           //TODO TODO OJO para syncronizar varios elementos hay qque modificar este ciclo asi como esta unicamante manda uno
            consumibleList.forEach {
                key = it.consumptionId
            }
            if(consumibleList.isNotEmpty()){
                //1.obtener any headerConsumible de la base de datos
                var consumible=getAnyDrugsDeliveryConsumerViewHeaderUseCase.invoke()
                //extraer el id de un elemento de la lista
                var dataList = mutableListOf<Consumible>()
                val prefs = FastPrefs(mContext)
                prefs.get(""+key,dataList)
                var headerConsumible = ConsumibleHeader(key.toInt(),consumible.consumer,consumible.vehicle,"Example2","2023-08-10T01:42:45.655Z",0)
                var headerConsumibleToJson = Json.encodeToString(headerConsumible)
                val JsonObject = Json.decodeFromString<JsonObject>(headerConsumibleToJson)
                val responseCode =postOneDrugsDeliveryConsumerViewHeaderUseCase.invoke(JsonObject)
                if(responseCode>=200 || responseCode<=300){
                    var consumibleToJsonArray = Json.encodeToString(dataList)
                    val jsonArray = Json.decodeFromString<JsonArray>(consumibleToJsonArray)
                    val responseCodeConsumible=postManyArticleUseCase.invoke(jsonArray)
                    if(responseCodeConsumible>=200 || responseCodeConsumible<=300)
                        toastInsertedSuccessfully=true
                }
            }else{
                toastTheresNotConsumiblesToSync=true
            }
        }
    }
}