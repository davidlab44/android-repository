package com.david.tot.data.network.consumible

import com.david.tot.domain.model.Article
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*
//import retrofit2.Retrofit

interface IConsumibleApiClient {

    @GET("api/APP_SP_DrugsDeliveryConsumerViewArticlesResult")
    suspend fun getAll(@Query("P_ConsumerUser") P_ConsumerUser:String): Response<List<Article>>

    @POST("products")
    suspend fun addOne(@Body product: Article): Response<ResponseBody>


    @POST("api/APP_SP_DrugsDeliveryConsumerViewSaveResult")
    suspend fun postManyConsumibes(@Body jsonArray: RequestBody,@Query("P_ConsumerUser") P_ConsumerUser:String,@Query("P_Vehicle")P_Vehicle:String): Response<ResponseBody>

    /*
    @POST("api/GlappDrugsDeliveryConsumptionDets")
    suspend fun postManyConsumibes(@Body jsonArray: RequestBody): Response<ResponseBody>
    */

    @PUT("products/{id}")
    suspend fun updateOne(@Path("id") id:String, @Body product:Article): Response<ResponseBody>

    @DELETE("products/{id}")
    suspend fun deleteOne(@Path("id") id:Int): Response<ResponseBody>

    @Multipart
    @POST("api/FileManager")
    @JvmSuppressWildcards
    suspend fun uploadPicture(@Part part: MultipartBody.Part,@PartMap params: Map<String,RequestBody>): Response<ResponseBody>

}

