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
    val drugsDeliveryConsumerViewHeader = authenticableViewModel.authenticable
    Column(
        modifier = Modifier.fillMaxSize().padding(5.dp).background(Color(0xFFf4bca4)),
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
                text = "Licencia: "+authenticableViewModel.authenticable.license
                        +" /Cat: "+authenticableViewModel.authenticable.licenseCategory
                        +" /Vence: "+authenticableViewModel.authenticable.licenseExpiration,
                color = Color.Black,
                fontSize = 12.sp
            )
        }
        Row(
            modifier = Modifier.padding(top = 5.dp), horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "VEHICULO: "+authenticableViewModel.authenticable.vehicle,
                color = Color.Black,
                fontSize = 20.sp
            )
        }
        Row(
            modifier = Modifier.padding(all = 1.dp), horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = " SOAT: "+authenticableViewModel.authenticable.soat
                        +" Vence: "+authenticableViewModel.authenticable.soatExpiration,
                color = Color.Black,
                fontSize = 12.sp
            )
        }
        Row(
            modifier = Modifier.padding(all = 1.dp), horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = " CRTM: "+authenticableViewModel.authenticable.crtm
                        +" Vence: "+authenticableViewModel.authenticable.crtmExpiration,
                color = Color.Black,
                fontSize = 12.sp
            )
        }
    }
}

