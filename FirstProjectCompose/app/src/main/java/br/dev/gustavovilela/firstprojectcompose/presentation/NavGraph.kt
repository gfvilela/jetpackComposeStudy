package br.dev.gustavovilela.firstprojectcompose.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.dev.gustavovilela.firstprojectcompose.presentation.ui.demo.DemoScreen
import br.dev.gustavovilela.firstprojectcompose.presentation.ui.list.CountryListScreen
import br.dev.gustavovilela.firstprojectcompose.presentation.ui.list.CountryListViewModel

@Composable
fun NavGraph(navController: NavHostController, countryListViewModel: CountryListViewModel) {
    NavHost(navController = navController, startDestination = NavRoutes.HOME) {
        composable(NavRoutes.HOME) {
            DemoScreen(navController)
        }
        composable(NavRoutes.LIST) {
            CountryListScreen(navController, viewModel = countryListViewModel)
        }
    }
}