package com.david.tot.data.network.confirmable

import com.david.tot.domain.model.Confirmable
import com.david.tot.domain.model.Reloadable
import com.google.gson.JsonArray
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import javax.inject.Inject

class ConfirmableService @Inject constructor(private val api: IConfirmableApiClient) {
    suspend fun getConfirmables(): List<Confirmable> {
        //api.rawJSON()
        return withContext(Dispatchers.IO) {
            val response = api.getAll()
            response.body() ?: emptyList()
        }
    }

    suspend fun postManyConfirmables(jsonArray: JsonArray):Int{
        return withContext(Dispatchers.IO) {
            val mediaType = "application/json".toMediaType()
            val body = jsonArray.toString().toRequestBody(mediaType)
            val respuesta = api.postMany(body)
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

    suspend fun addConfirmable(product: Confirmable):Int{
        return withContext(Dispatchers.IO) {
            val respuesta = api.addOne(product)
            val respuestaBody =respuesta.body().toString()
            val respuestaBody2 = respuesta.body()
            val respuestaBody3 =respuesta.errorBody()
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

    suspend fun updateConfirmable(reloadable:Confirmable):Int{
        return withContext(Dispatchers.IO) {
            val response = api.updateOne(reloadable.localId.toString(),reloadable)
            val res = response
            response.code()
        }
    }

    suspend fun deleteConfirmable(id:Int):Int{
        return withContext(Dispatchers.IO) {
            val response = api.deleteOne(id)
            val res = response
            response.code()
        }
    }

    suspend fun uploadPicture(params: HashMap<String,RequestBody>,part:MultipartBody.Part):Int{
        return withContext(Dispatchers.IO) {
            val response = api.uploadPicture(part,params)
            val res = response
            response.code()
        }
    }

}

