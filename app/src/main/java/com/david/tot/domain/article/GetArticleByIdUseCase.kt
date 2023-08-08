package com.david.tot.domain.article

import com.david.tot.data.ArticleRepository
import com.david.tot.domain.model.Article
import javax.inject.Inject

class GetArticleByIdUseCase @Inject constructor(private val repository: ArticleRepository) {
    suspend operator fun invoke(local_id:Int):Article{
        return repository.getArticleById(local_id)
    }
}
