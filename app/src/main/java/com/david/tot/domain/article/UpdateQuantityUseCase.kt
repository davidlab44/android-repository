package com.david.tot.domain.article

import com.david.tot.data.ProductRepository
import com.david.tot.domain.model.Article
import javax.inject.Inject

class UpdateQuantityUseCase @Inject constructor(private val repository: ProductRepository) {

    suspend operator fun invoke(idArticle:Int,quantityToRestore:Int) {
        return repository.updateQuantity(idArticle,quantityToRestore)
    }
}
