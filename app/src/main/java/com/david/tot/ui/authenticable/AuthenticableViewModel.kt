package com.david.tot.ui.authenticable

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
import com.david.tot.domain.authenticable.AddOneAuthenticableToLocalDbUseCase
import com.david.tot.domain.authenticable.GetAllAuthenticablesFromApiUseCase
import com.david.tot.domain.authenticable.GetAnyAuthenticableUseCase
import com.david.tot.domain.authenticable.PostOneAuthenticableUseCase
import com.david.tot.domain.authenticable.RetrieveAllAuthenticablesFromLocalDbUseCase
import com.david.tot.domain.model.Authenticable
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthenticableViewModel @Inject constructor(
    private val getAllAuthenticablesFromApiUseCase: GetAllAuthenticablesFromApiUseCase,
    private val getAnyAuthenticableUseCase: GetAnyAuthenticableUseCase,
    private val postOneAuthenticableUseCase:PostOneAuthenticableUseCase,
    private val addOneAuthenticableToLocalDbUseCase: AddOneAuthenticableToLocalDbUseCase,
    private val retrieveAllAuthenticablesFromLocalDbUseCase: RetrieveAllAuthenticablesFromLocalDbUseCase
) : ViewModel() {

    var authenticableList by mutableStateOf<List<Authenticable>>(emptyList())
    var quantityToRestore by mutableStateOf<String>("")
    var inventoryOutputResponseCode by mutableStateOf<Int>(0)
    var authenticable by mutableStateOf<Authenticable>(Authenticable(1,"","","","","","","","",""))


    fun getAllAutenticables(){
        CoroutineScope(Dispatchers.IO).launch {
            authenticableList = retrieveAllAuthenticablesFromLocalDbUseCase.invoke()
            //authenticableFromApiList = getAllAuthenticablesFromApiUseCase.invoke()
            if(authenticableList.isNotEmpty()){
                //authenticable = getAnyAuthenticableUseCase.invoke()
                authenticable = authenticableList[0]
            }

        }
    }





    /*
    fun saveInventoryOutputInremoteServer(inventoryOutput: JsonObject){
        CoroutineScope(Dispatchers.IO).launch {
            inventoryOutputResponseCode = postOneDrugsDeliveryConsumerViewHeaderUseCase.invoke(inventoryOutput)
        }
    }
    */

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