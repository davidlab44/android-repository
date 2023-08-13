package com.david.tot.domain.sync.consumible

import com.david.tot.data.SyncConsumibleRepository
import com.david.tot.domain.model.SyncConsumible
import javax.inject.Inject

class GetAllSyncConsumibleFromLocalDatabaseUseCase @Inject constructor(private val repository: SyncConsumibleRepository) {
    suspend operator fun invoke():List<SyncConsumible> {
        return repository.getAllSyncConsumibleFromLocalDatabase()
    }
}
