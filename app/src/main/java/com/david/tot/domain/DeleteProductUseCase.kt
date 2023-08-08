package com.david.tot.domain

import com.david.tot.data.ArticleRepository
import javax.inject.Inject

class DeleteProductUseCase @Inject constructor(private val repository: ArticleRepository) {

    suspend operator fun invoke(idProduct:Int):Int {
        return repository.deleteProduct(idProduct)
    }
}
