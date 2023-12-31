package com.david.tot.ui.consumible

import android.widget.Toast
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
import androidx.compose.material3.ExperimentalMaterial3Api

import androidx.compose.runtime.*
import com.david.tot.ui.authenticable.AuthenticableViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BodyArticleList(contextActivity:ArticleActivity, consumibleViewModel: ConsumibleViewModel, authenticableViewModel: AuthenticableViewModel) {

    val mContext = LocalContext.current
    if(consumibleViewModel.toastSuccess){
        Toast.makeText(mContext,"Consumible creado exitosamente!", Toast.LENGTH_LONG).show()
        contextActivity.finish()
    }
    if(consumibleViewModel.toastNotEnoughtConsumibles)
        Toast.makeText(mContext,"No hay suficiente cantidad de inventario "+consumibleViewModel.notEnoughtConsumibleName, Toast.LENGTH_LONG).show()

    //drugsDeliveryConsumerViewHeaderViewModel.drugsDeliveryConsumerViewHeaderFromApiList
    //val drugsHeader = drugsDeliveryConsumerViewHeaderViewModel.getAnyDrugsDeliveryConsumerViewHeaderFromDatabase()
    //articleViewModel.getAllFromLocalDatabase()
    //var quantityToRestore by rememberSaveable {mutableStateOf("") }
    consumibleViewModel.getAllConsumiblesFromLocalDatabase()
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

        consumibleViewModel.updateFilteredArticleList(text)
        //Text(text="Aqui"+text)


        val listModifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(top = 15.dp)
            .align(Alignment.CenterHorizontally)
        LazyColumn(modifier = listModifier) {
            val recipeList2 =consumibleViewModel.articleList
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
            val articleList =consumibleViewModel.articleList
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
                                    Text(text = ""+article.articleDescription)
                                }
                            }

                            Row(
                                modifier = Modifier.padding(all = 5.dp),horizontalArrangement = Arrangement.Center
                            ) {
                                Column(modifier=Modifier.weight(.5f)/*.border(1.dp, Color.Gray, RectangleShape)*/) {
                                    Row(
                                        modifier = Modifier.padding(all = 0.dp),
                                        horizontalArrangement = Arrangement.Center
                                    ) {
                                        Box() {
                                            var newQuantity by rememberSaveable { mutableStateOf("") }
                                            OutlinedTextField(
                                                value = newQuantity,
                                                onValueChange = { cant ->
                                                    newQuantity = cant
                                                    val cantCasted = cant.filter { it in '0'..'9' }
                                                    article.consumedQuantity = cantCasted.toInt()
                                                },
                                                //keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                                keyboardOptions = KeyboardOptions.Default.copy(
                                                    keyboardType = KeyboardType.NumberPassword
                                                ),
                                                label = { androidx.compose.material3.Text("Cantidad") },
                                                modifier = Modifier
                                                    //.padding(start = 16.dp, end = 16.dp, top = 20.dp)
                                                    .width(150.dp)
                                            )
                                        }
                                    }
                                    Row(
                                        modifier = Modifier.padding(all = 5.dp),
                                        horizontalArrangement = Arrangement.Center
                                    ) {
                                        Box(

                                        ) {
                                            Text(
                                                text = "Disponible: " + article.quantityAvailable.toInt()
                                                    .toString() + " " + article.unitOfMeasure.toLowerCase(),
                                                fontSize = 13.sp
                                            )
                                        }
                                    }
                                    Row(
                                        modifier = Modifier.padding(all = 5.dp),
                                        horizontalArrangement = Arrangement.Center
                                    ) {
                                        Box(

                                        ) {
                                            Text(
                                                text = "Reponer: " + article.quantityToStock.toInt()
                                                    .toString(), fontSize = 13.sp
                                            )
                                        }
                                    }
                                }

                                Column(modifier=Modifier.weight(.5f).height(130.dp)/*.border(1.dp, Color.Gray, RectangleShape)*/) {
                                    var newNote by rememberSaveable { mutableStateOf("") }
                                    if(article.isControlled) {
                                        OutlinedTextField(
                                            value = newNote,
                                            onValueChange = { note ->
                                                if(note.length>200){
                                                    newNote = note.substring(0, 200)
                                                }else{
                                                    newNote= note
                                                }
                                                article.notes = newNote.trim()
                                            },
                                            label = { androidx.compose.material3.Text("Nota") },
                                            modifier = Modifier
                                                //.padding(start = 16.dp, end = 16.dp, top = 20.dp)
                                                .fillMaxSize(),
                                            singleLine= false,
                                            maxLines = 8
                                        )
                                    }
                                }
                            }
                        }
                    }
                )
            }
        }
    }
}

