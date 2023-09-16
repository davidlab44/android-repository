package com.david.tot.domain.reloadable

import com.david.tot.data.ReloadableRepository
import com.david.tot.domain.model.Reloadable
import com.david.tot.domain.model.toDatabase
import javax.inject.Inject

class GetAllReloadablesFromApiUseCase @Inject constructor(private val repository: ReloadableRepository) {
    suspend operator fun invoke(user:String,restockId:Int,status:String):List<Reloadable> {
        return repository.getAllReloadablesFromApi(user, restockId, status)
    }

    /*
    suspend operator fun invoke(user:String,restockId:Int,status:String):List<Reloadable>{
        var recipes = repository.getAllReloadablesFromApi(user,restockId,status)
        return if(recipes.isNotEmpty()){
            repository.clearReloadables()
            repository.addAllReloadablesToLocalDb(recipes.map { it.toDatabase() })
            //recipes
            //recipes = repository.getAllRecipesFromApi()
            recipes = repository.getAllReloadablesFromDatabase()
            recipes
        }else{
            repository.getAllReloadablesFromDatabase()
        }
    }
    */
}
