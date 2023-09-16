package com.david.tot.domain.consumible

import com.david.tot.data.ConsumibleRepository
import javax.inject.Inject

class UpdateConsumibleQuantityUseCase @Inject constructor(private val repository: ConsumibleRepository) {

    suspend operator fun invoke(idArticle:Int,consumibleNewQuantity:Int):Int {
        return repository.updateConsumedQuantity(idArticle,consumibleNewQuantity)
    }
}
