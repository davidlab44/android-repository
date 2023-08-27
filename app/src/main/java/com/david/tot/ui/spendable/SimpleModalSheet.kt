//@file:OptIn(ExperimentalSheetApi::class)

package com.david.tot.ui.spendable






import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.lazy.items
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType

import com.david.tot.ui.RecipeViewModel
import com.david.tot.ui.spotable.SpotableViewModel
import eu.wewox.modalsheet.ExperimentalSheetApi


import eu.wewox.modalsheet.ModalSheet

@OptIn(ExperimentalSheetApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SimpleModalSheet(spotableViewModel: SpotableViewModel, spendableViewModel: SpendableViewModel, visible: Boolean, onVisibleChange: (Boolean) -> Unit) {
    ModalSheet(visible = visible, onVisibleChange = onVisibleChange) {
        Column(modifier = Modifier.height(500.dp) .padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
            ) {




















































            val mContext = LocalContext.current

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(2.dp), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                var text by rememberSaveable { mutableStateOf("") }
                val pattern = remember { Regex("^\\d+\$") }



                androidx.compose.material3.OutlinedTextField(
                    value = text,
                    modifier = Modifier
                        .fillMaxWidth()
                        //.background(Color.White)
                        //.background(Color(0xFF22475b))
                        .padding(12.dp),
                    onValueChange = { newText ->
                        text = newText
                    },
                    label = { androidx.compose.material3.Text(text = "Buscar") },
                    placeholder = { androidx.compose.material3.Text(text = "") }
                )

                spotableViewModel.retrieveFilteredSpotablesFromLocalDatabase(text)
                //Text(text="Aqui"+text)

                val listModifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(top= 15.dp)
                    .align(Alignment.CenterHorizontally)
                LazyColumn(modifier = listModifier) {
                    val recipeList2 =spotableViewModel.spotableList

                    //LIST
                    val spotableList =spotableViewModel.spotableList
                    items(spotableList) { article ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                                .clickable {
                                    spendableViewModel.spotable=article
                                    spendableViewModel.visibleBoolean=false
                                    spendableViewModel.visibleSpot=true
                                },
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
                                            androidx.compose.material3.Text(text = "Nombre: "+article.name)
                                        }
                                    }
                                    Row(
                                        modifier = Modifier.padding(all = 5.dp),horizontalArrangement = Arrangement.Center
                                    ) {
                                        Box(
                                        ) {
                                            androidx.compose.material3.Text(text = "Nit: "+article.nit)
                                        }
                                    }

                                    /*
                                    Row(
                                    ) {
                                        var newQuantity by rememberSaveable { mutableStateOf("") }
                                        androidx.compose.material3.OutlinedTextField(
                                            value = newQuantity,
                                            onValueChange = {cant->
                                                newQuantity=cant
                                                val cantCasted = cant.filter {it in '0'..'9'}
                                                article.consumedQuantity=cantCasted.toInt()
                                            },
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
                                            androidx.compose.material3.Text(text = "Disponible: "+article.quantityAvailable.toInt().toString()+" "+article.unitOfMeasure.toLowerCase(), fontSize = 13.sp)
                                        }
                                    }


                                    Row(
                                        modifier = Modifier.padding(all = 5.dp),horizontalArrangement = Arrangement.Center
                                    ) {
                                        Box(

                                        ) {
                                            androidx.compose.material3.Text(text = "Reponer: "+article.quantityToStock.toInt().toString(), fontSize = 13.sp)
                                        }
                                    }
                                    */

                                }
                            }
                        )
                    }
                }
            }





























            Button(onClick = {
                spendableViewModel.visibleBoolean=false
                //onVisibleChange(false)
                //saveProductToOrder(recipeViewModel,idProduct,kilo.toInt())
            }) {
                Text(text = "Cerrar")
            }
            BackHandler(enabled = true){
                //saveProductToOrder(recipeViewModel,idProduct,kilo.toInt())
                spendableViewModel.visibleBoolean=false
            }
        }
    }
}

fun saveProductToOrder(recipeViewModel:RecipeViewModel,idProduct:Int,requested_amount:Int){
    //val requested_amount:Int = (kilo*1000)+(kiloHash*100)
    //recipeViewModel.saveProductToOrder(idProduct,requested_amount,"")
}