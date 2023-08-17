package com.david.tot.domain.authenticable

import com.david.tot.data.AuthenticableRepository
import com.david.tot.domain.model.Authenticable
import javax.inject.Inject

class AddOneAuthenticableToLocalDbUseCase @Inject constructor(private val repository: AuthenticableRepository) {

    suspend operator fun invoke(authenticable:Authenticable) {
        repository.clearAllAuthenticablesFromLocalDb()
        repository.addOneAuthenticableToLocalDb(authenticable)
    }
}
