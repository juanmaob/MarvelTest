package com.seventhson.marvel.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.accompanist.pager.ExperimentalPagerApi
import com.seventhson.marvel.ui.detail.views.DetailScreen
import com.seventhson.marvel.ui.main.views.MainScreen

@ExperimentalPagerApi
@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = Screen.Main.route) {
        addMainGraph(navController)
        addDetailGraph(navController)
    }
}

private fun NavGraphBuilder.addMainGraph(navController: NavHostController) {
    composable(route = Screen.Main.route) {
        MainScreen(
            onItemClick = { character ->
                navController.navigate(
                    Screen.Detail.createRoute(
                        id = character.id
                    )
                )
            }
        )
    }
}

@ExperimentalPagerApi
private fun NavGraphBuilder.addDetailGraph(navController: NavHostController) {
    //si no pones arguments puedes obtener el argumento con get("key") o getString("key) pero te devuelve un string siempre
    composable(
        route = Screen.Detail.route,
        arguments = listOf(
            navArgument("id") { type = NavType.IntType }
        )
    ) { backStackEntry ->

        DetailScreen(
            onClickBack = {
                navController.popBackStack()
            }
        )
    }
}
