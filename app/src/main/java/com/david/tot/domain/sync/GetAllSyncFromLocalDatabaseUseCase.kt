package com.david.tot.domain.sync

import com.david.tot.data.SyncRepository
import com.david.tot.domain.model.Sync
import javax.inject.Inject

class GetAllSyncFromLocalDatabaseUseCase @Inject constructor(private val repository: SyncRepository) {
    suspend operator fun invoke():List<Sync> {
        return repository.getAll()
    }
}
