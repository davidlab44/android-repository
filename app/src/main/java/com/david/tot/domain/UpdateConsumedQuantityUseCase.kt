package com.david.tot.domain

import com.david.tot.data.ConsumibleRepository
import javax.inject.Inject

class UpdateConsumedQuantityUseCase @Inject constructor(private val repository: ConsumibleRepository) {

    suspend operator fun invoke(idArticle:Int,quantityToRestore:Int) {
        val aaa= repository.updateConsumedQuantity(idArticle,quantityToRestore)
    }
}
