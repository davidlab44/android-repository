package com.david.tot.domain.confirmable

import com.david.tot.data.AuthenticableRepository
import com.david.tot.data.ConfirmableRepository
import com.david.tot.domain.model.ConfirmableClean
import javax.inject.Inject

class PostOneConfirmableUseCase @Inject constructor(private val repository: ConfirmableRepository) {
    suspend operator fun invoke(confirmableClean: ConfirmableClean): Int {
        return repository.postOneConfirmable(confirmableClean)
    }
}
