package com.david.tot.domain.article

import com.david.tot.data.ProductRepository
import com.david.tot.domain.model.Article
import com.david.tot.domain.model.toDatabase
import javax.inject.Inject

class GetFilteredArticleListUseCase @Inject constructor(private val repository: ProductRepository) {
    suspend operator fun invoke(hash:String):List<Article>{
        var recipes = repository.getAllRecipesFromApi()
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
    }
}
