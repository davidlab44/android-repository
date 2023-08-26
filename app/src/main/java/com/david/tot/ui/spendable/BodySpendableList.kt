package com.david.tot.ui.spendable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.style.TextAlign
import com.david.tot.ui.cameraxtutorial.Main2Activity
import com.david.tot.ui.spotable.SpotableViewModel
import com.david.tot.util.DisplayLastPhoto
import com.yeslab.fastprefs.FastPrefs

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BodySpendableList(contextActivity:SpendableActivity, spendableViewModel: SpendableViewModel,spotableViewModel: SpotableViewModel) {

    var username: String by remember { mutableStateOf("") }
    var visibleInt by rememberSaveable { mutableStateOf(0)}


    if(spendableViewModel.visibleBoolean)
    SimpleModalSheet(
        spotableViewModel = spotableViewModel,
        spendableViewModel=spendableViewModel,
        visible = true,
        onVisibleChange = { visibleInt = 0 }
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        /*
        Row(
            Modifier.padding(12.dp)
        ) {
            /*
            Icon(
                Icons.Default.Home,
                contentDescription = null
            )
            */
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "ESTABLECIMIENTO: ",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
        }
        */

        Row(
            Modifier.padding(bottom=22.dp)
        ) {

            Button(
                onClick = {
                    spendableViewModel.visibleBoolean=true
                },
                modifier = Modifier
                    .width(200.dp)
                    .shadow(0.dp),
                shape = RoundedCornerShape(28.dp),
                contentPadding = PaddingValues(15.dp),
                colors = ButtonDefaults.buttonColors(Color(0xFFDDDDDD))
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.CenterStart)
                    ) {
                        /*
                        Spacer(modifier = Modifier.width(4.dp))
                        Icon(
                            Icons.Default.Home,
                            modifier = Modifier
                                .size(18.dp),
                            contentDescription = "drawable_icons",
                            tint = Color.White
                        )
                        */
                    }
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = "ESTABLECIMIENTO: ",
                        color = Color.Black,
                        //color = MaterialTheme.colors.loginButtonTextColor,
                        textAlign = TextAlign.Center,
                        fontSize = 16.sp,

                        )
                }
            }

        }

        //var text by rememberSaveable { mutableStateOf("") }
        Row(
        ) {
            var newQuantity by rememberSaveable { mutableStateOf("") }
            androidx.compose.material3.OutlinedTextField(
                value = newQuantity,
                onValueChange = { cant ->
                    newQuantity = cant
                    val cantCasted = cant.filter { it in '0'..'9' }
                    //article.consumedQuantity = cantCasted.toInt()
                },
                //keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                ),
                label = { androidx.compose.material3.Text("Valor:") },
                modifier = Modifier
                    //.padding(start = 16.dp, end = 16.dp, top = 20.dp)
                    .width(200.dp)
            )
        }

        Row(
            modifier = Modifier
                //.padding(all = 1.dp)
                .height(330.dp)
                .clickable(onClick = {
                    val prefs = FastPrefs(contextActivity)
                    prefs.setString("caller", "SpendableActivity")
                    contextActivity.startActivity(
                        Intent(
                            contextActivity,
                            Main2Activity::class.java
                        )
                    )
                }),
            //horizontalArrangement = Arrangement.Center
        ){
            DisplayLastPhoto()
        }



    }

}




@Composable
fun GoogleButton() {

}


