package com.david.tot.ui.authenticable

import android.content.Context
import com.yeslab.fastprefs.FastPrefs
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.Date

//can be used as method to get ordered identifiers from current date
//val sdf = SimpleDateFormat("yyyyMMdd hh:mm:ss")
//val sdf = SimpleDateFormat("MMdd hh:mm:ss")

fun fetchUser(mContext: Context):String {
    val prefs = FastPrefs(mContext)
    val user = prefs.getString("user","defaultUser")
    if(user!=null)
        return user
    else
        return ""
}


fun isLogged(mContext: Context):Boolean {
    val prefs = FastPrefs(mContext)
    val user = prefs.getString("user","defaultUser")
    if(user!=null && !user.equals("defaultUser") && !user.equals("0") )
        return true
    return false
}
