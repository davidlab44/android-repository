package com.david.tot.domain.article

import com.david.tot.data.ArticleRepository
import com.david.tot.domain.model.Article
import com.david.tot.domain.model.toDatabase
import javax.inject.Inject

class GetAllFromLocalDatabaseUseCase @Inject constructor(private val repository: ArticleRepository) {
    suspend operator fun invoke():List<Article>{
        return repository.getAllRecipesFromDatabase()
    }
}
