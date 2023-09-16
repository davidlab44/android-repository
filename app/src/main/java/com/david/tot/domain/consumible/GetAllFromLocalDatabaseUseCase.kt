package com.david.tot.domain.consumible

import com.david.tot.data.ConsumibleRepository
import com.david.tot.domain.model.Article
import javax.inject.Inject

class GetAllFromLocalDatabaseUseCase @Inject constructor(private val repository: ConsumibleRepository) {
    suspend operator fun invoke():List<Article>{
        return repository.getAllConsumiblesFromDatabase()
    }
}
