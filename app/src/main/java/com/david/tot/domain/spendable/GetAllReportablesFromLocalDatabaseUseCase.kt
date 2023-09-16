package com.david.tot.domain.spendable

import com.david.tot.data.PreRepository
import com.david.tot.data.ReportableRepository
import com.david.tot.data.SpendableRepository
import com.david.tot.domain.model.Pre
import com.david.tot.domain.model.Reportable
import com.david.tot.domain.model.Spendable
import javax.inject.Inject

class GetAllSpendablesFromLocalDatabaseUseCase @Inject constructor(private val repository: SpendableRepository) {
    suspend operator fun invoke():List<Spendable> {
        return repository.getAllSpendableFromDatabase()
    }
}
