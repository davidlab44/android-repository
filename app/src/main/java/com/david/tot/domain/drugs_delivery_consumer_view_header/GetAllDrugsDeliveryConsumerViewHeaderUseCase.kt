package com.david.tot.domain.drugs_delivery_consumer_view_header

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.david.tot.data.DrugsDeliveryConsumerViewHeaderRepository
import com.david.tot.domain.model.Article
import com.david.tot.domain.model.DrugsDeliveryConsumerViewHeader
import com.david.tot.domain.model.toDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class GetAllDrugsDeliveryConsumerViewHeaderUseCase @Inject constructor(private val repository: DrugsDeliveryConsumerViewHeaderRepository) {


    // delete all DrugsDeliveryConsumerViewHeader from database and insert the new ones
    suspend operator fun invoke():List<DrugsDeliveryConsumerViewHeader>{
        var recipes = repository.getAllDrugsDeliveryConsumerViewHeaderFromApi()
        return if(recipes.isNotEmpty()){
            //TODO check internet connection before to clear database
            repository.clearDrugsDeliveryConsumerViewHeader()
            repository.insertDrugsDeliveryConsumerViewHeader(recipes.map { it.toDatabase() })
            //recipes
            //recipes = repository.getAllRecipesFromApi()
            recipes = repository.getAllDrugsDeliveryConsumerViewHeaderFromDatabase()
            recipes
        }else{
            repository.getAllDrugsDeliveryConsumerViewHeaderFromDatabase()
        }
    }
}
