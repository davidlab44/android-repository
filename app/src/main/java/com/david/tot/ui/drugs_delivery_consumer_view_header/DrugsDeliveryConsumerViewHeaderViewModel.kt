package com.david.tot.ui.drugs_delivery_consumer_view_header

/*
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

import com.david.tot.domain.UpdateProductUseCase
import com.david.tot.domain.UpdateQuantityUseCase
import com.david.tot.domain.article.GetArticleByIdUseCase
import com.david.tot.domain.article.GetArticleListUseCase
import com.david.tot.domain.model.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleViewModel @Inject constructor(
    private val getArticleListUseCase: GetArticleListUseCase,
    private val updateProductUseCase: UpdateProductUseCase,
    private val updateQuantityUseCase: UpdateQuantityUseCase,
    private val getArticleByIdUseCase: GetArticleByIdUseCase,
    ) : ViewModel() {

    */


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.david.tot.domain.drugs_delivery_consumer_view_header.GetAllDrugsDeliveryConsumerViewHeaderUseCase
import com.david.tot.domain.drugs_delivery_consumer_view_header.GetAnyDrugsDeliveryConsumerViewHeaderUseCase
import com.david.tot.domain.drugs_delivery_consumer_view_header.PostOneDrugsDeliveryConsumerViewHeaderUseCase
import com.david.tot.domain.model.DrugsDeliveryConsumerViewHeader
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.json.JsonArray
import javax.inject.Inject

@HiltViewModel
class DrugsDeliveryConsumerViewHeaderViewModel @Inject constructor(
    private val getAllDrugsDeliveryConsumerViewHeaderUseCase: GetAllDrugsDeliveryConsumerViewHeaderUseCase,
    private val getAnyDrugsDeliveryConsumerViewHeaderUseCase: GetAnyDrugsDeliveryConsumerViewHeaderUseCase,
    private val postOneDrugsDeliveryConsumerViewHeaderUseCase:PostOneDrugsDeliveryConsumerViewHeaderUseCase
) : ViewModel() {

    var drugsDeliveryConsumerViewHeaderFromApiList by mutableStateOf<List<DrugsDeliveryConsumerViewHeader>>(emptyList())
    var quantityToRestore by mutableStateOf<String>("")
    var inventoryOutputResponseCode by mutableStateOf<Int>(0)
    var drugsDeliveryConsumerViewHeader by mutableStateOf<DrugsDeliveryConsumerViewHeader>(DrugsDeliveryConsumerViewHeader(1,"","","","","","","","",""))

    fun getAlldrugsDeliveryConsumerViewHeader(){
        CoroutineScope(Dispatchers.IO).launch {
            drugsDeliveryConsumerViewHeaderFromApiList = getAllDrugsDeliveryConsumerViewHeaderUseCase.invoke()
            if(drugsDeliveryConsumerViewHeaderFromApiList.isNotEmpty()){
                drugsDeliveryConsumerViewHeader = getAnyDrugsDeliveryConsumerViewHeaderUseCase.invoke()
            }

        }
    }


    fun saveInventoryOutputInremoteServer(inventoryOutput: JsonArray){
        CoroutineScope(Dispatchers.IO).launch {
            inventoryOutputResponseCode = postOneDrugsDeliveryConsumerViewHeaderUseCase.invoke(inventoryOutput)
        }
    }

    /*
    fun getAnyDrugsDeliveryConsumerViewHeaderFromDatabase(){
        CoroutineScope(Dispatchers.IO).launch {
            drugsDeliveryConsumerViewHeader = getAnyDrugsDeliveryConsumerViewHeaderUseCase.invoke()
        }
    }

     */

    /*
    var recipeModel by mutableStateOf<List<Article>>(emptyList())
    var quantityToRestore by mutableStateOf<String>("")

    fun onCreate() {
        CoroutineScope(Dispatchers.IO).launch {
            val result = getArticleListUseCase.invoke()
            if (!result.isNullOrEmpty()) {
                recipeModel =result
            }
        }
    }
    */
}