package com.david.tot.domain.sync

import com.david.tot.data.SyncRepository
import com.david.tot.domain.model.Sync
import javax.inject.Inject

class AddOneSyncFromLocalDatabaseUseCase @Inject constructor(private val repository: SyncRepository) {
    suspend operator fun invoke(sync: Sync) {
        return repository.addOne(sync)
    }
}
