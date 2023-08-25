package com.david.tot.ui.confirmable

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.graphics.Paint.Align
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Sync
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.david.tot.R
import com.david.tot.ui.theme.TotTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import com.david.tot.ui.consumible.*
import com.david.tot.ui.cameraxtutorial.Main2Activity

import com.david.tot.ui.pre.PreActivity
import com.david.tot.ui.reloadable.ReloadableActivity
import com.david.tot.ui.settings.SettingsActivity
import com.david.tot.ui.sync.SyncActivity

import androidx.compose.foundation.layout.*
import androidx.compose.material.*

import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import com.david.tot.ui.authenticable.AuthenticableViewModel


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement.Absolute.Center
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.filled.Save
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.text.style.TextAlign
import coil.compose.AsyncImagePainter.State.Empty.painter
import com.david.tot.domain.model.Confirmable
import com.david.tot.util.ConfirmableList
import com.david.tot.util.Dates
import com.david.tot.util.DisplayLastPhoto
import com.yeslab.fastprefs.FastPrefs

@Composable
fun BodyConfirmableList(contextActivity:ConfirmableActivity, confirmableViewModel: ConfirmableViewModel, authenticableViewModel: AuthenticableViewModel) {
    val mContext = LocalContext.current
    confirmableViewModel.getAllConfirmablesFromLocalDatabase(mContext)
    var value by remember { mutableStateOf("") }

    if(confirmableViewModel.toastSuccess){
        Toast.makeText(mContext,"Confirmable creado exitosamente!", Toast.LENGTH_LONG).show()
        contextActivity.finish()
    }
    if(confirmableViewModel.toastNotInternetConnection)
        Toast.makeText(mContext,"No hay conexión a internet", Toast.LENGTH_LONG).show()

    if(confirmableViewModel.toastConfirmationSuccess)
        Toast.makeText(mContext,"Confirmación exitosa", Toast.LENGTH_LONG).show()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(2.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var text by rememberSaveable { mutableStateOf("") }
        val pattern = remember { Regex("^\\d+\$") }

        val listModifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(top = 15.dp)
            .align(Alignment.CenterHorizontally)
        LazyColumn(modifier = listModifier) {

            //LIST
            //val confirmableList =confirmableViewModel.confirmableList
            val confirmableList = ConfirmableList
            items(confirmableList) { confirmable ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
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
                                    Text(text = "CONFIRMACIÓN DE SOLICITUD: "+confirmable.consecutive,fontSize = 20.sp,
                                        fontWeight = FontWeight.Bold )
                                }
                            }

                            Row(
                                modifier = Modifier.padding(all = 5.dp),horizontalArrangement = Arrangement.Center
                            ) {
                                Box(
                                ) {
                                    Text(text = "RestockId: "+confirmable.restockID)
                                }
                            }

                            Row(
                                modifier = Modifier.padding(all = 5.dp),horizontalArrangement = Arrangement.Center
                            ) {
                                Box(
                                ) {
                                    Text(text = "Fecha de creación de la solicitud: "+confirmable.creationDate)
                                }
                            }

                            Row(
                                modifier = Modifier.padding(all = 5.dp),horizontalArrangement = Arrangement.Center
                            ) {
                                Box(
                                ) {
                                    Text(text = "Nombre Solicitante: "+confirmable.restockerDisplayName)
                                }
                            }

                            Row(
                                modifier = Modifier.padding(all = 5.dp),horizontalArrangement = Arrangement.Center
                            ) {
                                Box(
                                ) {
                                    Text(text = "Vehiculo: "+confirmable.vehicle)
                                }
                            }

                            Row(
                                modifier = Modifier.padding(all = 5.dp),horizontalArrangement = Arrangement.Center
                            ) {
                                Box(

                                ) {
                                    Text(text = "STATUS: "+confirmable.status, fontSize = 15.sp)
                                }
                            }

                            Row(
                                //modifier = Modifier.padding(all = 0.dp),
                                horizontalArrangement = Arrangement.Center,
                                modifier = Modifier.fillMaxSize().padding(top=10.dp),
                            ) {
                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier.fillMaxSize(),
                                ) {
                                    OutlinedTextField(
                                        value = value,
                                        onValueChange = {
                                            value = it
                                        },
                                        label = { androidx.compose.material.Text("Comentarios: ") },
                                        modifier = Modifier
                                            //.padding(1.dp)
                                            .height(200.dp).align(Alignment.Center),
                                        singleLine= false,
                                        maxLines = 10
                                    )
                                }
                            }

                            Row(
                                modifier = Modifier
                                    //.padding(all = 1.dp)
                                    .height(330.dp).clickable(onClick = {
                                        val prefs = FastPrefs(contextActivity)
                                        prefs.setString("caller","ConfirmableActivity")
                                        contextActivity.startActivity(Intent(contextActivity, Main2Activity::class.java))
                                    }),
                                //horizontalArrangement = Arrangement.Center
                            ){
                                DisplayLastPhoto()
                                /*
                                val bitmap =  remember {mutableStateOf<Bitmap?>(null)}
                                var imageUri by remember {mutableStateOf<Uri?>(null)}
                                val context = LocalContext.current
                                //SHARED PREFERENCES
                                val prefs = FastPrefs(context)
                                //TODO todogl cambiar esto por una imagen por defecto
                                //prefs.setString("Reportable","defaultValue")
                                val value = prefs.getString("photoUrl","defaultValue")
                                Log.e("TG","value: "+value)
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
                                        }
                                    }
                                }
                                */
                            }
                        }
                    }
                )
            }
        }
    }

}