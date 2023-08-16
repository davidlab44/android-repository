package com.david.tot.domain.reloadable

import com.david.tot.data.ReloadableRepository
import com.david.tot.domain.model.Reloadable
import com.david.tot.domain.model.toDatabase
import javax.inject.Inject

class GetAllReloadablesFromApiUseCase @Inject constructor(private val repository: ReloadableRepository) {
    suspend operator fun invoke():List<Reloadable>{
        //TODO
        //TODO
        //TODO
        //TODO
        //TODO
        //TODO
        //TODO no permitir consultar nada hasta que no se termine de sincronizar!!!!
        //OJO
        var recipes = repository.getAllReloadablesFromApi()
        return if(recipes.isNotEmpty()){
            //TODO check internet connection before to clear database
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
}
