package com.david.tot.domain.confirmable

import com.david.tot.data.ConfirmableRepository
import com.david.tot.data.ConsumibleRepository
import com.david.tot.domain.model.Article
import com.david.tot.domain.model.Consumible
import com.david.tot.domain.model.toDatabase
import javax.inject.Inject

class GetAllConfirmableDetailsFromApiUseCase @Inject constructor(private val repository: ConfirmableRepository) {
    suspend operator fun invoke(user:String,reloadableId:Int,status:String):List<Consumible>{
        return repository.getAllConfirmableDetailsFromApi(user,reloadableId,status)
    }
}
