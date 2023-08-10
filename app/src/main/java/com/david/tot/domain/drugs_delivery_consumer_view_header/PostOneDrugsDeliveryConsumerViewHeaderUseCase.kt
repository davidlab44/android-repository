package com.david.tot.domain.drugs_delivery_consumer_view_header

import com.david.tot.data.DrugsDeliveryConsumerViewHeaderRepository
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import javax.inject.Inject

class PostOneDrugsDeliveryConsumerViewHeaderUseCase @Inject constructor(private val repository: DrugsDeliveryConsumerViewHeaderRepository) {
    suspend operator fun invoke(jsonObject: JsonObject): Int {
        return repository.postOneDrugsDeliveryConsumerViewHeader(jsonObject)
    }
}
