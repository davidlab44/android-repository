package com.david.tot.domain.reloadable

import com.david.tot.data.ReloadableRepository
import com.david.tot.data.SyncReloadableRepository
import javax.inject.Inject

class RemoveAllReloadablesFromLocalDatabaseUseCase @Inject constructor(private val repository: ReloadableRepository) {
    suspend operator fun invoke():Int{
        return repository.removeAllReloadablesFromLocalDatabase()
    }
}
