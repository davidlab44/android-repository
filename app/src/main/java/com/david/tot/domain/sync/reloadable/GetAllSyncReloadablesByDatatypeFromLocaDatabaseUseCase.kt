package com.david.tot.domain.sync.reloadable

import com.david.tot.data.SyncReloadableRepository
import com.david.tot.domain.model.Sync
import com.david.tot.domain.model.SyncReloadable
import javax.inject.Inject

class GetAllSyncReloadablesByDatatypeFromLocaDatabaseUseCase @Inject constructor(private val repository: SyncReloadableRepository) {
    suspend operator fun invoke(dataType:String):List<SyncReloadable> {
        return repository.getAllSyncReloadablesByDatatypeFromLocaDatabase(dataType)
    }
}
