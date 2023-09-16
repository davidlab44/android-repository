package com.david.tot.domain.authenticable

import com.david.tot.data.AuthenticableRepository
import com.david.tot.domain.model.Authenticable
import com.david.tot.domain.model.toDatabase
import javax.inject.Inject

class GetAllAuthenticablesFromApiUseCase @Inject constructor(private val repository: AuthenticableRepository) {

    // delete all DrugsDeliveryConsumerViewHeader from database and insert the new ones
    suspend operator fun invoke(user:String):Authenticable{
        var recipes = repository.getAllAuthenticablesFromApi(user)
        return if(recipes.isNotEmpty()){
            //TODO check internet connection before to clear database
            repository.clearAllAuthenticablesFromLocalDb()
            repository.addAllAuthenticablesToLocalDb(recipes.map { it.toDatabase() })
            //recipes
            //recipes = repository.getAllRecipesFromApi()
            recipes = repository.getAllDrugsDeliveryConsumerViewHeaderFromDatabase()
            recipes[0]
        }else{
            repository.getAllDrugsDeliveryConsumerViewHeaderFromDatabase()[0]
        }
    }
}
