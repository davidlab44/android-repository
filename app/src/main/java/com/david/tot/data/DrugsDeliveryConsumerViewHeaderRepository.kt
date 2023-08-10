package com.david.tot.data

import com.david.tot.data.database.dao.DrugsDeliveryConsumerViewHeaderDao
import com.david.tot.data.network.vehicle.DrugsDeliveryConsumerViewHeaderService
import com.david.tot.domain.model.DrugsDeliveryConsumerViewHeader
import com.david.tot.domain.model.toDomain
import kotlinx.serialization.json.JsonObject
import javax.inject.Inject

class DrugsDeliveryConsumerViewHeaderRepository @Inject constructor(
    private val api: DrugsDeliveryConsumerViewHeaderService,
    private val drugsDeliveryConsumerViewHeaderDao: DrugsDeliveryConsumerViewHeaderDao
) {

    suspend fun getAllDrugsDeliveryConsumerViewHeaderFromApi(): List<DrugsDeliveryConsumerViewHeader> {
        val response: List<DrugsDeliveryConsumerViewHeader> = api.getAllDrugsDeliveryConsumerViewHeader()
        return response.map { it.toDomain() }
    }

    suspend fun getAllDrugsDeliveryConsumerViewHeaderFromDatabase():List<DrugsDeliveryConsumerViewHeader>{
        val response: List<DrugsDeliveryConsumerViewHeader> = drugsDeliveryConsumerViewHeaderDao.getAll()
        return response.map { it.toDomain() }
    }

    suspend fun getAnyDrugsDeliveryConsumerViewHeaderFromDatabase():DrugsDeliveryConsumerViewHeader{
        return drugsDeliveryConsumerViewHeaderDao.getAny()
        //return response.map { it.toDomain() }
    }

    suspend fun postOneDrugsDeliveryConsumerViewHeader(jsonObject: JsonObject):Int{
        return api.postOne(jsonObject)
    }

    suspend fun insertDrugsDeliveryConsumerViewHeader(items:List<DrugsDeliveryConsumerViewHeader>){
        drugsDeliveryConsumerViewHeaderDao.insertAll(items)
    }

    suspend fun clearDrugsDeliveryConsumerViewHeader(){
        drugsDeliveryConsumerViewHeaderDao.deleteAll()
    }



}
