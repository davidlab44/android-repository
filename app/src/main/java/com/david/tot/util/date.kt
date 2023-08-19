package com.david.tot.util

import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.Date

//can be used as method to get ordered identifiers from current date
//val sdf = SimpleDateFormat("yyyyMMdd hh:mm:ss")
//val sdf = SimpleDateFormat("MMdd hh:mm:ss")
val sdf = SimpleDateFormat("hh:mm:ss")
val currentDate = sdf.format(Date())
val date = currentDate.filter {it in '0'..'9'}

class Dates {
    fun geDateAsString():String {
        val sdf = SimpleDateFormat("mm hh:mm:ss")
        val currentDate = sdf.format(Date())
        val date = currentDate.filter {it in '0'..'9'}
        return date.toString()
    }

    fun dateAsLong():Long {
        val sdf = SimpleDateFormat("mm hh:mm:ss")
        val currentDate = sdf.format(Date())
        val dateAsLong = currentDate.filter {it in '0'..'9'}
        return dateAsLong.toLong()
    }

    fun dateAsInt():Int {
        val sdf = SimpleDateFormat("hh:mm:ss")
        val currentDate = sdf.format(Date())
        val dateAsInt = currentDate.filter {it in '0'..'9'}
        return dateAsInt.toInt()
    }

    fun date():String {
        //val sdf = SimpleDateFormat("yyyy-MM-ddThh:mm:ss.655Z")
        //return  sdf.format(Date())
        return LocalDateTime.now().toString()
        //val dateAsInt = currentDate.filter {it in '0'..'9'}
        //return Date().toString()
    }
}