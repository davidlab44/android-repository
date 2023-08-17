package com.david.tot.domain.pre

import com.david.tot.data.PreRepository
import com.david.tot.data.SyncRepository
import com.david.tot.domain.model.Pre
import com.david.tot.domain.model.Sync
import javax.inject.Inject

class AddOnePreFromLocalDatabaseUseCase @Inject constructor(private val repository: PreRepository) {
    suspend operator fun invoke(pre:Pre) {
        return repository.addOnePreToLocalDatabase(pre)
    }
}
