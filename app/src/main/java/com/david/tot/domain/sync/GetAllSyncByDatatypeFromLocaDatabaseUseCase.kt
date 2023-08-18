package com.david.tot.domain.sync

import com.david.tot.data.SyncReloadableRepository
import com.david.tot.data.SyncRepository
import com.david.tot.domain.model.Sync
import javax.inject.Inject

class GetAllSyncByDatatypeFromLocaDatabaseUseCase @Inject constructor(private val repository: SyncRepository) {
    suspend operator fun invoke(syncType:String):List<Sync> {
        return repository.getAllSyncByDatatypeFromLocaDatabase(syncType)
    }
}
