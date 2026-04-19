package com.example.pract_7_8.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.pract_7_8.presentation.ui.screen.ItemDetail
import com.example.pract_7_8.presentation.ui.screen.ToDoList
import com.example.pract_7_8.presentation.viewmodel.TodolistViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun AppNavHost(
    modifier: Modifier
) {
    val navController = rememberNavController()
    val viewModel: TodolistViewModel = koinViewModel()

    NavHost(navController = navController, startDestination = Routes.Home.route) {
        composable(Routes.Home.route) {
            ToDoList(
                viewModel,
                onItemClick = { itemId ->
                    navController.navigate(Routes.Detail.createRoute(itemId)) {
                        popUpTo(Routes.Home.route) {inclusive = true}
                    }
                              },
                modifier = modifier
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

            LaunchedEffect(itemId) {
                viewModel.getSingleTodo(itemId)
            }

            val item = viewModel.detailsUiState.collectAsStateWithLifecycle().value.item

            ItemDetail(
                item = item,
                onBackClick = { navController.navigate(Routes.Home.route) {popUpTo(Routes.Home.route) {inclusive = true} } },
                modifier = modifier
            )
        }
    }
}

