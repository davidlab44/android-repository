package com.david.tot.domain

import com.david.tot.data.ArticleRepository
import com.david.tot.domain.model.Article
import javax.inject.Inject

class AddProductUseCase @Inject constructor(private val repository: ArticleRepository) {

    suspend operator fun invoke(product: Article):Int {
        return repository.addProduct(product)
    }

}
