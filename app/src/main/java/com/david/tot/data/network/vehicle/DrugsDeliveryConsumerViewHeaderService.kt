package com.david.tot.data.network.vehicle


import com.david.tot.domain.model.Article
import com.david.tot.domain.model.DrugsDeliveryConsumerViewHeader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DrugsDeliveryConsumerViewHeaderService @Inject constructor(private val api: IDrugsDeliveryConsumerViewHeaderApiClient) {
    suspend fun getAllDrugsDeliveryConsumerViewHeader(): List<DrugsDeliveryConsumerViewHeader> {
        //api.rawJSON()
        return withContext(Dispatchers.IO) {
            val response = api.getAllVehicles()
            response.body() ?: emptyList()
        }
    }
}

