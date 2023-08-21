package com.david.tot.util


import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ExpandingTextFinal() {

    var value by remember { mutableStateOf("") }
    OutlinedTextField(
        value = value,
        onValueChange = {
            value = it
        },
        label = { Text("Descripción de la novedad") },
        modifier = Modifier.padding(20.dp).height(200.dp),
        singleLine= false,
        maxLines = 10
    )

    /*
    var value by remember { mutableStateOf("") }
    TextField(
        value = value,
        onValueChange = {
            value = it
        },
        singleLine = true,
        label = { Text("Enter text") },
        modifier = Modifier.padding(20.dp)
    )

     */
}