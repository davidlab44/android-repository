package com.david.tot.domain

import com.david.tot.data.ConsumibleRepository
import com.david.tot.domain.model.Article

import javax.inject.Inject

class UpdateProductUseCase @Inject constructor(private val repository: ConsumibleRepository) {

    suspend operator fun invoke(product: Article):Int {
        return repository.updateProduct(product)
    }
}
