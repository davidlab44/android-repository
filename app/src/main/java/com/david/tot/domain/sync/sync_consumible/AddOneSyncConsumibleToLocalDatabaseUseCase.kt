package com.david.tot.domain.sync.sync_consumible

import com.david.tot.data.SyncConsumibleRepository
import com.david.tot.data.SyncRepository
import com.david.tot.domain.model.Sync
import com.david.tot.domain.model.SyncConsumible
import javax.inject.Inject

class AddOneSyncConsumibleToLocalDatabaseUseCase @Inject constructor(private val repository: SyncConsumibleRepository) {
    suspend operator fun invoke(syncConsumible: SyncConsumible) {
        return repository.addOne(syncConsumible)
    }
}
