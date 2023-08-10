package com.david.tot.domain.article

import com.david.tot.data.ArticleRepository
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import javax.inject.Inject

class PostManyArticleUseCase @Inject constructor(private val repository: ArticleRepository) {

    suspend operator fun invoke(jsonObject: JsonArray): Int {
        return repository.postMany(jsonObject)
    }
}
