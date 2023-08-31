package com.david.tot.ui.pre

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
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
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.style.TextAlign
import com.david.tot.util.preList

@Composable
//fun BodyPreList(preViewModel: PreViewModel, drugsDeliveryConsumerViewHeaderViewModel: DrugsDeliveryConsumerViewHeaderViewModel) {
fun BodyPreList() {
    //drugsDeliveryConsumerViewHeaderViewModel.drugsDeliveryConsumerViewHeaderFromApiList
    //val drugsHeader = drugsDeliveryConsumerViewHeaderViewModel.getAnyDrugsDeliveryConsumerViewHeaderFromDatabase()

    //preViewModel.getAllFromLocalDatabase()
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

        /*
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

         */

        //preViewModel.updateFilteredArticleList(text)
        //Text(text="Aqui"+text)















        val listModifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(10.dp)
            .align(Alignment.CenterHorizontally)
        LazyColumn(modifier = listModifier) {
            //val recipeList2 =recipeViewModel.recipeModel
            //val recipeList =CheckList

            val articleList = preList
            items(articleList) { recipe ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable {},
                    elevation = 10.dp,
                    content = {
                        Column( horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .border(1.dp, Color.Gray, RectangleShape)
                                .fillMaxWidth()
                                .padding(20.dp)) {
                            Row(
                                modifier = Modifier.padding(all = 1.dp),horizontalArrangement = Arrangement.Center
                            ){
                                Text(
                                    text = recipe.name,
                                    textAlign = TextAlign.Center, color = Color.Black, fontSize = 20.sp)
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(1.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    // Reemplaza por el ejemplo que deseas ver
                                }
                            }

                            Row(
                                modifier = Modifier.padding(all = 5.dp),horizontalArrangement = Arrangement.Center
                            ){
                                Box(
                                    modifier = Modifier
                                        .width(120.dp)
                                        .padding(1.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    // Reemplaza por el ejemplo que deseas ver
                                    val checked = remember { mutableStateOf(false) }
                                    Checkbox(
                                        checked = checked.value,
                                        onCheckedChange = { checked.value = it },
                                        modifier=Modifier.width(40.dp)

                                    )
                                    Text(text = " \u0020 \u0020 \u0020   \u0020 \u0020 \u0020 \u0020 \u0020     NO")
                                }
                                Box(
                                    modifier = Modifier
                                        .width(120.dp)
                                        .padding(1.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    // Reemplaza por el ejemplo que deseas ver
                                    val checked2 = remember { mutableStateOf(false) }
                                    Checkbox(
                                        checked = checked2.value,
                                        onCheckedChange = { checked2.value = it },
                                        modifier=Modifier.width(40.dp)
                                    )
                                    Text(text = " \u0020 \u0020 \u0020 \u0020 \u0020 \u0020 \u0020 \u0020 \u0020  SI")

                                }
                            }

                        }
                    }
                )
            }
        }






        /*
        val listModifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(top= 15.dp)
            .align(Alignment.CenterHorizontally)
        LazyColumn(modifier = listModifier) {

            val articleList = preList
            items(articleList) { pre ->
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
                                    Text(text = ""+pre.name)
                                }
                            }
                        }
                    }
                )
            }
        }

        */






    }
}

