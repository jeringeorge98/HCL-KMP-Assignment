package org.example.project.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHost
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import org.example.project.network.repository.CountryRepository
import org.example.project.ui.DetailScreen
import org.example.project.ui.HomeScreen
import org.example.project.viewmodels.HomeVMFactory


@Composable
fun AppNav(
    countryRepository: CountryRepository,
    navCoordinator: NavCoordinator
) {
    val NAV_CONTROLLER_ANIMATION_DURATION_MILLIS=500
    val NAV_CONTROLLER_SLIDE_OFFSET = 2000
    val navController = rememberNavController()
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val startingDestination = Screen.Home.route

    LaunchedEffect(navController){
        navCoordinator.events.flowWithLifecycle(lifecycle).collect { event ->
            when(event){
                is NavEvent.Back -> navController.popBackStack()
                is NavEvent.ToDetailScreen -> navController.navigate(Screen.Detail.passName(event.countryName))
            }

        }
    }
    NavHost(
        navController,
        startDestination = startingDestination,
        enterTransition = {
            slideInHorizontally(
                initialOffsetX = { NAV_CONTROLLER_SLIDE_OFFSET },
                animationSpec = tween(NAV_CONTROLLER_ANIMATION_DURATION_MILLIS),
            ) + fadeIn()
        },
        exitTransition = {
            slideOutHorizontally(
                targetOffsetX = { -NAV_CONTROLLER_SLIDE_OFFSET },
                animationSpec = tween(NAV_CONTROLLER_ANIMATION_DURATION_MILLIS),
            ) + fadeOut()
        },
    ) {
        composable(route = Screen.Home.route) {
            HomeScreen(
                viewModel = viewModel(
                    factory = HomeVMFactory(
                        repository = countryRepository,
                        navCoordinator = navCoordinator
                    )
                )
            )
        }

        composable(route = Screen.Detail.route, arguments = listOf(navArgument("countryName") {
            type =
                NavType.StringType
        })) { backStackEntry ->
            val countryName = backStackEntry.arguments?.getString("countryName") ?: ""

            DetailScreen(
                viewModel =
                    viewModel(
                        factory =
                            HomeVMFactory(
                                countryRepository,
                                navCoordinator,
                            ),
                    ),
                countryName = countryName
            )
        }
    }

}