package com.david.tot.domain.sync.sync_consumible

import com.david.tot.data.ArticleRepository
import com.david.tot.data.SyncConsumibleRepository
import com.david.tot.domain.model.SyncConsumible
import com.david.tot.domain.model.toDatabase
import javax.inject.Inject

class AddManyArticleToLocalDatabaseUseCase @Inject constructor(private val repository: SyncConsumibleRepository) {
    suspend operator fun invoke(syncConsumibleList:List<SyncConsumible>){
        repository.addManyArticleToLocalDatabase(syncConsumibleList)
    }
}
