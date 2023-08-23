package com.david.tot.domain.sync.reloadable

import com.david.tot.data.SyncReloadableRepository
import com.david.tot.domain.model.SyncReloadable
import javax.inject.Inject

class RetrieveAllSyncReloadablesFromLocalDatabaseUseCase @Inject constructor(private val repository: SyncReloadableRepository) {
    suspend operator fun invoke():List<SyncReloadable> {
        return repository.retrieveAllSyncReloadablesFromLocalDatabase()
    }
}
