package com.david.tot.ui.sync

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

import androidx.compose.runtime.*

@Composable
fun BodySyncList(syncViewModel: SyncViewModel) {
    //val mContext = LocalContext.current
    syncViewModel.getAllSyncsFromLocalDatabase()
    val syncList =syncViewModel.syncList
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(2.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //var text by rememberSaveable { mutableStateOf("") }
        //val pattern = remember { Regex("^\\d+\$") }
        val listModifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(top = 15.dp)
            .align(Alignment.CenterHorizontally)
        LazyColumn(modifier = listModifier) {
            //LIST
            items(syncList) { sync ->
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
                                Box(
                                ) {
                                    Text(text = "ID: "+sync.local_id)
                                }
                            }
                            Row(
                                modifier = Modifier.padding(all = 5.dp),horizontalArrangement = Arrangement.Center
                            ) {
                                Box(

                                ) {
                                    Text(text = "Tipo: "+sync.sharedPreferenceName)
                                }
                            }
                            Row(
                                modifier = Modifier.padding(all = 5.dp),horizontalArrangement = Arrangement.Center
                            ) {
                                Box(

                                ) {
                                    Text(text = "Fecha: "+sync.objectType)
                                }
                            }
                            /*
                            Row(
                                modifier = Modifier.padding(all = 5.dp),horizontalArrangement = Arrangement.Center
                            ) {
                                Box(

                                ) {
                                    Text(text = "Fecha de creación: "+sync.createdAt)
                                }
                            }
                            */
                        }
                    }
                )
            }
        }
    }
}