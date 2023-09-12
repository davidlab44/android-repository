package com.david.tot.data.network.confirmable

import com.david.tot.domain.model.Confirmable
import com.david.tot.domain.model.Consumible
import com.david.tot.domain.model.Reloadable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface IConfirmableApiClient {

    @GET("api/Glapp_SP_DrugsDeliveryRestocksResult")
    suspend fun getAll(): Response<List<Confirmable>>

    @GET("api/Glapp_SP_DrugsDeliveryRestocksArticlesResult")
    suspend fun getAllConfirmableDetailsFromApi(@Query("P_User") P_User:String,@Query("P_RestockID") P_RestockID:Int,@Query("P_View") P_View:String): Response<List<Consumible>>

    @GET("api/Glapp_SP_DrugsDeliveryRestocksArticlesResult")
    suspend fun postOneConfirmable(@Query("P_RestockID") P_RestockID:Int,@Query("P_User") P_User:String,@Query("P_Vehicle") P_Vehicle:String,@Query("P_DeliveryConfirmationImageUrl") P_DeliveryConfirmationImageUrl:String,@Query("P_DeliveryConfirmationComments") P_DeliveryConfirmationComments:String): Response<List<Confirmable>>

    @POST("products")
    suspend fun addOne(@Body product: Confirmable): Response<ResponseBody>

    @POST("api/GlappDrugsDeliveryRestocksDets")
    suspend fun postMany(@Body jsonArray: RequestBody): Response<ResponseBody>




    @PUT("products/{id}")
    suspend fun updateOne(@Path("id") id:String, @Body product:Confirmable): Response<ResponseBody>

    @DELETE("products/{id}")
    suspend fun deleteOne(@Path("id") id:Int): Response<ResponseBody>

    @Multipart
    @POST("api/FileManager")
    @JvmSuppressWildcards
    suspend fun uploadPicture(@Part part: MultipartBody.Part,@PartMap params: Map<String,RequestBody>): Response<ResponseBody>

}

