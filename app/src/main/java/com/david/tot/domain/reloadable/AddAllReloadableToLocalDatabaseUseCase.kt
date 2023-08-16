package com.david.tot.domain.reloadable

import com.david.tot.data.ReloadableRepository
import com.david.tot.domain.model.Reloadable
import com.david.tot.domain.model.toDatabase
import javax.inject.Inject

class AddAllReloadableToLocalDatabaseUseCase @Inject constructor(private val repository: ReloadableRepository) {
    suspend operator fun invoke(reloadableList:List<Reloadable>):List<Reloadable>{
        return if(reloadableList.isNotEmpty()){
            repository.clearReloadables()
            repository.addAllReloadablesToLocalDb(reloadableList.map { it.toDatabase() })
            //recipes
            //recipes = repository.getAllRecipesFromApi()
            val articleListRetrieved = repository.getAllReloadablesFromDatabase()
            articleListRetrieved
        }else{
            repository.getAllReloadablesFromDatabase()
        }
    }
}
