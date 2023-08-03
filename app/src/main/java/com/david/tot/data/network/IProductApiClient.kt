package com.david.tot.data.network

import com.david.tot.domain.model.Product
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*
//import retrofit2.Retrofit

interface IProductApiClient {
    @GET("products")
    suspend fun getAllRecipes(): Response<List<Product>>

    @POST("products")
    suspend fun addProduct(@Body product: Product): Response<ResponseBody>

    @PUT("products/{id}")
    suspend fun updateProduct(@Path("id") id:String, @Body product:Product): Response<ResponseBody>

    @DELETE("products/{id}")
    suspend fun deleteProduct(@Path("id") id:Int): Response<ResponseBody>

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

