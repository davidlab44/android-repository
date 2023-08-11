package com.david.tot.ui.pre

import com.david.tot.R

// All icon list here
//https://fonts.google.com/icons?selected=Material+Symbols+Sharp:cached:FILL@0;wght@400;GRAD@0;opsz@48&icon.style=Sharp
sealed class PreBotomNavigationItem(var route: String, var icon: Int, var title: String) {
    object Home : PreBotomNavigationItem("home", R.drawable.ic_home, "Home")
    object Music : PreBotomNavigationItem("music", R.drawable.ic_profile, "")
    object Movies : PreBotomNavigationItem("movies", R.drawable.ic_movie, "")
    object Books : PreBotomNavigationItem("books", R.drawable.ic_book, "")
    object Profile : PreBotomNavigationItem("profile", R.drawable.ic_profile, "")
}