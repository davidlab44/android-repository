package com.david.tot.ui.spotable

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
import com.david.tot.domain.model.Spotable
import com.david.tot.domain.spotable.RetrieveFilteredSpotablesFromLocalDatabaseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SpotableViewModel @Inject constructor(
    /*
    private val getAllFromApiUseCase: GetAllFromApiUseCase,
    private val getArticleByIdUseCase: GetArticleByIdUseCase,
    private val updateConsumedQuantityUseCase: UpdateConsumedQuantityUseCase,
    private val getFilteredArticleListUseCase: GetFilteredArticleListUseCase,
    private val getAllFromLocalDatabaseUseCase: GetAllFromLocalDatabaseUseCase,
    private val addOneSyncToLocalDatabaseUseCase: AddOneSyncFromLocalDatabaseUseCase,
    //private val updateAllArticlesInLocalDatabaseUseCase: UpdateAllArticlesInLocalDatabaseUseCase,
    //private val addAllArticleToLocalDatabaseUseCase: AddAllArticleToLocalDatabaseUseCase,
    private val addManySyncConsumibleToLocalDatabaseUseCase: AddManySyncConsumibleToLocalDatabaseUseCase,
    private val reAddAllConsumibleToLocalDatabaseUseCase: ReAddAllConsumibleToLocalDatabaseUseCase
    */
    private val retrieveFilteredSpotablesFromLocalDatabaseUseCase: RetrieveFilteredSpotablesFromLocalDatabaseUseCase,
) : ViewModel() {

    var spotableList by mutableStateOf<List<Spotable>>(emptyList())
    //var toastNot by mutableStateOf<Boolean>(false)

    fun retrieveFilteredSpotablesFromLocalDatabase(hash:String){
        CoroutineScope(Dispatchers.IO).launch {
            val result = retrieveFilteredSpotablesFromLocalDatabaseUseCase.invoke("%$hash%")
            if (!result.isNullOrEmpty()) {
                spotableList =result
            }
        }
    }

}