package com.david.tot.ui.sync

import com.david.tot.R

// All icon list here
//https://fonts.google.com/icons?selected=Material+Symbols+Sharp:cached:FILL@0;wght@400;GRAD@0;opsz@48&icon.style=Sharp
sealed class BotomNavigationItem(var route: String, var icon: Int, var title: String) {
    object Home : BotomNavigationItem("home", R.drawable.backup_white_18dp, "Home")
    object Music : BotomNavigationItem("music", R.drawable.cached_icon, "")
    object Movies : BotomNavigationItem("movies", R.drawable.ic_profile, "")
    object Books : BotomNavigationItem("books", R.drawable.ic_book, "")
    object Profile : BotomNavigationItem("Sync", R.drawable.cloud_done_fill1_wght400_grad0_opsz24, "")
}