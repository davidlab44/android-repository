package com.david.tot.domain.authenticable

import com.david.tot.data.AuthenticableRepository
import javax.inject.Inject

class PostOneReloadableHeaderUseCase @Inject constructor(private val repository: AuthenticableRepository) {
    suspend operator fun invoke(jsonObject: String): Int {
        return repository.postOneReloadableHeader(jsonObject.toString())
    }
}
