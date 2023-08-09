package com.david.tot.domain

import com.david.tot.data.ArticleRepository
import javax.inject.Inject

class UpdateConsumedQuantityUseCase @Inject constructor(private val repository: ArticleRepository) {

    suspend operator fun invoke(idArticle:Int,quantityToRestore:Int) {
        val aaa= repository.updateConsumedQuantity(idArticle,quantityToRestore)
    }
}
