package com.david.tot.ui.reportable

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.david.tot.util.ExpandingTextFinal
import com.yeslab.fastprefs.FastPrefs

@Composable
fun BodyReportableList(contextActivity:ReportableActivity, reportableViewModel: ReportableViewModel) {

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //var text by rememberSaveable { mutableStateOf("") }
        Row(
            modifier = Modifier.padding(all = 2.dp).height(350.dp),
            horizontalArrangement = Arrangement.Center
        ){
            val bitmap =  remember {mutableStateOf<Bitmap?>(null)}
            var imageUri by remember {mutableStateOf<Uri?>(null)}
            val context = LocalContext.current

            //SHARED PREFERENCES
            val prefs = FastPrefs(context)
            //TODO todogl cambiar esto por una imagen por defecto
            //prefs.setString("Reportable","defaultValue")
            val value = prefs.getString("Reportable","defaultValue")
            Log.e("TG","value: "+value)
            imageUri=Uri.parse("file://"+value)
            imageUri?.let {
                if (Build.VERSION.SDK_INT < 28) {
                    bitmap.value = MediaStore.Images
                        .Media.getBitmap(context.contentResolver,it)
                } else {
                    val source = ImageDecoder
                        .createSource(context.contentResolver,it)
                    bitmap.value = ImageDecoder.decodeBitmap(source)
                }

                bitmap.value?.let {  btm ->
                    Column( horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(2.dp)) {
                        Row(
                            modifier = Modifier.padding(all = 2.dp),horizontalArrangement = Arrangement.Center
                        ) {
                            Image(bitmap = btm.asImageBitmap(),
                                contentDescription =null,
                                modifier = Modifier.size(300.dp)
                            )
                        }
                        Row(
                            modifier = Modifier.padding(all = 12.dp),horizontalArrangement = Arrangement.Center
                        ) {
                            /*
                            Button(enabled = enabledImage, modifier = Modifier.padding(1.dp),
                                onClick = {
                                    enabledImage = false
                                    bitmap.value?.let {
                                        //updateProductViewModel.updateProductImage(updateProductViewModel.productRemoteId.toInt(), it)
                                        //updateProductViewModel.updateProductImage(1, it)
                                } }) {
                                Text(text = "ENVIAR IMAGEN")
                            }

                             */
                            //Text(text = "ACTIVITY PARA ENVIAR NOVEDADES CON SU FOTO")
                        }
                    }
                }
            }
        }



        Row(
            modifier = Modifier.padding(all = 2.dp).height(200.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            ExpandingTextFinal()
            /*
            Button(
                enabled = true,
                modifier = Modifier.padding(1.dp),
                onClick = {
                    /*Do something*/
                }) {
                Text(text = "Camara")
            }
            */
        }
    }

}




