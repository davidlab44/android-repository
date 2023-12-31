package com.david.tot.ui.authenticable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.unit.sp

import androidx.compose.ui.unit.dp

@Composable
fun AuthenticableScreenComponentHeader(authenticableViewModel: AuthenticableViewModel) {
    //authenticableViewModel.getAlldrugsDeliveryConsumerViewHeader()
    authenticableViewModel.getAllAutenticables()
    val drugsDeliveryConsumerViewHeader = authenticableViewModel.authenticable
    Column(
        modifier = Modifier.fillMaxSize().padding(5.dp),
        //horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.padding(all = 1.dp),
            //horizontalArrangement = Arrangement.Center
        ) {
            var consumerName=""
            if(drugsDeliveryConsumerViewHeader.consumer.length>30){
                consumerName = drugsDeliveryConsumerViewHeader.consumer.substring(0, 30)
            }else{
                consumerName= drugsDeliveryConsumerViewHeader.consumer
            }
            Text(
                text = "" + consumerName,
                color = Color.Black,
                fontSize = 18.sp
            )
        }
        Row(
            modifier = Modifier.padding(all = 1.dp),
            //horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Licencia: "+authenticableViewModel.authenticable.license
                        +" /Cat: "+authenticableViewModel.authenticable.licenseCategory
                        +" /Vence: "+authenticableViewModel.authenticable.licenseExpiration,
                color = Color.Black,
                fontSize = 13.sp
            )
        }
        Row(
            modifier = Modifier.padding(top = 5.dp), horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "VEHICULO: "+authenticableViewModel.authenticable.vehicle,
                color = Color.Black,
                fontSize = 16.sp
            )
        }
        Row(
            modifier = Modifier.padding(all = 1.dp), horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = " SOAT: "+authenticableViewModel.authenticable.soat
                        +" Vence: "+authenticableViewModel.authenticable.soatExpiration,
                color = Color.Black,
                fontSize = 13.sp
            )
        }
        Row(
            modifier = Modifier.padding(all = 1.dp), horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = " CRTM: "+authenticableViewModel.authenticable.crtm
                        +" Vence: "+authenticableViewModel.authenticable.crtmExpiration,
                color = Color.Black,
                fontSize = 13.sp
            )
        }
    }
}

