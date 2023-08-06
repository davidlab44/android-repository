package com.david.tot.ui.article

import com.david.tot.R
import androidx.compose.material.IconToggleButton

// All icon list here
//https://fonts.google.com/icons?selected=Material+Symbols+Sharp:cached:FILL@0;wght@400;GRAD@0;opsz@48&icon.style=Sharp
sealed class NavigationItem(var route: String, var icon: Int, var title: String) {
    object Home : NavigationItem("home", R.drawable.ic_home, "Home")
    object Music : NavigationItem("music", R.drawable.ic_profile, "")
    object Movies : NavigationItem("movies", R.drawable.ic_movie, "")
    object Books : NavigationItem("books", R.drawable.ic_book, "")
    object Profile : NavigationItem("profile", R.drawable.ic_profile, "")
}