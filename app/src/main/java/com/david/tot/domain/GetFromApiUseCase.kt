package com.david.tot.domain

import com.david.tot.data.ConsumibleRepository
import com.david.tot.domain.model.Article
import com.david.tot.domain.model.toDatabase
import javax.inject.Inject

class GetFromApiUseCase @Inject constructor(private val repository: ConsumibleRepository) {
    suspend operator fun invoke(st: kotlin.String):List<Article>{
        var recipes = repository.getAllRecipesFromApi()
        return if(recipes.isNotEmpty()){
            //TODO check internet connection before to clear database
            repository.clearRecipes()
            repository.insertRecipes(recipes.map { it.toDatabase() })
            //recipes
            recipes = repository.getAllRecipesFromDatabase()
            //recipes = repository.getAllRecipesFromDatabase()
            recipes
        }else{
            repository.getAllRecipesFromDatabase()
        }
    }
}
