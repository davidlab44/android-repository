package com.david.tot.data.network.reloadable

import com.david.tot.domain.model.Article
import com.david.tot.domain.model.Reloadable
import com.google.gson.JsonArray
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import javax.inject.Inject

class ReloadableService @Inject constructor(private val api: IReloadableApiClient) {
    suspend fun getReloadables(): List<Reloadable> {
        //api.rawJSON()
        return withContext(Dispatchers.IO) {
            val response = api.getAll()
            response.body() ?: emptyList()
        }
    }

    suspend fun postManyConsumible(jsonArray: JsonArray):Int{
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

    suspend fun addReloadable(product:Article):Int{
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

    suspend fun updateReloadable(article:Article):Int{
        return withContext(Dispatchers.IO) {
            val response = api.updateOne(article.local_id.toString(),article)
            val res = response
            response.code()
        }
    }

    suspend fun deleteReloadable(id:Int):Int{
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

