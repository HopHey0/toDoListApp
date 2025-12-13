package com.example.pract_7_8.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.pract_7_8.presentation.ui.screen.ItemDetail
import com.example.pract_7_8.presentation.ui.screen.ToDoList
import com.example.pract_7_8.presentation.viewmodel.TodolistViewModel

@Composable
fun AppNavHost(
    viewModel: TodolistViewModel,
    navController: NavHostController = rememberNavController(),
) {
    NavHost(navController = navController, startDestination = Routes.Home.route) {
        composable(Routes.Home.route) {
            ToDoList(
                viewModel,
                onItemClick = { itemId ->
                    navController.navigate(Routes.Detail.createRoute(itemId)) {popUpTo(Routes.Home.route)} },
            )
        }
        composable(
            route = Routes.Detail.route,
            arguments = listOf(
                navArgument("itemId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val itemId = backStackEntry.arguments?.getInt("itemId") ?: -1

            ItemDetail(
                itemId = itemId,
                viewModel = viewModel,
                onBackClick = { navController.navigate(Routes.Home.route) }
            )
        }
    }
}

