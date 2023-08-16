package com.david.tot.domain.reloadable

import com.david.tot.data.ReloadableRepository
import com.david.tot.domain.model.Reloadable
import com.david.tot.domain.model.toDatabase
import javax.inject.Inject

class GetReloadableListUseCase @Inject constructor(private val repository: ReloadableRepository) {
    suspend operator fun invoke():List<Reloadable>{
        var reloadableList = repository.getAllReloadablesFromApi()
        return if(reloadableList.isNotEmpty()){
            //TODO check internet connection before to clear database
            repository.clearReloadables()
            repository.addAllReloadablesToLocalDb(reloadableList.map { it.toDatabase() })
            //recipes
            //recipes = repository.getAllRecipesFromApi()
            reloadableList = repository.getAllReloadablesFromDatabase()
            reloadableList
        }else{
            repository.getAllReloadablesFromDatabase()
        }
    }
}
