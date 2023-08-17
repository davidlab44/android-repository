package com.david.tot.domain.reloadable

import com.david.tot.data.ReloadableRepository
import com.david.tot.domain.model.Reloadable
import javax.inject.Inject

class GetReloadableByIdUseCase @Inject constructor(private val repository: ReloadableRepository) {
    suspend operator fun invoke(local_id:Int): Reloadable {
        return repository.getReloadableById(local_id)
    }
}
