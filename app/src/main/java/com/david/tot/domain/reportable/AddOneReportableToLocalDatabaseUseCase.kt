package com.david.tot.domain.reportable

import com.david.tot.data.PreRepository
import com.david.tot.data.ReportableRepository
import com.david.tot.domain.model.Pre
import com.david.tot.domain.model.Reportable
import javax.inject.Inject

class AddOneReportableToLocalDatabaseUseCase @Inject constructor(private val repository: ReportableRepository) {
    suspend operator fun invoke(reportable: Reportable) {
        return repository.addOneReportableToLocalDatabase(reportable)
    }
}
