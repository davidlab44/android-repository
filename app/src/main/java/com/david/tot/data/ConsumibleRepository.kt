package com.david.tot.data

import com.david.tot.data.database.dao.ConsumibleDao
import com.david.tot.data.network.consumible.ConsumibleService
import com.david.tot.domain.model.Article
import com.david.tot.domain.model.toDomain
import com.david.tot.util.IsImageFile
import com.google.gson.JsonArray
import okhttp3.MultipartBody
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import javax.inject.Inject

class ConsumibleRepository @Inject constructor(
    private val api: ConsumibleService,
    private val consumibleDao: ConsumibleDao
) {

    suspend fun getAllConsumiblesFromApi(): List<Article> {
        val response: List<Article> = api.getRecipes()
        return response.map { it.toDomain() }
    }

    /*
    suspend fun addProduct(product:Article):Int{
        return api.addProduct(product)
    }
     */

    suspend fun postMany(jsonArray: JsonArray):Int{
        return api.postManyConsumible(jsonArray)
    }

    suspend fun updateProduct(product:Article):Int{
        val responseCode = api.updateProduct(product)
        return responseCode
    }

    suspend fun deleteProduct(id:Int):Int{
        val responseCode = api.deleteProduct(id)
        return responseCode
    }

    suspend fun getAllConsumiblesFromDatabase():List<Article>{
        val response: List<Article> = consumibleDao.getAll()
        return response.map { it.toDomain() }
    }

    suspend fun getFiltered(hash: String): List<Article> {
        val response: List<Article> = consumibleDao.getFiltered(hash)
        response.map { it.toDomain() }
        return consumibleDao.getFiltered(hash)
    }

    suspend fun insertRecipes(recipes:List<Article>){
        consumibleDao.insertAll(recipes)
    }

    suspend fun clearRecipes(){
        consumibleDao.deleteAll()
    }

    suspend fun getArticleById(local_id:Int): Article {
        return consumibleDao.getById(local_id)
    }

    suspend fun updateImageProduct(idProduct:Int,file: File) :Int {
        val isFile = IsImageFile().accept(file)
        val ii = isFile
        val requestFile: RequestBody = RequestBody.create("image/jpg".toMediaType(),file)
        val multipartImage = MultipartBody.Part.createFormData("MyFile", file.getName(), requestFile);
        //val file1 = File("") // just for example I'm initializing File with "" path
        val params = HashMap<String, RequestBody>()
        params["AltText"] = "AltText".toRequestBody()
        params["Description"] = "Description".toRequestBody()
        /*
        params["place_name"] = "".toRequestBody()
        params["village"] = "".toRequestBody()
        params["hb_taluk"] = "".toRequestBody()
        params["properties_land"] = "".toRequestBody()
        params["other_spec"] = "".toRequestBody()
        params["land_type"] = "".toRequestBody()
        params["leagal_issues"] = "".toRequestBody()
        params["contact_num"] = "".toRequestBody()
        params["address"] = "".toRequestBody()
        params["price"] = "".toRequestBody()
        */
        //val filePart = MultipartBody.Part.createFormData("img1", file1.name, reqFile1)
        val responseCode = api.uploadPicture(params, multipartImage)
        //val responseCode = api.uploadPicture(idProduct,multipartImage)
        val responseCode2 = responseCode
        return responseCode
    }


    suspend fun updateConsumedQuantity(idArticle:Int, consumibleNewQuantity:Int): Int {
        return consumibleDao.updateConsumedQuantity(idArticle,consumibleNewQuantity)
        /*
        return if(updated==1){
            Log.e("TAG","TAGTrue")
        }else{
            Log.e("TAG","TAGFalse")
        }
        */
    }

}
