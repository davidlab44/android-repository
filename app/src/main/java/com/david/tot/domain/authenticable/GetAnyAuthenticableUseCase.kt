package com.david.tot.domain.authenticable

import com.david.tot.data.AuthenticableRepository
import com.david.tot.domain.model.Authenticable
import javax.inject.Inject

class GetAnyAuthenticableUseCase @Inject constructor(private val repository: AuthenticableRepository) {

    // delete all DrugsDeliveryConsumerViewHeader from database and insert the new ones
    suspend operator fun invoke():Authenticable{
            return repository.getAnyDrugsDeliveryConsumerViewHeaderFromDatabase()
    }
}


