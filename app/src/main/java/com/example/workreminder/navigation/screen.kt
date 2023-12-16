package com.example.workreminder.navigation

sealed class Screen(val route: String)
{
    data object Splash: Screen(route = "splash_screen")
    data object TOODO: Screen(route = "todo")
    data object Setter: Screen(route = "setter")
    data object Finder: Screen(route = "finder")
    data object UpdateTask: Screen(route = "update_task/{id}"){
        fun getByID(id:Int)="update_task/$id"
    }
}
