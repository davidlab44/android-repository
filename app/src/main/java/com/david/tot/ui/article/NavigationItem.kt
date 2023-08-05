package com.david.tot.ui.article

import com.david.tot.R

sealed class NavigationItem(var route: String, var icon: Int, var title: String) {
    object Home : NavigationItem("home", R.drawable.ic_home, "Home")
    object Music : NavigationItem("music", R.drawable.ic_music, "")
    object Movies : NavigationItem("movies", R.drawable.ic_movie, "")
    object Books : NavigationItem("books", R.drawable.ic_book, "")
    object Profile : NavigationItem("profile", R.drawable.ic_profile, "")
}