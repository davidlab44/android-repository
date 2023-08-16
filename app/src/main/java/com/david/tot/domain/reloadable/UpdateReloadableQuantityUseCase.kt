package com.david.tot.domain.reloadable

import com.david.tot.data.ReloadableRepository
import javax.inject.Inject

class UpdateReloadableQuantityUseCase @Inject constructor(private val repository: ReloadableRepository) {

    suspend operator fun invoke(idReloadable:Int,reloadableNewQuantity:Int):Int {
        return repository.updateConsumedQuantity(idReloadable,reloadableNewQuantity)
    }
}
