package com.david.tot.domain.consumible

import com.david.tot.data.ConsumibleRepository
import com.david.tot.domain.model.Article
import javax.inject.Inject

class GetArticleByIdUseCase @Inject constructor(private val repository: ConsumibleRepository) {
    suspend operator fun invoke(local_id:Int):Article{
        return repository.getArticleById(local_id)
    }
}
