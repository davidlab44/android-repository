package com.david.tot.domain.consumible

import com.david.tot.data.ConsumibleRepository
import com.david.tot.domain.model.Article
import com.david.tot.domain.model.toDatabase
import javax.inject.Inject

class GetArticleListUseCase @Inject constructor(private val repository: ConsumibleRepository) {
    suspend operator fun invoke():List<Article>{
        var recipes = repository.getAllConsumiblesFromApi()
        return if(recipes.isNotEmpty()){
            //TODO check internet connection before to clear database
            repository.clearRecipes()
            repository.insertRecipes(recipes.map { it.toDatabase() })
            //recipes
            //recipes = repository.getAllRecipesFromApi()
            recipes = repository.getAllConsumiblesFromDatabase()
            recipes
        }else{
            repository.getAllConsumiblesFromDatabase()
        }
    }
}
