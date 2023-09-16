package com.david.tot.domain.consumible

import com.david.tot.data.ConsumibleRepository
import com.david.tot.domain.model.Article
import com.david.tot.domain.model.toDatabase
import javax.inject.Inject

class AddAllArticleToLocalDatabaseUseCase @Inject constructor(private val repository: ConsumibleRepository) {
    suspend operator fun invoke(articleList:List<Article>):List<Article>{
        return if(articleList.isNotEmpty()){
            repository.clearRecipes()
            repository.insertRecipes(articleList.map { it.toDatabase() })
            //recipes
            //recipes = repository.getAllRecipesFromApi()
            val articleListRetrieved = repository.getAllConsumiblesFromDatabase()
            articleListRetrieved
        }else{
            repository.getAllConsumiblesFromDatabase()
        }
    }
}
