package com.david.tot.ui.settings

import com.david.tot.R

// All icon list here
//https://fonts.google.com/icons?selected=Material+Symbols+Sharp:cached:FILL@0;wght@400;GRAD@0;opsz@48&icon.style=Sharp
sealed class SettingsBotomNavigationItem(var route: String, var icon: Int, var title: String) {
    object Home : SettingsBotomNavigationItem("home", R.drawable.ic_home, "Home")
    object Music : SettingsBotomNavigationItem("music", R.drawable.ic_profile, "")
    object Movies : SettingsBotomNavigationItem("movies", R.drawable.ic_movie, "")
    object Books : SettingsBotomNavigationItem("books", R.drawable.ic_book, "")
    object Profile : SettingsBotomNavigationItem("profile", R.drawable.ic_profile, "")
}