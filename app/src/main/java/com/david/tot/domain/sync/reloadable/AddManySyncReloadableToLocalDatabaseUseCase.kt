package com.david.tot.domain.sync.reloadable

import com.david.tot.data.SyncReloadableRepository
import com.david.tot.domain.model.SyncReloadable
import javax.inject.Inject

class AddManySyncReloadableToLocalDatabaseUseCase @Inject constructor(private val repository: SyncReloadableRepository) {
    suspend operator fun invoke(syncReloadableList:List<SyncReloadable>){
        repository.addManyReloadablesToLocalDatabase(syncReloadableList)
    }
}
