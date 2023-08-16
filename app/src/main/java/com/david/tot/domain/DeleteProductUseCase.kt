package com.david.tot.domain

import com.david.tot.data.ConsumibleRepository
import javax.inject.Inject

class DeleteProductUseCase @Inject constructor(private val repository: ConsumibleRepository) {

    suspend operator fun invoke(idProduct:Int):Int {
        return repository.deleteProduct(idProduct)
    }
}
