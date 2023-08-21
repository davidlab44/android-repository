package com.david.tot.domain.confirmable

import com.david.tot.data.AuthenticableRepository
import com.david.tot.data.ConfirmableRepository
import javax.inject.Inject

class PostOneConfirmableUseCase @Inject constructor(private val repository: ConfirmableRepository) {
    suspend operator fun invoke(jsonObject: String): Int {
        return repository.postOneConfirmable(jsonObject.toString())
    }
}
