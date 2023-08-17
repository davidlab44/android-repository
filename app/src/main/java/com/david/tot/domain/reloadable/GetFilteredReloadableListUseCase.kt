package com.david.tot.domain.reloadable

import com.david.tot.data.ReloadableRepository
import com.david.tot.domain.model.Reloadable
import javax.inject.Inject

class GetFilteredReloadableListUseCase @Inject constructor(private val repository: ReloadableRepository) {
    suspend operator fun invoke(hash:String):List<Reloadable>{
        /*
        //var recipes = repository.getAllRecipesFromApi()
        return if(recipes.isNotEmpty()){
            //TODO check internet connection before to clear database
            repository.clearRecipes()
            repository.insertRecipes(recipes.map { it.toDatabase() })
            //recipes
            //recipes = repository.getAllRecipesFromApi()
            recipes = repository.getFiltered(hash)
            recipes
        }else{
            repository.getAllRecipesFromDatabase()
        }
        */
        return repository.getFiltered(hash)
    }
}
