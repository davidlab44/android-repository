package com.david.tot.domain.confirmable

import com.david.tot.data.ConfirmableRepository
import com.david.tot.data.ConsumibleRepository
import com.david.tot.domain.model.Article
import com.david.tot.domain.model.Confirmable
import javax.inject.Inject

class GetAllConfirmablesFromLocalDatabaseUseCase @Inject constructor(private val repository: ConfirmableRepository) {
    suspend operator fun invoke():List<Confirmable>{
        return repository.getAllConfirmablesFromApi()
    }
}
