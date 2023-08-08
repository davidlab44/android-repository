package com.david.tot.domain.article

import com.david.tot.data.ArticleRepository
import com.david.tot.domain.model.Article
import com.david.tot.domain.model.toDatabase
import javax.inject.Inject

class GetArticleListUseCase @Inject constructor(private val repository: ArticleRepository) {
    suspend operator fun invoke():List<Article>{
        var recipes = repository.getAllRecipesFromApi()
        return if(recipes.isNotEmpty()){
            //TODO check internet connection before to clear database
            repository.clearRecipes()
            repository.insertRecipes(recipes.map { it.toDatabase() })
            //recipes
            //recipes = repository.getAllRecipesFromApi()
            recipes = repository.getAllRecipesFromDatabase()
            recipes
        }else{
            repository.getAllRecipesFromDatabase()
        }
    }
}
