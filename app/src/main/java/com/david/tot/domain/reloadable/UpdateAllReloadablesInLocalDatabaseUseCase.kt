package com.david.tot.domain.reloadable

import com.david.tot.data.ReloadableRepository
import com.david.tot.domain.model.Reloadable
import com.david.tot.domain.model.toDatabase
import javax.inject.Inject

class UpdateAllReloadablesInLocalDatabaseUseCase @Inject constructor(private val repository: ReloadableRepository) {
    suspend operator fun invoke(articleList:List<Reloadable>):List<Reloadable>{
        
        return if(articleList.isNotEmpty()){
            //TODO check internet connection before to clear database
            repository.clearReloadables()
            repository.insertReloadables(articleList.map { it.toDatabase() })
            val articleListRetrieved = repository.getAllReloadablesFromDatabase()
            articleListRetrieved
        }else{
            repository.getAllReloadablesFromDatabase()
        }
    }
}
