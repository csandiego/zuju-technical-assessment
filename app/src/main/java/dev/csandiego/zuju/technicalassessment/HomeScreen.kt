package dev.csandiego.zuju.technicalassessment

import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dev.csandiego.zuju.technicalassessment.service.MatchService
import dev.csandiego.zuju.technicalassessment.service.TeamService

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(teamService: TeamService, matchService: MatchService) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigation {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                listOf("teams", "matches").forEach {
                    BottomNavigationItem(
                        selected = it == currentRoute,
                        onClick = {
                            navController.navigate(it) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = { /*TODO*/ },
                        label = { Text(text = it) }
                    )
                }
            }
        }
    ) {
        NavHost(
            navController = navController,
            startDestination = "teams",
            modifier = Modifier.padding(it),
        ) {
            composable("teams") {
                TeamListScreen(service = teamService)
            }
            composable("matches") {
                MatchListScreen(service = matchService)
            }
        }
    }
}