package com.david.tot.domain

import com.david.tot.data.ProductRepository
import javax.inject.Inject

class DeleteProductUseCase @Inject constructor(private val repository: ProductRepository) {

    suspend operator fun invoke(idProduct:Int):Int {
        return repository.deleteProduct(idProduct)
    }
}
