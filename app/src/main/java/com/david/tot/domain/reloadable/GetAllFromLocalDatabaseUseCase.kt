package com.david.tot.domain.reloadable

import com.david.tot.data.ConsumibleRepository
import com.david.tot.data.ReloadableRepository
import com.david.tot.domain.model.Article
import com.david.tot.domain.model.Reloadable
import javax.inject.Inject

class GetAllFromLocalDatabaseUseCase @Inject constructor(private val repository: ReloadableRepository) {
    suspend operator fun invoke():List<Reloadable>{
        return repository.getAllReloadablesFromDatabase()
    }
}
