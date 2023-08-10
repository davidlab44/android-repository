package com.david.tot.ui.sync

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material3.OutlinedTextField

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.david.tot.ui.article.ArticleViewModel

@Composable
fun ScreenComponentHeader(syncViewModel: SyncViewModel) {
    val mContext = LocalContext.current
    val articleList =syncViewModel.articleList
    val size = articleList.size

    Box(){
        Card(
            modifier = Modifier.fillMaxWidth().padding(8.dp).clickable{},
            elevation = 10.dp,
            content = {
                Column( horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.border(1.dp, Color.Gray, RectangleShape).fillMaxWidth().padding(20.dp)) {
                    Row(
                        modifier = Modifier.padding(all = 12.dp),horizontalArrangement = Arrangement.Center
                    ){
                        androidx.compose.material3.Text(
                            text = "ELEMENTOS PENDIENTES: " + size,
                            color = Color.Black, fontSize = 15.sp, fontWeight = FontWeight.Black
                        )
                    }
                }
            }
        )
    }

    /*
    Column(
        modifier = Modifier.fillMaxSize().padding(5.dp),
        //horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.padding(all = 1.dp),
            //horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Tripulante: " + drugsDeliveryConsumerViewHeader.consumer,
                color = Color.Black,
                fontSize = 20.sp
            )
        }
        Row(
            modifier = Modifier.padding(all = 1.dp),
            //horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Licencia: "+drugsDeliveryConsumerViewHeaderViewModel.drugsDeliveryConsumerViewHeader.license
                        +" /Cat: "+drugsDeliveryConsumerViewHeaderViewModel.drugsDeliveryConsumerViewHeader.licenseCategory
                        +" /Vence: "+drugsDeliveryConsumerViewHeaderViewModel.drugsDeliveryConsumerViewHeader.licenseExpiration,
                color = Color.Black,
                fontSize = 12.sp
            )
        }
        Row(
            modifier = Modifier.padding(top = 5.dp), horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "VEHICULO: "+drugsDeliveryConsumerViewHeaderViewModel.drugsDeliveryConsumerViewHeader.vehicle,
                color = Color.Black,
                fontSize = 20.sp
            )
        }
        Row(
            modifier = Modifier.padding(all = 1.dp), horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = " SOAT: "+drugsDeliveryConsumerViewHeaderViewModel.drugsDeliveryConsumerViewHeader.soat
                        +" Vence: "+drugsDeliveryConsumerViewHeaderViewModel.drugsDeliveryConsumerViewHeader.soatExpiration,
                color = Color.Black,
                fontSize = 12.sp
            )
        }
        Row(
            modifier = Modifier.padding(all = 1.dp), horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = " CRTM: "+drugsDeliveryConsumerViewHeaderViewModel.drugsDeliveryConsumerViewHeader.crtm
                        +" Vence: "+drugsDeliveryConsumerViewHeaderViewModel.drugsDeliveryConsumerViewHeader.crtmExpiration,
                color = Color.Black,
                fontSize = 12.sp
            )
        }
    }
    */

}

