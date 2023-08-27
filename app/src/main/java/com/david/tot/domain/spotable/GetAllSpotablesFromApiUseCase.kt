package com.david.tot.domain.spotable

import com.david.tot.data.ReloadableRepository
import com.david.tot.data.SpotableRepository
import com.david.tot.domain.model.Reloadable
import com.david.tot.domain.model.Spotable
import com.david.tot.domain.model.toDatabase
import com.david.tot.util.SpotableLists
import javax.inject.Inject

class GetAllSpotablesFromApiUseCase @Inject constructor(private val repository: SpotableRepository) {
    suspend operator fun invoke():List<Spotable>{
        var recipes = SpotableLists
        return if(recipes.isNotEmpty()){
            repository.clearSpotables()
            repository.addAllSpotablesToLocalDb(recipes.map { it.toDatabase() })
            recipes = repository.getAllSpotablesFromDatabase()
            recipes
        }else{
            repository.getAllSpotablesFromDatabase()
        }
    }
}
