package com.example.pract_7_8.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.pract_7_8.presentation.ui.screen.ItemDetail
import com.example.pract_7_8.presentation.ui.screen.ToDoList
import com.example.pract_7_8.presentation.viewmodel.TodoDetailsViewModel
import com.example.pract_7_8.presentation.viewmodel.TodoListViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun AppNavHost(modifier: Modifier) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.Home.route) {
        composable(Routes.Home.route) {
            val listViewModel: TodoListViewModel = koinViewModel()
            ToDoList(
                listViewModel,
                onItemClick = { itemId ->
                    navController.navigate(Routes.Detail.createRoute(itemId))
                },
                modifier = modifier
            )
        }
        composable(
            route = Routes.Detail.route,
            arguments = listOf(navArgument("itemId") { type = NavType.IntType })
        ) { backStackEntry ->
            val detailsViewModel: TodoDetailsViewModel = koinViewModel()
            val itemId = backStackEntry.arguments?.getInt("itemId") ?: -1

            LaunchedEffect(itemId) {
                detailsViewModel.loadTodo(itemId)
            }

            val item = detailsViewModel.detailsUiState.collectAsStateWithLifecycle().value.item

            ItemDetail(
                item = item,
                viewModel = detailsViewModel,
                onBackClick = { navController.popBackStack() },
                modifier = modifier
            )
        }
    }
}
