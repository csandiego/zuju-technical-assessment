package dev.csandiego.zuju.technicalassessment.compose

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dev.csandiego.zuju.technicalassessment.R
import dev.csandiego.zuju.technicalassessment.service.MatchService
import dev.csandiego.zuju.technicalassessment.service.TeamService
import dev.csandiego.zuju.technicalassessment.ui.theme.BarbiePink

sealed class HomeScreenRoute(val route: String, @StringRes val label: Int, val icon: ImageVector) {
    object TeamsRoute : HomeScreenRoute("teams", R.string.teams, Icons.Filled.Person)
    object MatchesRoute : HomeScreenRoute("matches", R.string.matches, Icons.Filled.DateRange)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    teamService: TeamService,
    matchService: MatchService
) {
    val localNavController = rememberNavController()
    Scaffold(
        topBar = {
            TopAppBar (
                title = { Text(text = stringResource(id = R.string.home_title)) }
            )
        },
        bottomBar = {
            BottomNavigation(backgroundColor = BarbiePink) {
                val navBackStackEntry by localNavController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                listOf(HomeScreenRoute.TeamsRoute, HomeScreenRoute.MatchesRoute).forEach {
                    BottomNavigationItem(
                        selected = it.route == currentRoute,
                        onClick = {
                            localNavController.navigate(it.route) {
                                popUpTo(localNavController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = it.icon,
                                contentDescription = stringResource(id = it.label),
                                modifier = Modifier.padding(8.dp)
                            )
                        },
                        label = {
                            Text(
                                text = stringResource(id = it.label)
                            )
                        }
                    )
                }
            }
        }
    ) {
        NavHost(
            navController = localNavController,
            startDestination = "teams",
            modifier = Modifier.padding(it),
        ) {
            composable(HomeScreenRoute.TeamsRoute.route) {
                TeamListScreen(navController = navController, service = teamService)
            }
            composable(HomeScreenRoute.MatchesRoute.route) {
                MatchListScreen(navController = navController, service = matchService)
            }
        }
    }
}