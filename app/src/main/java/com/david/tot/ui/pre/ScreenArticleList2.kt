package com.david.tot.ui.pre


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

import androidx.compose.runtime.*


@Composable
fun ScreenRecipeList2(navegarPantalla2: (String) -> Unit, articleViewModel: PreViewModel) {


    /*
    articleViewModel.getAllFromLocalDatabase()

    var quantityToRestore by rememberSaveable {mutableStateOf("") }
    val mContext = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //var text by rememberSaveable { mutableStateOf("") }
        Row(
            modifier = Modifier.padding(all = 12.dp),
            horizontalArrangement = Arrangement.Center
        ){
            /*
            Image(
                painter = rememberImagePainter("https://upload.wikimedia.org/wikipedia/commons/a/ae/SEPT_17TH_LOS_ANGELES_INTERNATIONAL_TRUCK_PHOTO_PATRICE_RAUNET_HOLLYWOOD.jpg"),
                contentDescription = null,
                Modifier
                    .height(200.dp)
            )
            */
        }
        /*
        TextField(
            value = text,
            onValueChange = {
                text = it
            },
            label = { Text("Label") }
        )
        */
        //recipeViewModel.updateRecipeList(text)
        //Text(text="Aqui"+text)


        val listModifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(10.dp)
            .align(Alignment.CenterHorizontally)
        LazyColumn(modifier = listModifier) {
            val recipeList2 =articleViewModel.preList
            //val recipeList =CheckList
            val recipeList =articleViewModel.preList
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
                                                articleViewModel.updateConsumedQuantity(recipe.local_id.toInt(),it.trim().toInt())
                                                //screenArticleViewModel.getArticleByIdUseCase(recipe.local_id.toInt())
                                            }else
                                                Toast.makeText(mContext,"El dato ingresado debe ser un numero" , Toast.LENGTH_SHORT).show()
                                        },
                                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                        label = { androidx.compose.material3.Text("Cantidad a reponer:") },
                                        modifier = Modifier
                                            //.padding(start = 16.dp, end = 16.dp, top = 20.dp)
                                            .width(180.dp)
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


fun castItToInt(it:String):Boolean{
    return it.trim().toInt()!=null
}