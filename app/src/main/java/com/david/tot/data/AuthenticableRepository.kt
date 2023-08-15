package com.david.tot.data

import com.david.tot.data.database.dao.AuthenticableDao
import com.david.tot.data.network.vehicle.DrugsDeliveryConsumerViewHeaderService
import com.david.tot.domain.model.Authenticable
import com.david.tot.domain.model.toDomain
import javax.inject.Inject

class AuthenticableRepository @Inject constructor(
    private val api: DrugsDeliveryConsumerViewHeaderService,
    private val authenticableDao: AuthenticableDao
) {

    suspend fun getAllDrugsDeliveryConsumerViewHeaderFromApi(): List<Authenticable> {
        val response: List<Authenticable> = api.getAllDrugsDeliveryConsumerViewHeader()
        return response.map { it.toDomain() }
    }

    suspend fun getAllDrugsDeliveryConsumerViewHeaderFromDatabase():List<Authenticable>{
        val response: List<Authenticable> = authenticableDao.getAll()
        return response.map { it.toDomain() }
    }

    suspend fun getAnyDrugsDeliveryConsumerViewHeaderFromDatabase():Authenticable{
        return authenticableDao.getAny()
        //return response.map { it.toDomain() }
    }

    suspend fun postOneDrugsDeliveryConsumerViewHeader(jsonObject: String):Int{
        return api.postOne(jsonObject)
    }

    suspend fun addAllAuthenticablesToLocalDb(authenticableList:List<Authenticable>){
        authenticableDao.addAllAuthenticablesToLocalDb(authenticableList)
    }

    suspend fun addOneAuthenticableToLocalDb(authenticable:Authenticable){
        authenticableDao.addOneAuthenticableToLocalDb2(authenticable)
    }

    suspend fun clearDrugsDeliveryConsumerViewHeader(){
        authenticableDao.deleteAll()
    }

}
