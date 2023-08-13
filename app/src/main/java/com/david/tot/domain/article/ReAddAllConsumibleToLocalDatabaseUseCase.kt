package com.david.tot.domain.article

import com.david.tot.data.ArticleRepository
import com.david.tot.domain.model.Article
import com.david.tot.domain.model.toDatabase
import javax.inject.Inject

class ReAddAllConsumibleToLocalDatabaseUseCase @Inject constructor(private val repository: ArticleRepository) {
    suspend operator fun invoke(consumibleListUpdated:List<Article>):List<Article>{
        var recipes = consumibleListUpdated
        repository.clearRecipes()
        val consumibleListEmpty = repository.getAllRecipesFromDatabase()
        if(consumibleListEmpty.isEmpty()){
            repository.insertRecipes(recipes.map { it.toDatabase() })
            recipes = repository.getAllRecipesFromDatabase()
        }
        return recipes
    }
}
