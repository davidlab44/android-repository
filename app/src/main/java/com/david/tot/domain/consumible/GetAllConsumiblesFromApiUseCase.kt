package com.david.tot.domain.consumible

import com.david.tot.data.ConsumibleRepository
import com.david.tot.domain.model.Article
import com.david.tot.domain.model.toDatabase
import javax.inject.Inject

class GetAllConsumiblesFromApiUseCase @Inject constructor(private val repository: ConsumibleRepository) {
    suspend operator fun invoke(user:String):List<Article>{
        var consumibleList = repository.getAllConsumiblesFromApi(user)
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
