package com.david.tot.ui.article

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
import kotlinx.coroutines.Delay
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScreenArticleViewModel @Inject constructor(
    private val getArticleListUseCase: GetArticleListUseCase,
    private val updateProductUseCase: UpdateProductUseCase,
    private val updateQuantityUseCase: UpdateQuantityUseCase,
    private val getArticleByIdUseCase: GetArticleByIdUseCase,

    ) : ViewModel() {

    var recipeModel by mutableStateOf<List<Article>>(emptyList())
    var quantityToRestore by mutableStateOf<String>("")

    fun onCreate() {
        Log.e("TAG","TAG")
        //viewModelScope.launch {

        CoroutineScope(Dispatchers.IO).launch {
            //ProductRepository().requestProductList()
            //val product = Product(999,"Espinaca","fruiit.jpg", "glu glu glu",10000,0,0,1)
            //val a = addProductUseCase.invoke(product)

            /*
            val id = Calendar.getInstance().time
            val product = Product(999,"Espinaca","https:\\/\\/static9.depositphotos.com\\/1642482\\/1148\\/i\\/600\\/depositphotos_11489401-stock-photo-orange-fruit.jpg", "glu glu glu",10000,0,0,1)
            val a = addProductUseCase.invoke(product)
            val ff =77
            */

            val result = getArticleListUseCase.invoke()
            if (!result.isNullOrEmpty()) {
                recipeModel =result
            }
        }
    }

    fun updateQuantity(idArticle:Int,quantityToRestore:Int){
        CoroutineScope(Dispatchers.IO).launch {
            //Thread.sleep(1000)
            val quantityToRestore = updateQuantityUseCase(idArticle,quantityToRestore).toString()
            val article = getArticleByIdUseCase.invoke(idArticle)
            val article2 = article
        }
    }

    /*
    fun getArticleByIdUseCase(idArticle:Int){
        CoroutineScope(Dispatchers.IO).launch {

        }
    }

     */

    /*
    fun updateRecipeList(hash:String){
        CoroutineScope(Dispatchers.IO).launch {
            val result = getRecipesUseCase.invoke("%$hash%")
            if (!result.isNullOrEmpty()) {
                recipeModel =result
            }
        }
    }
    */

    /*
    fun addProduct(product:Product){
        //TODO mostrar lo que retorna el producto creado  o por lo menos un aconfirmacion visual para  el usuario de que si se creo el producto
        CoroutineScope(Dispatchers.IO).launch {
            addProductUseCase.invoke(product)
        }
    }
    */

    //Edit Product
    //the purpose of this group of variables is jus pass the necesary information to Screen Detail in order to create bundle
    var productLocalId by mutableStateOf<Int>(0)
    var productRemoteId by mutableStateOf<Int>(0)
    var productName by mutableStateOf<String>("")
    var productDescription by mutableStateOf<String>("")
    var productImage by mutableStateOf<String>("")
    var productPrice by mutableStateOf<Int>(0)
}