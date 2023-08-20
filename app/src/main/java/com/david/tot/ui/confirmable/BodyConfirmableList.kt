package com.david.tot.ui.confirmable

import androidx.compose.foundation.layout.*
import androidx.compose.material.*

import androidx.compose.runtime.*
import com.david.tot.ui.authenticable.AuthenticableViewModel

@Composable
fun BodyConfirmableList(contextActivity:ConfirmableActivity, confirmableViewModel: ConfirmableViewModel, authenticableViewModel: AuthenticableViewModel) {

    /*
    val mContext = LocalContext.current
    if(confirmableViewModel.toastSuccess){
        Toast.makeText(mContext,"Confirmable creado exitosamente!", Toast.LENGTH_LONG).show()
        contextActivity.finish()
    }
    //drugsDeliveryConsumerViewHeaderViewModel.drugsDeliveryConsumerViewHeaderFromApiList
    //val drugsHeader = drugsDeliveryConsumerViewHeaderViewModel.getAnyDrugsDeliveryConsumerViewHeaderFromDatabase()
    //articleViewModel.getAllFromLocalDatabase()
    //var quantityToRestore by rememberSaveable {mutableStateOf("") }
    confirmableViewModel.getAllConfirmablesFromLocalDatabase()
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

        confirmableViewModel.updateFilteredConfirmableList(text)
        //Text(text="Aqui"+text)


        val listModifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(top= 15.dp)
            .align(Alignment.CenterHorizontally)
        LazyColumn(modifier = listModifier) {
            val recipeList2 =confirmableViewModel.articleList
            //val recipeList =CheckList
            //var dataList = mutableListOf(Confirmable(0, 1,"",1,"UND","2023-08-08T00:48:12.104Z",0))
            /*
            var dataList = mutableListOf<Confirmable>()
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
            val articleList =confirmableViewModel.articleList
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
                                    Text(text = "Descripción: "+article.articleDescription)
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
                                    Text(text = "Inventario: "+article.quantityAvailable.toInt().toString()+" "+article.unitOfMeasure.toLowerCase(), fontSize = 13.sp)
                                }
                            }

                            Row(
                                modifier = Modifier.padding(all = 5.dp),horizontalArrangement = Arrangement.Center
                            ) {
                                Box(

                                ) {
                                    Text(text = "Disponible: "+article.quantityToStock.toInt().toString()+" "+article.unitOfMeasure.toLowerCase()+" / Reponer: "+article.quantityToRestore.toInt().toString(), fontSize = 13.sp)
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

