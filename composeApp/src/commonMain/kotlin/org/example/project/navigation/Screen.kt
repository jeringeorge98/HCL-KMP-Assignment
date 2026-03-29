package org.example.project.navigation

sealed class Screen(val route:String) {
    object Home: Screen("home")
    object Detail: Screen("details/{countryName}"){
        fun passName(name: String): String ="details/$name"
    }
}