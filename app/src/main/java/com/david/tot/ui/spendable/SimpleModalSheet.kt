//@file:OptIn(ExperimentalSheetApi::class)

package com.david.tot.ui.spendable






import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material3.Slider

import androidx.compose.ui.text.toUpperCase
import com.david.tot.ui.RecipeViewModel
import com.david.tot.ui.spotable.SpotableViewModel
import eu.wewox.modalsheet.ExperimentalSheetApi


import eu.wewox.modalsheet.ModalSheet

@OptIn(ExperimentalSheetApi::class)
@Composable
fun SimpleModalSheet(recipeViewModel: SpotableViewModel, visible: Boolean, onVisibleChange: (Boolean) -> Unit) {
    ModalSheet(visible = visible, onVisibleChange = onVisibleChange) {
        Column(modifier = Modifier.fillMaxSize().padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
            ) {
            var kilo by remember { mutableStateOf(0f) }
            var kiloHash by remember {mutableStateOf(0f)}

            Row() {
                //Text(text="${title.toUpperCase()}",fontSize = 35.sp,fontWeight = FontWeight.ExtraBold)
            }
            Row(Modifier.height(100.dp),content={})
            Row() {
                Text(text="" + kilo.toInt()+". "+kiloHash.toInt()+" KILOS",fontSize = 25.sp,fontWeight = FontWeight.ExtraBold)
            }
            Row(Modifier.height(100.dp),content={})
            Row() {
                Slider( //modifier = Modifier.width(width = 400.dp).height(height = 1000.dp).rotate(degrees = -90f),
                    value = kilo,
                    steps = 39,
                    onValueChange = { sliderValue_ -> kilo = sliderValue_ },
                    onValueChangeFinished = {
                        // this is called when the user completed selecting the value
                        // TODO convertir a miligramos y guardar en la base de datos como miligramos para enviar
                        Log.e("MainActivity", "kilo Value = $kilo")
                        //saveProductToOrder(recipeViewModel,idProduct,kilo.toInt(),kiloHash.toInt())
                    },
                    valueRange = 0f..40f
                )
            }
            /*
            Row(Modifier.height(100.dp),content={})
            Row() {
                Slider( //modifier = Modifier.width(width = 400.dp).height(height = 1000.dp).rotate(degrees = -90f),
                    value = kiloHash,
                    steps = 8,
                    onValueChange = { sliderValue_ -> kiloHash = sliderValue_ },
                    onValueChangeFinished = {
                        // this is called when the user completed selecting the value
                        Log.d("MainActivity", "kiloHash Value = $kiloHash")
                        // TODO convertir a miligramos y guardar en la base de datos como miligramos para enviar
                        // TODO fix error with value 7, when round decimal 6.999... to int
                        //saveProductToOrder(recipeViewModel,idProduct,kilo.toInt(),kiloHash.toInt())
                    },
                    valueRange = 0f..9f
                )
            }
            */

            //KiloHash()
            Row(Modifier.height(100.dp),content={})
            Button(onClick = {
                onVisibleChange(false)
                //saveProductToOrder(recipeViewModel,idProduct,kilo.toInt())
            }) {
                Text(text = "Cerrar")
            }
            BackHandler(enabled = true){
                //saveProductToOrder(recipeViewModel,idProduct,kilo.toInt())
            }
        }
    }
}

fun saveProductToOrder(recipeViewModel:RecipeViewModel,idProduct:Int,requested_amount:Int){
    //val requested_amount:Int = (kilo*1000)+(kiloHash*100)
    //recipeViewModel.saveProductToOrder(idProduct,requested_amount,"")
}