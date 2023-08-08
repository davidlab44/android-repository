package com.david.tot.domain.article

import com.david.tot.data.ArticleRepository
import javax.inject.Inject

class UpdateConsumibleQuantityUseCase @Inject constructor(private val repository: ArticleRepository) {

    suspend operator fun invoke(idArticle:Int,consumibleNewQuantity:Int):Int {
        return repository.updateConsumedQuantity(idArticle,consumibleNewQuantity)
    }
}
