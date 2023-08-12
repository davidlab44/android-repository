package com.david.tot.domain.sync_consumible

import com.david.tot.data.SyncConsumibleRepository
import com.david.tot.data.SyncRepository
import com.david.tot.domain.model.Sync
import com.david.tot.domain.model.SyncConsumible
import javax.inject.Inject

class GetAllSyncConsumibleFromLocalDatabaseUseCase @Inject constructor(private val repository: SyncConsumibleRepository) {
    suspend operator fun invoke():List<SyncConsumible> {
        return repository.getAll()
    }
}
