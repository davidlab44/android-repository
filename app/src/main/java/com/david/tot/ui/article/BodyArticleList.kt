package com.david.tot.ui.article

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.compose.foundation.layout.width

import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

import androidx.compose.runtime.*
import androidx.compose.ui.focus.onFocusEvent
import com.david.tot.ui.drugs_delivery_consumer_view_header.DrugsDeliveryConsumerViewHeaderViewModel

@Composable
fun BodyArticleList(articleViewModel: ArticleViewModel, drugsDeliveryConsumerViewHeaderViewModel: DrugsDeliveryConsumerViewHeaderViewModel) {

    //drugsDeliveryConsumerViewHeaderViewModel.drugsDeliveryConsumerViewHeaderFromApiList
    //val drugsHeader = drugsDeliveryConsumerViewHeaderViewModel.getAnyDrugsDeliveryConsumerViewHeaderFromDatabase()

    articleViewModel.onCreate()
    //var quantityToRestore by rememberSaveable {mutableStateOf("") }
    val mContext = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(2.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var text by rememberSaveable { mutableStateOf("") }
        val pattern = remember { Regex("^\\d+\$") }

        /*
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

            //LIST
            val articleList =articleViewModel.articleList
            items(articleList) { article ->
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
                                    Text(text = "Local_id: "+article.local_id+" Descripcion: "+article.articleDescription)
                                }
                            }

                            Row(
                            ) {

                                /*
                                Text("Cantidad")

                                var txt =""
                                val pattern = remember { Regex("^\\d+\$") }

                                TextField(
                                    value = txt,
                                    onValueChange = {
                                        if (it.isEmpty() || it.matches(pattern)) {
                                            txt = it
                                        }
                                    },
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                                )

                                 */

                                /*
                                //val pattern = remember { Regex("^\\d+\$") }
                                val pattern = remember { Regex("0-9") }

                                var requiredQuantity =0
                                TextField(
                                    value = ""+requiredQuantity,
                                    onValueChange = { text ->
                                        requiredQuantity = text.toInt()

                                        if(text.matches(Regex("^[0-9]*\$"))){
                                            Toast.makeText(mContext,"match!", Toast.LENGTH_LONG).show()
                                        }else{
                                            Toast.makeText(mContext,"no", Toast.LENGTH_LONG).show()
                                        }
                                    },
                                    keyboardOptions = KeyboardOptions.Default.copy(
                                        keyboardType = KeyboardType.NumberPassword
                                    ),
                                    visualTransformation = VisualTransformation.None
                                )
                                */

                                var newQuantity by rememberSaveable { mutableStateOf("") }
                                OutlinedTextField(
                                    value = newQuantity,
                                    onValueChange = {
                                        newQuantity=it
                                        article.consumedQuantity=it.toInt()
                                    },
                                    /*
                                    onValueChange = {
                                        val requiredQuantity = it.trim().toInt()
                                        if(it.matches(pattern) && it.isNotBlank() && it.isNotEmpty() && it.toInt()!=null){

                                            if(requiredQuantity>0 && requiredQuantity<=article.quantityAvailable.toInt()){
                                                articleViewModel.updateConsumedQuantity(article.local_id.toInt(),requiredQuantity)
                                                articleViewModel.updateFilteredArticleList("")
                                            }else{
                                                Toast.makeText(mContext,"No hay suficiente cantidad en inventario ", Toast.LENGTH_SHORT).show()
                                            }
                                        }else{
                                            Toast.makeText(mContext,"La cantidad ingresada debe ser un NUMERO ENTERO sin puntos ni espacios"+it.trim().toInt(), Toast.LENGTH_LONG).show()
                                        }
                                    },
                                    */
                                    //keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                    keyboardOptions = KeyboardOptions.Default.copy(
                                        keyboardType = KeyboardType.NumberPassword
                                    ),
                                    label = { androidx.compose.material3.Text("Cantidad") },
                                    modifier = Modifier
                                        //.padding(start = 16.dp, end = 16.dp, top = 20.dp)
                                        .width(170.dp)
                                )

                            }
                            Row(
                                modifier = Modifier.padding(all = 5.dp),horizontalArrangement = Arrangement.Center
                            ) {
                                Box(

                                ) {
                                    Text(text = "Disponible en inventario: "+article.quantityAvailable.toInt().toString()+" "+article.unitOfMeasure.toLowerCase(), fontSize = 13.sp)
                                }
                            }
                            Row(
                                modifier = Modifier.padding(all = 5.dp),horizontalArrangement = Arrangement.Center
                            ) {
                                Box(

                                ) {
                                    Text(text = "nueva cantidad: "+article.consumedQuantity.toInt().toString()+" "+article.unitOfMeasure.toLowerCase(), fontSize = 13.sp)
                                }
                            }
                        }
                    }
                )
            }
        }
    }
}

