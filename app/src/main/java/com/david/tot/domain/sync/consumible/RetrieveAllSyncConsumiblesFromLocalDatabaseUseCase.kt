package com.david.tot.domain.sync.consumible

import com.david.tot.data.SyncConsumibleRepository
import com.david.tot.domain.model.Consumible
import javax.inject.Inject

class RetrieveAllSyncConsumiblesFromLocalDatabaseUseCase @Inject constructor(private val repository: SyncConsumibleRepository) {
    /*
    suspend operator fun invoke():List<Consumible> {
        return repository.retrieveAllSyncConsumiblesFromLocalDatabase()
    }

     */
}
