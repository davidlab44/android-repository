package com.david.tot.data.network.reloadable

import com.david.tot.domain.model.Article
import com.david.tot.domain.model.Reloadable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*
//import retrofit2.Retrofit

interface IReloadableApiClient {
    /*@GET("products")
    suspend fun getAllRecipes(): Response<List<Article>>
    //api/Article
    */

    /*
    @GET("api/APP_SP_DrugsDeliveryConsumerViewArticlesResult")
    suspend fun getAll(): Response<List<Reloadable>>
    */

    @GET("api/Glapp_SP_DrugsDeliveryRestocksResult")
    suspend fun getAll(@Query("user") user:String,@Query("id") id:Int,@Query("type") type:String): Response<List<Reloadable>>

    @POST("products")
    suspend fun addOne(@Body product: Reloadable): Response<ResponseBody>

    @POST("api/Glapp_SP_DrugsDeliveryRestockSaveResult")
    suspend fun postMany(@Body jsonArray: RequestBody,@Query("P_RestockId") P_RestockId:Int,@Query("P_ConsumerUser") P_ConsumerUser:String,@Query("P_Vehicle")P_Vehicle:String): Response<ResponseBody>


    /*
    @POST("api/GlappDrugsDeliveryRestocksDets")
    suspend fun postMany(@Body jsonArray: RequestBody): Response<ResponseBody>
    */
    @PUT("products/{id}")
    suspend fun updateOne(@Path("id") id:String, @Body product:Reloadable): Response<ResponseBody>

    @DELETE("products/{id}")
    suspend fun deleteOne(@Path("id") id:Int): Response<ResponseBody>

    @Multipart
    //@POST("pictures")
    @POST("api/FileManager")
    @JvmSuppressWildcards
    //suspend fun uploadPicture(@Part part: MultipartBody.part): Response<ResponseBody>
    suspend fun uploadPicture(@Part part: MultipartBody.Part,@PartMap params: Map<String,RequestBody>): Response<ResponseBody>

    //suspend fun uploadPicture(@Part part: MultipartBody.Part,@Field("AltText") AltText:String?,@Field("Description") Description:String?): Response<ResponseBody>
    //suspend fun uploadPicture(@Part part: MultipartBody.Part,@Part("AltText") AltText:String,@Part("Description") Description:String): Response<ResponseBody>
    //suspend fun uploadPicture(@Part part: MultipartBody.Part): Response<ResponseBody>
}

