package com.david.tot.ui.spendable

import com.david.tot.R

// All icon list here
//https://fonts.google.com/icons?selected=Material+Symbols+Sharp:cached:FILL@0;wght@400;GRAD@0;opsz@48&icon.style=Sharp
sealed class BotomNavigationItem(var route: String, var icon: Int, var title: String) {
    object Home : BotomNavigationItem("home", R.drawable.ic_home, "Home")
    object Music : BotomNavigationItem("music", R.drawable.photo_camera_fill1_wght400_grad0_opsz24, "")
    object Movies : BotomNavigationItem("movies", R.drawable.local_shipping_fill1_wght400_grad0_opsz24, "")
    object Books : BotomNavigationItem("books", R.drawable.ic_book, "")
    object Profile : BotomNavigationItem("profile", R.drawable.ic_profile, "")
}