package com.david.tot.domain.consumible

import com.david.tot.data.ConsumibleRepository
import com.david.tot.domain.model.Article
import com.david.tot.domain.model.toDatabase
import javax.inject.Inject

class UpdateAllArticlesInLocalDatabaseUseCase @Inject constructor(private val repository: ConsumibleRepository) {
    suspend operator fun invoke(articleList:List<Article>):List<Article>{
        
        return if(articleList.isNotEmpty()){
            //TODO check internet connection before to clear database
            repository.clearRecipes()
            repository.insertRecipes(articleList.map { it.toDatabase() })
            val articleListRetrieved = repository.getAllRecipesFromDatabase()
            articleListRetrieved
        }else{
            repository.getAllRecipesFromDatabase()
        }
    }
}
