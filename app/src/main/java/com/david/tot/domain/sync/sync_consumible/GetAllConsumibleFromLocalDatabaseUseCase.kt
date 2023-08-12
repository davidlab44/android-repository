package com.david.tot.domain.sync.sync_consumible

import com.david.tot.data.SyncConsumibleRepository
import com.david.tot.data.SyncRepository
import com.david.tot.domain.model.Consumible
import com.david.tot.domain.model.Sync
import javax.inject.Inject

class GetAllConsumibleFromLocalDatabaseUseCase @Inject constructor(private val repository: SyncConsumibleRepository) {
    suspend operator fun invoke():List<Consumible> {
        return repository.getAllConsumibleFromLocalDatabaseUseCase()
    }
}
