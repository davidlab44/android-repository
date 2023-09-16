package com.david.tot.domain.authenticable

import com.david.tot.data.AuthenticableRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val repository: AuthenticableRepository) {
    suspend operator fun invoke(user:String,password:String): Int {
        return repository.login(user,password)
    }
}
