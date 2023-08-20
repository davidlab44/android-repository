package com.david.tot.ui.confirmable

import com.david.tot.R

// All icon list here
//https://fonts.google.com/icons?selected=Material+Symbols+Sharp:cached:FILL@0;wght@400;GRAD@0;opsz@48&icon.style=Sharp
sealed class BotomNavigationItem(var route: String, var icon: Int, var title: String) {
    object Home : BotomNavigationItem("home", R.drawable.home_repair_service_fill1_wght400_grad0_opsz24, "")
    object Music : BotomNavigationItem("music", R.drawable.check_box_fill1_wght400_grad0_opsz24, "")
    object Movies : BotomNavigationItem("movies", R.drawable.ic_movie, "")
    object Books : BotomNavigationItem("books", R.drawable.ic_book, "")
    object Profile : BotomNavigationItem("profile", R.drawable.ic_profile, "")
}