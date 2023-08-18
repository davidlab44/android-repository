package com.david.tot.domain.sync.reloadable

import com.david.tot.data.SyncConsumibleRepository
import com.david.tot.data.SyncReloadableRepository
import javax.inject.Inject

class RemoveManySyncReloadablesFromLocalDatabaseUseCase @Inject constructor(private val repository: SyncReloadableRepository) {
    suspend operator fun invoke(syncReloadableId:Int){
        repository.removeManySyncConsumiblesFromLocalDatabase(syncReloadableId)
    }
}
