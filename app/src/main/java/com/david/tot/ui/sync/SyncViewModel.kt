package com.david.tot.ui.sync

import android.annotation.SuppressLint
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
import com.david.tot.domain.sync.sync_consumible.GetAllConsumibleFromLocalDatabaseUseCase
import com.google.gson.Gson
import com.google.gson.JsonArray
//import com.yeslab.fastprefs.FastPrefs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SyncViewModel @Inject constructor(
    private val getAllConsumibleFromLocalDatabaseUseCase: GetAllConsumibleFromLocalDatabaseUseCase,
    private val postOneDrugsDeliveryConsumerViewHeaderUseCase: PostOneDrugsDeliveryConsumerViewHeaderUseCase,
    private val getAnyDrugsDeliveryConsumerViewHeaderUseCase: GetAnyDrugsDeliveryConsumerViewHeaderUseCase,
    private val postManyArticleUseCase: PostManyArticleUseCase
) : ViewModel() {


    var syncList by mutableStateOf<List<Sync>>(emptyList())
    var quantityToRestore by mutableStateOf<String>("")
    var toastTheresNotConsumiblesToSync by mutableStateOf<Boolean>(false)
    var toastConsumiblesSynced by mutableStateOf<Boolean>(false)
    var toastInsertedSuccessfully by mutableStateOf<Boolean>(false)
    var key by mutableStateOf<Int>(0)

    fun getAllSyncsFromLocalDatabase() {
        //viewModelScope.launch {
        CoroutineScope(Dispatchers.IO).launch {
            //val id = Calendar.getInstance().time
            //val syncList = getAllSyncsFromLocalDatabaseUseCase.invoke()
        }
    }


    fun postManyConsumibleToApi() {
        //viewModelScope.launch {
        CoroutineScope(Dispatchers.IO).launch {
            var consumibleList by mutableStateOf<List<Consumible>>(emptyList())
            consumibleList = getAllConsumibleFromLocalDatabaseUseCase.invoke()
            if (!consumibleList.isNullOrEmpty()) {
                val jsonArray: JsonArray = Gson().toJsonTree(consumibleList).asJsonArray
                val postManyArticleUseCase = postManyArticleUseCase.invoke(jsonArray)
                if(postManyArticleUseCase in 200..300)
                    toastConsumiblesSynced=true
            }
        }
    }



    @SuppressLint("SuspiciousIndentation")
    fun syncConsumible(){
        CoroutineScope(Dispatchers.IO).launch {
            val consumibleList = getAllConsumibleFromLocalDatabaseUseCase.invoke()
           //TODO TODO OJO para syncronizar varios elementos hay qque modificar este ciclo asi como esta unicamante manda uno
            consumibleList.forEach {
                key = it.consumptionId
            }
            if(consumibleList.isNotEmpty()){
                //1.obtener any headerConsumible de la base de datos
                var consumible=getAnyDrugsDeliveryConsumerViewHeaderUseCase.invoke()
                //extraer el id de un elemento de la lista


                //TODO ya en el servicion no tienes que tocar este Json
                var headerCons = ConsumibleHeader(0,consumible.consumer,consumible.vehicle,"Example2","2023-08-10T01:42:45.655Z",0)
                var gson = Gson()
                var headerConsumible = gson.toJson(headerCons)
                //var headerConsumibleToJson = Json.encodeToString(headerConsumible)
                //val JsonObject = Json.decodeFromString<ConsumibleHeader>(headerConsumibleToJson)
                //val tt=  JsonObject.toString()
                Log.e("TAG",""+headerConsumible)

                val responseCode =postOneDrugsDeliveryConsumerViewHeaderUseCase.invoke(headerConsumible)
                //TODO virtualassembler 2
                /*
                if(responseCode in 200..300){
                    var dataList = mutableListOf<Consumible>()
                    //val prefs = FastPrefs(mContext)
                    //Cambiar el nombre de la preferencia  que esta quemado aqui
                    //un detalle importante la misma data list que le paso por defecto a la sp pa que no chille es la misma donde almaceno el contenido de la sp
                    //dataList = prefs.get("david",dataList)!!

                    var consumibleList:List<Consumible> = prefs.get("david",dataList)!!

                    Log.e("consumibleList",""+consumibleList)

                    val jsonArray1: JsonArray = Gson().toJsonTree(consumibleList).asJsonArray
                    //var list=ArrayList<Consumible>()
                    //val listresult: Array<Consumible> = Gson().fromJson(consumibleList,Array<Consumible>:: class.java)
                    //list.addAll(listresult)
                    Log.e("TAG","TAG")
                    Log.e("TAG","TAG"+jsonArray1)
                    //val gsonHandler = Gson()
                    //val weatherList: List<Consumible> = gsonHandler.fromJson(consumibleList.toString() , Array<Consumible>::class.java).toList()
                    //Log.e("",""+weatherList)

                    //val element: JsonElement = gsonHandler.toJsonTree(consumibleList, object : TypeToken<List<Consumible>>() {}.type)
                    //val element = gsonHandler.toJsonTree(consumibleList, object : TypeToken<List<Consumible>>() {}.type)

                    /*
                    var gson = Gson()
                    var manyConsumibleHeader = gson.fromJson(dataList.toString(), ManyConsumibleHeader::class.java)
                    var consumibleList = gson.toJson(manyConsumibleHeader)
                    */

                    //var consumibleToJsonArray = Json.encodeToString(dataList)
                    //val jsonArray = Json.decodeFromString<JsonArray>(consumibleToJsonArray)

                    val responseCodeConsumible=postManyArticleUseCase.invoke(jsonArray1)
                    if(responseCodeConsumible in 200..300)
                        toastInsertedSuccessfully=true
                }
                */
            }else{
                toastTheresNotConsumiblesToSync=true
            }
        }
    }







    /*
    @SuppressLint("SuspiciousIndentation")
    fun syncConsumible(mContext: Context){
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


                //TODO ya en el servicion no tienes que tocar este Json
                var headerCons = ConsumibleHeader(0,consumible.consumer,consumible.vehicle,"Example2","2023-08-10T01:42:45.655Z",0)
                var gson = Gson()
                var headerConsumible = gson.toJson(headerCons)
                //var headerConsumibleToJson = Json.encodeToString(headerConsumible)
                //val JsonObject = Json.decodeFromString<ConsumibleHeader>(headerConsumibleToJson)
                //val tt=  JsonObject.toString()
                Log.e("TAG",""+headerConsumible)

                val responseCode =postOneDrugsDeliveryConsumerViewHeaderUseCase.invoke(headerConsumible)
                //TODO virtualassembler 2
                /*
                if(responseCode in 200..300){
                    var dataList = mutableListOf<Consumible>()
                    //val prefs = FastPrefs(mContext)
                    //Cambiar el nombre de la preferencia  que esta quemado aqui
                    //un detalle importante la misma data list que le paso por defecto a la sp pa que no chille es la misma donde almaceno el contenido de la sp
                    //dataList = prefs.get("david",dataList)!!

                    var consumibleList:List<Consumible> = prefs.get("david",dataList)!!

                    Log.e("consumibleList",""+consumibleList)

                    val jsonArray1: JsonArray = Gson().toJsonTree(consumibleList).asJsonArray
                    //var list=ArrayList<Consumible>()
                    //val listresult: Array<Consumible> = Gson().fromJson(consumibleList,Array<Consumible>:: class.java)
                    //list.addAll(listresult)
                    Log.e("TAG","TAG")
                    Log.e("TAG","TAG"+jsonArray1)
                    //val gsonHandler = Gson()
                    //val weatherList: List<Consumible> = gsonHandler.fromJson(consumibleList.toString() , Array<Consumible>::class.java).toList()
                    //Log.e("",""+weatherList)

                     //val element: JsonElement = gsonHandler.toJsonTree(consumibleList, object : TypeToken<List<Consumible>>() {}.type)
                    //val element = gsonHandler.toJsonTree(consumibleList, object : TypeToken<List<Consumible>>() {}.type)


                    /*
                    var gson = Gson()
                    var manyConsumibleHeader = gson.fromJson(dataList.toString(), ManyConsumibleHeader::class.java)
                    var consumibleList = gson.toJson(manyConsumibleHeader)
                    */

                    //var consumibleToJsonArray = Json.encodeToString(dataList)
                    //val jsonArray = Json.decodeFromString<JsonArray>(consumibleToJsonArray)


                    val responseCodeConsumible=postManyArticleUseCase.invoke(jsonArray1)
                    if(responseCodeConsumible in 200..300)
                        toastInsertedSuccessfully=true


                }
                */
            }else{
                toastTheresNotConsumiblesToSync=true
            }
        }
    }
*/

}