package com.david.tot.data

import com.david.tot.data.database.dao.ReloadableDao
import com.david.tot.data.network.reloadable.ReloadableService
import com.david.tot.domain.model.Article
import com.david.tot.domain.model.Reloadable
import com.david.tot.domain.model.toDomain
import com.david.tot.util.IsImageFile
import com.google.gson.JsonArray
import okhttp3.MultipartBody
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import javax.inject.Inject

class ReloadableRepository @Inject constructor(
    private val api: ReloadableService,
    private val reloadableDao: ReloadableDao
) {

    suspend fun getAllReloadablesFromApi(): List<Reloadable> {
        val response: List<Reloadable> = api.getReloadables()
        return response.map { it.toDomain() }
    }

    suspend fun addReloadable(reloadable:Reloadable):Int{
        return api.addReloadable(reloadable)
    }

    suspend fun postMany(jsonArray: JsonArray):Int{
        return api.postManyConsumible(jsonArray)
    }

    suspend fun updateReloadable(reloadable:Reloadable):Int{
        val responseCode = api.updateReloadable(reloadable)
        return responseCode
    }

    suspend fun deleteProduct(id:Int):Int{
        val responseCode = api.deleteReloadable(id)
        return responseCode
    }

    suspend fun getAllReloadablesFromDatabase():List<Reloadable>{
        val response: List<Reloadable> = reloadableDao.getAll()
        return response.map { it.toDomain() }
    }

    suspend fun getFiltered(hash: String): List<Reloadable> {
        val response: List<Reloadable> = reloadableDao.getFiltered(hash)
        response.map { it.toDomain() }
        return reloadableDao.getFiltered(hash)
    }

    suspend fun insertReloadables(reloadables:List<Reloadable>){
        reloadableDao.insertAll(reloadables)
    }

    suspend fun clearReloadables(){
        reloadableDao.deleteAll()
    }

    suspend fun getReloadableById(local_id:Int): Reloadable {
        return reloadableDao.getById(local_id)
    }

    suspend fun updateImageReloadable(idReloadable:Int,file: File) :Int {
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

    suspend fun updateConsumedQuantity(idReloadable:Int, reloadableNewQuantity:Int): Int {
        return reloadableDao.updateConsumedQuantity(idReloadable,reloadableNewQuantity)
        /*
        return if(updated==1){
            Log.e("TAG","TAGTrue")
        }else{
            Log.e("TAG","TAGFalse")
        }
        */
    }
}
