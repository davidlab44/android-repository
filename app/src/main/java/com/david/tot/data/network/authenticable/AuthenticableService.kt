package com.david.tot.data.network.authenticable


import android.util.Log
import com.david.tot.domain.model.ConsumibleHeader
import com.david.tot.domain.model.Authenticable
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import javax.inject.Inject

class AuthenticableService @Inject constructor(private val api: IAuthenticableApiClient) {
    suspend fun getAllAuthenticableFromApi(): List<Authenticable> {
        //api.rawJSON()
        return withContext(Dispatchers.IO) {
            val response = api.getAllVehicles()
            response.body() ?: emptyList()
        }
    }

    suspend fun postOne(jsonObject: String):Int{
        return withContext(Dispatchers.IO) {
            var gson = Gson()
            var yourModel = gson.fromJson(jsonObject, ConsumibleHeader::class.java)
            yourModel.consumptionId=0
            yourModel.creationDate = "2019-08-10T19:18:30.384Z"
            var headerConsumible = gson.toJson(yourModel)

            val mediaType = "application/json".toMediaType()
            val body = headerConsumible.toString().toRequestBody(mediaType)
            val respuesta = api.sendJson(body)
            val respuestaBody =respuesta.body().toString()
            Log.e("TAG1",""+respuestaBody)
            val respuestaStringed = respuesta.body()!!.string()
            Log.e("TAG2",""+respuestaStringed)
            val consumibleHeader:ConsumibleHeader = gson.fromJson(respuestaStringed, ConsumibleHeader::class.java)
            Log.e("TAG4","consumibleHeader: "+consumibleHeader)
            val consumptionId = consumibleHeader.consumptionId
            Log.e("TAG3",""+consumptionId)
            val respuestaBody2 = respuesta.message()
            consumptionId
        }
    }

}

