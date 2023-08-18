package com.david.tot.domain.sync.reloadable

import com.david.tot.data.SyncReloadableRepository
import javax.inject.Inject

class RemoveManySyncReloadablesByObjectIdFromLocalDatabaseUseCase @Inject constructor(private val repository: SyncReloadableRepository) {
    suspend operator fun invoke(syncReloadableId:Int):Int{
        return repository.removeManySyncReloadablesByObjectIdFromLocalDatabase(syncReloadableId)
    }
}
