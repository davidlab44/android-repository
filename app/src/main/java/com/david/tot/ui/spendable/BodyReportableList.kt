package com.david.tot.ui.spendable

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.david.tot.ui.cameraxtutorial.Main2Activity
import com.david.tot.util.Dates
import com.david.tot.util.DisplayLastPhoto
import com.david.tot.util.ExpandingTextFinal
import com.yeslab.fastprefs.FastPrefs

@Composable
fun BodySpendableList(contextActivity:SpendableActivity, spendableViewModel: SpendableViewModel) {

    Column(
        modifier = Modifier.fillMaxSize().padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
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
                label = { androidx.compose.material3.Text("Valor") },
                modifier = Modifier
                    //.padding(start = 16.dp, end = 16.dp, top = 20.dp)
                    .width(170.dp)
            )

        }

        Row(
            modifier = Modifier
                //.padding(all = 1.dp)
                .height(330.dp).clickable(onClick = {
                    val prefs = FastPrefs(contextActivity)
                    prefs.setString("caller","SpendableActivity")
                    contextActivity.startActivity(Intent(contextActivity, Main2Activity::class.java))
                }),
            //horizontalArrangement = Arrangement.Center
        ){
            DisplayLastPhoto()
        }


        Row(
            modifier = Modifier.padding(all = 1.dp).height(250.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            ExpandingTextFinal()
        }
    }

}




