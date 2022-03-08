package com.seventhson.marvel.ui.navigation


sealed class Screen(val route: String) {
    object Main : Screen("main")
    object Detail : Screen("detail/{id}") {
        fun createRoute(id: Int, name: String? = null, image: String? = null) = "detail/$id"
    }
}