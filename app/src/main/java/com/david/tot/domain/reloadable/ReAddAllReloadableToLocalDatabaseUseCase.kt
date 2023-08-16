package com.david.tot.domain.reloadable

import com.david.tot.data.ReloadableRepository
import com.david.tot.domain.model.Reloadable
import com.david.tot.domain.model.toDatabase
import javax.inject.Inject

class ReAddAllReloadableToLocalDatabaseUseCase @Inject constructor(private val repository: ReloadableRepository) {
    suspend operator fun invoke(consumibleListUpdated:List<Reloadable>):List<Reloadable>{
        var reloadableList = consumibleListUpdated
        repository.clearReloadables()
        val consumibleListEmpty = repository.getAllReloadablesFromDatabase()
        if(consumibleListEmpty.isEmpty()){
            repository.insertReloadables(reloadableList.map { it.toDatabase() })
            reloadableList = repository.getAllReloadablesFromDatabase()
        }
        return reloadableList
    }
}
