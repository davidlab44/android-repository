package com.david.tot.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

import androidx.compose.runtime.*
import com.david.tot.ui.article.ArticleViewModel
import com.david.tot.ui.drugs_delivery_consumer_view_header.DrugsDeliveryConsumerViewHeaderViewModel

@Composable
fun BodyList(articleViewModel: ArticleViewModel, drugsDeliveryConsumerViewHeaderViewModel: DrugsDeliveryConsumerViewHeaderViewModel) {

    Log.e("","algo")


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

