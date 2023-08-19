package com.david.tot.ui.pre

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.compose.foundation.clickable

import androidx.compose.runtime.*
import java.text.SimpleDateFormat
import java.util.Date

@Composable
fun Header() {
    val sdf = SimpleDateFormat("dd hh:mm:ss")
    val currentDate = sdf.format(Date())
    val date = currentDate.filter {it in '0'..'9'}
    val key = date.toInt()
    Box(){
        Card(
            modifier = Modifier.fillMaxWidth().padding(0.dp).clickable{},
            elevation = 10.dp,
            content = {
                Column( horizontalAlignment = Alignment.Start,
                    modifier = Modifier.border(0.dp, Color.Gray, RectangleShape).fillMaxWidth().padding(5.dp)) {
                    /*
                    Row(
                        modifier = Modifier.padding(all = 5.dp),horizontalArrangement = Arrangement.Start
                    ){
                        androidx.compose.material3.Text(
                            text = "No. Consecutivo "+key,
                            color = Color.Black, fontSize = 15.sp
                        )
                    }
                    Row(
                        modifier = Modifier.padding(all = 5.dp),horizontalArrangement = Arrangement.Start
                    ){
                        androidx.compose.material3.Text(
                            text = "Ejecutado por: Rucutundu Herrera",
                            color = Color.Black, fontSize = 15.sp
                        )
                    }

                    Row(
                        modifier = Modifier.padding(all = 5.dp),horizontalArrangement = Arrangement.Start
                    ){
                        androidx.compose.material3.Text(
                            text = "Ruta: Pasto-Popayán Popayán-Pasto",
                            color = Color.Black, fontSize = 15.sp
                        )
                    }

                     */
                    Row(
                        modifier = Modifier.padding(all = 5.dp),horizontalArrangement = Arrangement.Start
                    ){
                        androidx.compose.material3.Text(
                            //text = "VEHICULO:[AF-HKT3055] Camión D-Series 240 DXI",
                            text = "VEHICULO:[AF-HKT3055] Ambulancia ER",
                            color = Color.Black, fontSize = 15.sp
                        )
                    }
                    Row(
                        modifier = Modifier.padding(all = 5.dp),horizontalArrangement = Arrangement.Start
                    ){
                        androidx.compose.material3.Text(
                            text = "PLACA: GLR-489",
                            color = Color.Black, fontSize = 15.sp
                        )
                    }
                }
            }
        )
    }
}

