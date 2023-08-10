package com.david.tot.data.network.vehicle


import com.david.tot.domain.model.ConsumibleHeader
import com.david.tot.domain.model.DrugsDeliveryConsumerViewHeader
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import java.text.SimpleDateFormat
import java.util.Date
import javax.inject.Inject

class DrugsDeliveryConsumerViewHeaderService @Inject constructor(private val api: IDrugsDeliveryConsumerViewHeaderApiClient) {
    suspend fun getAllDrugsDeliveryConsumerViewHeader(): List<DrugsDeliveryConsumerViewHeader> {
        //api.rawJSON()
        return withContext(Dispatchers.IO) {
            val response = api.getAllVehicles()
            response.body() ?: emptyList()
        }
    }

    suspend fun postOne(jsonObject: String):Int{
        return withContext(Dispatchers.IO) {

            val sdf = SimpleDateFormat("hh:mm:ss")
            val currentDate = sdf.format(Date())
            val date = currentDate.filter {it in '0'..'9'}


            var gson = Gson()
            var yourModel = gson.fromJson(jsonObject, ConsumibleHeader::class.java)
            yourModel.consumptionId=0
            yourModel.creationDate = "2019-08-10T19:18:30.384Z"
            var headerConsumible = gson.toJson(yourModel)

            val mediaType = "application/json".toMediaType()
            val body = headerConsumible.toString().toRequestBody(mediaType)
            val respuesta = api.sendJson(body)
            val respuestaBody =respuesta.body().toString()
            val respuestaBody2 = respuesta.message()
            val respuestaBody3 =respuesta
            val respuestaBody4 =respuesta.isSuccessful
            val respuestaBody5 =respuesta.code()
            val respuestaRaw =respuesta.raw()
            val respuestaBody7 =respuesta.headers()
            val respuestaBody8 =respuestaBody2
            val respuestaBody11 =respuestaBody3
            val respuestaBod12 =respuestaRaw
            val respuestaBody13=respuestaBody4
            val respuestaBody14 =respuestaBody7
            val respuestaBod15 =respuestaBody5
            val respuestaBody9 =respuestaBody
            //if (response.isSuccessful) { }
            respuesta.code()
        }
    }

}

