package com.david.tot.data.network.vehicle

import com.david.tot.domain.model.Article
import com.david.tot.domain.model.DrugsDeliveryConsumerViewHeader
import kotlinx.serialization.json.JsonArray
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*


//import retrofit2.Retrofit

interface IDrugsDeliveryConsumerViewHeaderApiClient {
    
    @GET("api/APP_SP_DrugsDeliveryConsumerViewHeaderResult")
    suspend fun getAllVehicles(): Response<List<DrugsDeliveryConsumerViewHeader>>

    /*
    @POST("example/api/order")
    fun postOrder(@Query("data") jsonArray: JSONArray?): Call<JSONArray?>?
    */

    //@Headers("Content-Type, application/json")
    @POST("api/APP_SP_DrugsDeliveryConsumerViewArticlesResult")
    suspend fun sendJson(@Body jsonArray: RequestBody): Response<ResponseBody>

    //suspend fun sendJson(@Query("jsonArray") jsonObject: JsonArray): Response<ResponseBody>
    //Response{protocol=http/1.1, code=400, message=Bad Request, url=http://192.168.1.93:53033/api/APP_SP_DrugsDeliveryConsumerViewArticlesResult}
    //suspend fun sendJson(@Body jsonObject:String): Response<ResponseBody>



}

