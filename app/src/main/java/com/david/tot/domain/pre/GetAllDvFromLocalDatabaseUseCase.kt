package com.david.tot.domain.pre

import com.david.tot.data.PreRepository
import com.david.tot.domain.model.Dv
import com.david.tot.domain.model.Pre
import javax.inject.Inject

class GetAllDvFromLocalDatabaseUseCase @Inject constructor(private val repository: PreRepository) {
    suspend operator fun invoke():List<Dv> {
        return repository.getAllDvFromDatabase()
    }
}
