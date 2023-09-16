package com.david.tot.data.network.authenticable

import com.david.tot.domain.model.Authenticable
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

//import retrofit2.Retrofit

interface IAuthenticableApiClient {
    
    @GET("api/APP_SP_DrugsDeliveryConsumerViewHeaderResult")
    suspend fun getAllVehicles(@Query("P_ConsumerUser") P_ConsumerUser:String): Response<List<Authenticable>>


    /*
    @POST("example/api/order")
    fun postOrder(@Query("data") jsonArray: JSONArray?): Call<JSONArray?>?
    */

    //@Headers("Content-Type, application/json")
    @POST("api/GlappDrugsDeliveryConsumptions")
    suspend fun sendJson(@Body jsonArray: RequestBody): Response<ResponseBody>

    @POST("api/GLAPP_SP_USR_OBTENERResult")
    suspend fun login(@Query("P_USUARIO") P_USUARIO:String,@Query("P_CLAVE") P_CLAVE :String): Response<ResponseBody>


    @POST("api/GlappDrugsDeliveryRestocks")
    suspend fun sendReloadableHeaderJson(@Body jsonArray: RequestBody): Response<ResponseBody>

    //suspend fun sendJson(@Query("jsonArray") jsonObject: JsonArray): Response<ResponseBody>
    //Response{protocol=http/1.1, code=400, message=Bad Request, url=http://192.168.1.93:53033/api/APP_SP_DrugsDeliveryConsumerViewArticlesResult}
    //suspend fun sendJson(@Body jsonObject:String): Response<ResponseBody>

}

