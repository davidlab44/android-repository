package com.david.tot.domain.drugs_delivery_consumer_view_header

import com.david.tot.data.DrugsDeliveryConsumerViewHeaderRepository
import com.david.tot.domain.model.DrugsDeliveryConsumerViewHeader
import javax.inject.Inject

class GetAnyDrugsDeliveryConsumerViewHeaderUseCase @Inject constructor(private val repository: DrugsDeliveryConsumerViewHeaderRepository) {

    // delete all DrugsDeliveryConsumerViewHeader from database and insert the new ones
    suspend operator fun invoke():DrugsDeliveryConsumerViewHeader{
            return repository.getAnyDrugsDeliveryConsumerViewHeaderFromDatabase()
    }
}


