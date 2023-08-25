package com.david.tot.util

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
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter.State.Empty.painter
import com.david.tot.R
import com.yeslab.fastprefs.FastPrefs

@Composable
fun DisplayLastPhoto(){
    val bitmap =  remember { mutableStateOf<Bitmap?>(null) }
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val context = LocalContext.current

    //SHARED PREFERENCES
    val prefs = FastPrefs(context)
    //TODO todogl cambiar esto por una imagen por defecto
    //prefs.setString("Reportable","defaultValue")
    val value = prefs.getString("photoUrl","defaultValue")
    prefs.remove("photoUrl")
    Log.e("TG","value: "+value)
    if(value=="defaultValue"){
        Column(
            //horizontalAlignment = Alignment.CenterHorizontally,
            //modifier = Modifier.padding(2.dp)
        ) {
            Row(
                //modifier = Modifier.padding(all = 2.dp),
                //horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.image_fill0_wght400_grad0_opsz48),
                    contentDescription =null,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
        return
    }
    imageUri= Uri.parse("file://"+value)
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
            Column(
                //horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(top=24.dp, bottom=20.dp)) {
                Row(
                    //modifier = Modifier.padding(all = 2.dp),
                    //horizontalArrangement = Arrangement.Center
                ) {
                    Image(bitmap = btm.asImageBitmap(),
                        contentDescription =null,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}