package com.david.tot.ui.spotable

import com.david.tot.R

// All icon list here
//https://fonts.google.com/icons?selected=Material+Symbols+Sharp:cached:FILL@0;wght@400;GRAD@0;opsz@48&icon.style=Sharp
sealed class SpotableBotomNavigationItem(var route: String, var icon: Int, var title: String) {
    object Home : SpotableBotomNavigationItem("home", R.drawable.ic_home, "Home")
    object Music : SpotableBotomNavigationItem("music", R.drawable.photo_camera_fill1_wght400_grad0_opsz24, "")
    object Movies : SpotableBotomNavigationItem("movies", R.drawable.local_shipping_fill1_wght400_grad0_opsz24, "")
    object Books : SpotableBotomNavigationItem("books", R.drawable.ic_book, "")
    object Profile : SpotableBotomNavigationItem("profile", R.drawable.ic_profile, "")
}