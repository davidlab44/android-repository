package com.david.tot.domain.article

import com.david.tot.data.ArticleRepository
import com.google.gson.JsonArray
import javax.inject.Inject

class PostManyArticleUseCase @Inject constructor(private val repository: ArticleRepository) {
    suspend operator fun invoke(jsonArray: JsonArray): Int {
        return repository.postMany(jsonArray)
    }
}
