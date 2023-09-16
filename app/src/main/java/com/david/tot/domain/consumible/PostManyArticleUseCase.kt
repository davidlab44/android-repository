package com.david.tot.domain.consumible

import com.david.tot.data.ConsumibleRepository
import com.google.gson.JsonArray
import javax.inject.Inject

class PostManyArticleUseCase @Inject constructor(private val repository: ConsumibleRepository) {
    suspend operator fun invoke(jsonArray: JsonArray): Int {
        return repository.postMany(jsonArray)
    }
}
