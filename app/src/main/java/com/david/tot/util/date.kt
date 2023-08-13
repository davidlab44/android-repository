package com.david.tot.util

import java.text.SimpleDateFormat
import java.util.Date

//can be used as method to get ordered identifiers from current date
//val sdf = SimpleDateFormat("yyyyMMdd hh:mm:ss")
//val sdf = SimpleDateFormat("MMdd hh:mm:ss")
val sdf = SimpleDateFormat("hh:mm:ss")
val currentDate = sdf.format(Date())
val date = currentDate.filter {it in '0'..'9'}