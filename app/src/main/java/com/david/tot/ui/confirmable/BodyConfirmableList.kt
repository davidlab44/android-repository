package com.david.tot.ui.confirmable


import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
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
        Toast.makeText(mContext,"No hay conexion a internet", Toast.LENGTH_LONG).show()

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
            .padding(top= 15.dp)
            .align(Alignment.CenterHorizontally)
        LazyColumn(modifier = listModifier) {

            //LIST
            val confirmableList =confirmableViewModel.confirmableList
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
                                    Text(text = "Fecha de Creación de la solicitud: "+confirmable.creationDate)
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
                                modifier = Modifier.padding(all = 0.dp),horizontalArrangement = Arrangement.Center
                            ) {
                                Box(

                                ) {
                                    OutlinedTextField(
                                        value = value,
                                        onValueChange = {
                                            value = it
                                        },
                                        label = { androidx.compose.material.Text("Comentarios: ") },
                                        modifier = Modifier.padding(20.dp).height(200.dp),
                                        singleLine= false,
                                        maxLines = 10
                                    )
                                }
                            }

                            Row(
                                modifier = Modifier.padding(start = 18.dp),horizontalArrangement = Center
                            ) {
                                Box(

                                ) {
                                    androidx.compose.material3.Button(
                                        onClick = {

                                        },
                                        modifier = Modifier
                                            .padding(bottom = 10.dp)
                                            .height(60.dp)
                                    ) {
                                        androidx.compose.material3.Text("CONFIRMAR")
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