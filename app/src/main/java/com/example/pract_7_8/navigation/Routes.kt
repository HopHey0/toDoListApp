package com.example.pract_7_8.navigation

sealed class Routes(val route: String) {

    object Home : Routes("home")
    object Detail : Routes("detail/{itemId}") {
        fun createRoute(itemId: Int) = "detail/$itemId"
    }
}