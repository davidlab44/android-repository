package com.david.tot.domain.reloadable

import com.david.tot.data.ReloadableRepository
import com.google.gson.JsonArray
import javax.inject.Inject

class PostManyReloadableUseCase @Inject constructor(private val repository: ReloadableRepository) {
    /*
    suspend operator fun invoke(jsonArray: JsonArray): Int {
        return repository.postMany(jsonArray)
    }
    
     */
}
