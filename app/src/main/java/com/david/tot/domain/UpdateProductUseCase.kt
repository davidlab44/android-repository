package com.david.tot.domain

import com.david.tot.data.ProductRepository
import com.david.tot.domain.model.Article

import javax.inject.Inject

class UpdateProductUseCase @Inject constructor(private val repository: ProductRepository) {

    suspend operator fun invoke(product: Article):Int {
        return repository.updateProduct(product)
    }
}
