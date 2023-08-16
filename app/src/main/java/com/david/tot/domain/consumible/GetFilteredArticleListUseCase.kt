package com.david.tot.domain.consumible

import com.david.tot.data.ConsumibleRepository
import com.david.tot.domain.model.Article
import javax.inject.Inject

class GetFilteredArticleListUseCase @Inject constructor(private val repository: ConsumibleRepository) {
    suspend operator fun invoke(hash:String):List<Article>{
        /*
        //var recipes = repository.getAllRecipesFromApi()
        return if(recipes.isNotEmpty()){
            //TODO check internet connection before to clear database
            repository.clearRecipes()
            repository.insertRecipes(recipes.map { it.toDatabase() })
            //recipes
            //recipes = repository.getAllRecipesFromApi()
            recipes = repository.getFiltered(hash)
            recipes
        }else{
            repository.getAllRecipesFromDatabase()
        }
        */
        return repository.getFiltered(hash)
    }
}
