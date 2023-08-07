package com.david.tot.domain.drugs_delivery_consumer_view_header

import com.david.tot.data.DrugsDeliveryConsumerViewHeaderRepository
import kotlinx.serialization.json.JsonArray
import javax.inject.Inject

class SaveInventoryOutputInremoteServerUseCase @Inject constructor(private val repository: DrugsDeliveryConsumerViewHeaderRepository) {
    suspend operator fun invoke(jsonObject: JsonArray): Int {
        return repository.saveInventoryOutputInremoteServer(jsonObject)
    }
}
