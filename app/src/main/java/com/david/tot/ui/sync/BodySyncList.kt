package com.david.tot.ui.sync

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
import androidx.compose.ui.text.font.FontWeight
import com.david.tot.ui.drugs_delivery_consumer_view_header.DrugsDeliveryConsumerViewHeaderViewModel

@Composable
fun BodySyncList(syncViewModel: SyncViewModel) {

    syncViewModel.onCreate()
    //var quantityToRestore by rememberSaveable {mutableStateOf("") }
    val mContext = LocalContext.current
    val articleList =syncViewModel.articleList
    val size = articleList.size
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(2.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var text by rememberSaveable { mutableStateOf("") }
        val pattern = remember { Regex("^\\d+\$") }

        Box(){
           Card(
               modifier = Modifier.fillMaxWidth().padding(8.dp).clickable{},
               elevation = 10.dp,
               content = {
                   Column( horizontalAlignment = Alignment.CenterHorizontally,
                       modifier = Modifier.border(1.dp, Color.Gray, RectangleShape).fillMaxWidth().padding(20.dp)) {
                       Row(
                           modifier = Modifier.padding(all = 12.dp),horizontalArrangement = Arrangement.Center
                       ){
                           Text(
                               text = "ELEMENTOS PENDIENTES: "+size,
                               color = Color.Black, fontSize = 15.sp, fontWeight = FontWeight.Black)
                       }
                   }
               }
           )
       }

        val listModifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(top = 15.dp)
            .align(Alignment.CenterHorizontally)
        LazyColumn(modifier = listModifier) {
            //LIST

            items(articleList) { sync ->
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
                                Box(
                                ) {
                                    Text(text = "Local_id: "+sync.local_id)
                                }
                            }

                            Row(
                                modifier = Modifier.padding(all = 5.dp),horizontalArrangement = Arrangement.Center
                            ) {
                                Box(

                                ) {
                                    Text(text = "key: "+sync.objectId)
                                }
                            }
                            Row(
                                modifier = Modifier.padding(all = 5.dp),horizontalArrangement = Arrangement.Center
                            ) {
                                Box(

                                ) {
                                    Text(text = "tipo: "+sync.objectType)
                                }
                            }
                            Row(
                                modifier = Modifier.padding(all = 5.dp),horizontalArrangement = Arrangement.Center
                            ) {
                                Box(

                                ) {
                                    Text(text = "Fecha de creacion: "+sync.createdAt)
                                }
                            }
                        }
                    }
                )
            }
        }
    }

    /*
    syncViewModel.onCreate()
    //var quantityToRestore by rememberSaveable {mutableStateOf("") }
    val mContext = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(2.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var text by rememberSaveable { mutableStateOf("") }
        val pattern = remember { Regex("^\\d+\$") }

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

        //syncViewModel.updateFilteredArticleList(text)
        //Text(text="Aqui"+text)


        val listModifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(top= 15.dp)
            .align(Alignment.CenterHorizontally)
        LazyColumn(modifier = listModifier) {
            val recipeList2 =syncViewModel.articleList
            //val recipeList =CheckList
            //var dataList = mutableListOf(Consumible(0, 1,"",1,"UND","2023-08-08T00:48:12.104Z",0))
            //LIST
            val articleList =syncViewModel.articleList
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
                                Box(
                                ) {
                                    Text(text = "Local_id: "+article.local_id+" Descripcion: "+article.articleDescription)
                                }
                            }

                            Row(
                            ) {
                                var newQuantity by rememberSaveable { mutableStateOf("") }
                                OutlinedTextField(
                                    value = newQuantity,
                                    onValueChange = {
                                        newQuantity=it
                                        article.consumedQuantity=it.toInt()
                                    },
                                    keyboardOptions = KeyboardOptions.Default.copy(
                                        keyboardType = KeyboardType.NumberPassword
                                    ),
                                    label = { androidx.compose.material3.Text("Cantidad a reponer:") },
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

     */
}