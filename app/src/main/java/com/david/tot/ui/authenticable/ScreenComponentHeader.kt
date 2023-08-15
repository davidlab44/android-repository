package com.david.tot.ui.authenticable

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.unit.sp

import androidx.compose.ui.unit.dp

@Composable
fun ScreenComponentHeader(drugsDeliveryConsumerViewHeaderViewModel: DrugsDeliveryConsumerViewHeaderViewModel) {
    val drugsDeliveryConsumerViewHeader = drugsDeliveryConsumerViewHeaderViewModel.authenticable
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
                text = "Licencia: "+drugsDeliveryConsumerViewHeaderViewModel.authenticable.license
                        +" /Cat: "+drugsDeliveryConsumerViewHeaderViewModel.authenticable.licenseCategory
                        +" /Vence: "+drugsDeliveryConsumerViewHeaderViewModel.authenticable.licenseExpiration,
                color = Color.Black,
                fontSize = 12.sp
            )
        }
        Row(
            modifier = Modifier.padding(top = 5.dp), horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "VEHICULO: "+drugsDeliveryConsumerViewHeaderViewModel.authenticable.vehicle,
                color = Color.Black,
                fontSize = 20.sp
            )
        }
        Row(
            modifier = Modifier.padding(all = 1.dp), horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = " SOAT: "+drugsDeliveryConsumerViewHeaderViewModel.authenticable.soat
                        +" Vence: "+drugsDeliveryConsumerViewHeaderViewModel.authenticable.soatExpiration,
                color = Color.Black,
                fontSize = 12.sp
            )
        }
        Row(
            modifier = Modifier.padding(all = 1.dp), horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = " CRTM: "+drugsDeliveryConsumerViewHeaderViewModel.authenticable.crtm
                        +" Vence: "+drugsDeliveryConsumerViewHeaderViewModel.authenticable.crtmExpiration,
                color = Color.Black,
                fontSize = 12.sp
            )
        }
    }
}

