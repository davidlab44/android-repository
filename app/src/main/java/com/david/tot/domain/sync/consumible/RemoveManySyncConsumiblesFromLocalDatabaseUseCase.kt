package com.david.tot.domain.sync.consumible

import com.david.tot.data.SyncConsumibleRepository
import javax.inject.Inject

class RemoveManySyncConsumiblesFromLocalDatabaseUseCase @Inject constructor(private val repository: SyncConsumibleRepository) {
    suspend operator fun invoke(syncConsumibleId:Int):Int{
        return repository.removeManySyncConsumiblesFromLocalDatabase(syncConsumibleId)
    }
}
