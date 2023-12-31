package com.david.tot.ui

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import android.annotation.SuppressLint
import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import com.david.tot.ui.authenticable.AuthenticableActivity

import com.david.tot.ui.consumible.ConsumibleViewModel
import com.david.tot.ui.authenticable.AuthenticableViewModel
import com.david.tot.ui.consumible.ArticleActivity
import com.david.tot.ui.sync.SyncActivity
import com.david.tot.util.assetList

@SuppressLint("UnrememberedMutableState")
@Composable
fun BodyList(consumibleViewModel: ConsumibleViewModel, authenticableViewModel: AuthenticableViewModel) {

    val mContext = LocalContext.current
    mContext.startActivity(Intent(mContext, ArticleActivity::class.java))

    /*
    //var lista by mutableStateOf<List<Asset>>(emptyList())
    //var lista by mutableStateOf<MutableListList<Asset>>(MutableE)
    //var lista by mutableStateOf<List<Asset>>(emptyList())

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        //horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //var text by rememberSaveable { mutableStateOf("") }
        Row(
            modifier = Modifier.padding(all = 0.dp).height(80.dp),horizontalArrangement = Arrangement.Center
            //horizontalArrangement = Arrangement.Center
        ) {
            //ScreenComponentHeader(drugsDeliveryConsumerViewHeaderViewModel)
            Box(){
                Card(
                    modifier = Modifier.fillMaxWidth().padding(0.dp).clickable{},
                    elevation = 10.dp,
                    content = {
                        Column( horizontalAlignment = Alignment.Start,
                            modifier = Modifier.border(0.dp, Color.Gray, RectangleShape).fillMaxWidth().padding(5.dp)) {
                            Row(
                                modifier = Modifier.padding(all = 12.dp),horizontalArrangement = Arrangement.Center
                            ){
                                androidx.compose.material3.Text(
                                    text = "LISTADO DE ACTIVOS FIJOS",
                                    textAlign = TextAlign.Center,
                                    color = Color.Black, fontSize = 15.sp,
                                )
                            }
                        }
                    }
                )
            }

        }

        Row(
            modifier = Modifier.padding(all = 0.dp).height(75.dp),horizontalArrangement = Arrangement.Center
            //horizontalArrangement = Arrangement.Center
        ) {
            //ScreenComponentHeader(drugsDeliveryConsumerViewHeaderViewModel)
            var text by rememberSaveable { mutableStateOf("") }
            TextField(
                value = text,
                onValueChange = {
                    text = it
                    //articleViewModel.actualizarLista(it)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    //.background(Color.White)
                    //.background(Color(0xFF22475b))
                    .padding(12.dp),
                label = { Text("Buscar") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Email Icon"
                    )
                },
            )

        }

        Row(
            modifier = Modifier.padding(all = 2.dp),
            //horizontalArrangement = Arrangement.Center
        ) {
            //BodyPreList(preViewModel,drugsDeliveryConsumerViewHeaderViewModel)
            val listModifier = Modifier.fillMaxSize().background(Color.White).padding(10.dp)
            LazyColumn(modifier = listModifier) {
                //val recipeList2 =recipeViewModel.recipeModel
                //val recipeList =CheckList
                val articleList = assetList
                items(articleList) { recipe ->
                    Card(
                        modifier = Modifier.fillMaxWidth().padding(8.dp).clickable{},
                        elevation = 10.dp,
                        content = {
                            Column( horizontalAlignment = Alignment.Start,
                                modifier = Modifier.border(1.dp, Color.Gray, RectangleShape).fillMaxWidth().padding(20.dp)) {
                                Row(
                                    modifier = Modifier.padding(all = 6.dp),horizontalArrangement = Arrangement.Center
                                ){
                                    Text(
                                        text = recipe.name,
                                        textAlign = TextAlign.Start, color = Color.Black, fontSize = 15.sp)
                                }
                                Row(
                                    modifier = Modifier.padding(all = 6.dp),horizontalArrangement = Arrangement.Center
                                ){
                                    Text(
                                        text = "PLACA: "+recipe.placa,
                                        textAlign = TextAlign.Start, color = Color.Black, fontSize = 14.sp)
                                }
                            }
                        }
                    )
                }
            }
        }
    }
    */


    /*
    //drugsDeliveryConsumerViewHeaderViewModel.drugsDeliveryConsumerViewHeaderFromApiList
    //val drugsHeader = drugsDeliveryConsumerViewHeaderViewModel.getAnyDrugsDeliveryConsumerViewHeaderFromDatabase()

    articleViewModel.getAllFromApi()
    //var quantityToRestore by rememberSaveable {mutableStateOf("") }
    val mContext = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(2.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var text by rememberSaveable { mutableStateOf("") }

        /*
        //filtro

        TextField(
            value = text,
            onValueChange = {
                text = it
            },
            label = { Text("Buscar") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Email Icon"
                )
            },
        )

        */

        OutlinedTextField(
            value = text,
            modifier = Modifier
                .fillMaxWidth()
                //.background(Color.White)
                //.background(Color(0xFF22475b))
                .padding(12.dp),
            onValueChange = { newText ->
                text = newText
            },
            label = { Text(text = "Buscar") },
            placeholder = { Text(text = "") }
        )

        articleViewModel.updateFilteredArticleList(text)
        //Text(text="Aqui"+text)

        val listModifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(top= 15.dp)
            .align(Alignment.CenterHorizontally)
        LazyColumn(modifier = listModifier) {
            val recipeList2 =articleViewModel.articleList
            //val recipeList =CheckList
            //var dataList = mutableListOf(Consumible(0, 1,"",1,"UND","2023-08-08T00:48:12.104Z",0))
            /*
            var dataList = mutableListOf<Consumible>()
            val prefs = FastPrefs(mContext)
            prefs.set("key",dataList)
            //val value = prefs.get("key",dataList)
            //Text(text= "value= ${value.toString()}")
            val jsonList = Json.encodeToString(dataList)
            val jsont = jsonList
            val jsonArray = Json.decodeFromString<JsonArray>(jsont)
            //send json to the server
            drugsDeliveryConsumerViewHeaderViewModel.saveInventoryOutputInremoteServer(jsonArray)
            */
            val recipeList =articleViewModel.articleList
            items(recipeList) { recipe ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable { },
                    elevation = 10.dp,
                    content = {
                        Column(
                            //horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .border(1.dp, Color.Gray, RectangleShape)
                                .fillMaxWidth()
                                .padding(10.dp)) {
                            Row(
                                modifier = Modifier.padding(all = 5.dp),horizontalArrangement = Arrangement.Center
                            ) {
                                /*
                                Box(
                                    modifier = Modifier.fillMaxWidth().padding(1.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = recipe.articleDescription,
                                        textAlign = TextAlign.Center,
                                        color = Color.Black,
                                        fontSize = 18.sp
                                    )
                                }
                                */
                                Box(
                                ) {
                                    Text(text = recipe.articleDescription)
                                }
                            }
                            Row(
                                modifier = Modifier.padding(all = 5.dp),horizontalArrangement = Arrangement.Center
                            ) {
                                /*
                                Box(
                                    modifier = Modifier.fillMaxWidth().padding(1.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = recipe.articleDescription,
                                        textAlign = TextAlign.Center,
                                        color = Color.Black,
                                        fontSize = 18.sp
                                    )
                                }
                                */
                                Box(
                                ) {
                                    Text(text = ""+recipe.quantityToRestore)
                                }
                            }

                            /*
                            Row(
                            ) {
                                    OutlinedTextField(
                                        value = ""+recipe.quantityToRestore,
                                        onValueChange = {
                                            //loginViewModel.productDescription = it
                                            //email = loginViewModel.productDescription
                                            //if()
                                            if(it!=""
                                                //&&it.trim().isNullOrEmpty()&&it.toInt()!=null&&it.toInt()!=null&&it.trim()!=""
                                            ){
                                                //articleViewModel.updateQuantity(recipe.local_id.toInt(),it.trim().toInt())
                                                //screenArticleViewModel.getArticleByIdUseCase(recipe.local_id.toInt())
                                            }else
                                                Toast.makeText(mContext,"El dato ingresado debe ser un numero" , Toast.LENGTH_SHORT).show()
                                        },
                                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                        label = { androidx.compose.material3.Text("Cantidad a reponer:") },
                                        modifier = Modifier
                                            //.padding(start = 16.dp, end = 16.dp, top = 20.dp)
                                            .width(170.dp)
                                    )
                                    /*
                                    TextField(
                                        value = quantityToRestore,
                                        onValueChange = {
                                            //screenArticleViewModel.updateQuantity(it)
                                            //quantityToRestore = it
                                        },
                                    )
                                    //+" "+recipe.unitOfMeasure.toLowerCase()
                                     */
                            }
                            */

                            Row(
                                modifier = Modifier.padding(all = 5.dp),horizontalArrangement = Arrangement.Center
                            ) {
                                Box(

                                ) {
                                    Text(text = "Disponible en inventario: "+recipe.quantityAvailable.toInt().toString()+" "+recipe.unitOfMeasure.toLowerCase(), fontSize = 13.sp)
                                }
                            }
                        }
                    }
                )
            }
        }
    }
    */
}

