package com.david.tot.domain.sync

import com.david.tot.data.SyncConsumibleRepository
import com.david.tot.data.SyncRepository
import javax.inject.Inject

class RemoveOneSyncFromLocalDatabaseUseCase @Inject constructor(private val repository: SyncRepository) {
    suspend operator fun invoke(syncId:Int){
        repository.removeOneSyncFromLocalDatabase(syncId)
    }
}
