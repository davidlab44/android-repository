package com.david.tot.data.network.vehicle

import com.david.tot.domain.model.Article
import com.david.tot.domain.model.DrugsDeliveryConsumerViewHeader
import retrofit2.Response
import retrofit2.http.*
//import retrofit2.Retrofit

interface IDrugsDeliveryConsumerViewHeaderApiClient {
    
    @GET("api/APP_SP_DrugsDeliveryConsumerViewHeaderResult")
    suspend fun getAllVehicles(): Response<List<DrugsDeliveryConsumerViewHeader>>

}

