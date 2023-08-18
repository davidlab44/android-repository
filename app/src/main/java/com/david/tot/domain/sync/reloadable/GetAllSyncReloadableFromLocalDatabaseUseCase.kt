package com.david.tot.domain.sync.reloadable

import com.david.tot.data.SyncConsumibleRepository
import com.david.tot.data.SyncReloadableRepository
import com.david.tot.domain.model.SyncConsumible
import com.david.tot.domain.model.SyncReloadable
import javax.inject.Inject

class GetAllSyncReloadableFromLocalDatabaseUseCase @Inject constructor(private val repository: SyncReloadableRepository) {
    suspend operator fun invoke(syncType:String):List<SyncReloadable> {
        return repository.getAllSyncReloadableFromLocalDatabase(syncType)
    }
}
