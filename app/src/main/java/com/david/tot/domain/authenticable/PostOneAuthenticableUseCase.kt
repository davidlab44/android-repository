package com.david.tot.domain.authenticable

import com.david.tot.data.AuthenticableRepository
import javax.inject.Inject

class PostOneAuthenticableUseCase @Inject constructor(private val repository: AuthenticableRepository) {
    suspend operator fun invoke(jsonObject: String): Int {
        return repository.postOneDrugsDeliveryConsumerViewHeader(jsonObject.toString())
    }
}
