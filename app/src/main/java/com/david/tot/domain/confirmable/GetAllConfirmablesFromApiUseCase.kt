package com.david.tot.domain.confirmable

import com.david.tot.data.ConsumibleRepository
import com.david.tot.domain.model.Article
import com.david.tot.domain.model.toDatabase
import javax.inject.Inject

class GetAllConfirmablesFromApiUseCase @Inject constructor(private val repository: ConsumibleRepository) {
    suspend operator fun invoke():List<Article>{
        var consumibleList = repository.getAllConsumiblesFromApi()
        return if(consumibleList.isNotEmpty()){
            repository.clearRecipes()
            repository.insertRecipes(consumibleList.map { it.toDatabase() })
            consumibleList = repository.getAllConsumiblesFromDatabase()
            consumibleList
        }else{
            repository.getAllConsumiblesFromDatabase()
        }
    }
}
