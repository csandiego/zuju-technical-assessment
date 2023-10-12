package dev.csandiego.zuju.technicalassessment.compose

import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.TopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dev.csandiego.zuju.technicalassessment.service.MatchService
import dev.csandiego.zuju.technicalassessment.service.ReminderService
import dev.csandiego.zuju.technicalassessment.service.TeamService

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    teamService: TeamService,
    matchService: MatchService,
    reminderService: ReminderService
) {
    val localNavController = rememberNavController()
    Scaffold(
        topBar = {
            TopAppBar (
                title = { Text(text = "Zuju") }
            )
        },
        bottomBar = {
            BottomNavigation {
                val navBackStackEntry by localNavController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                listOf("teams", "matches", "reminders").forEach {
                    BottomNavigationItem(
                        selected = it == currentRoute,
                        onClick = {
                            localNavController.navigate(it) {
                                popUpTo(localNavController.graph.findStartDestination().id) {
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
            navController = localNavController,
            startDestination = "teams",
            modifier = Modifier.padding(it),
        ) {
            composable("teams") {
                TeamListScreen(navController = navController, service = teamService)
            }
            composable("matches") {
                MatchListScreen(navController = navController, service = matchService)
            }
            composable("reminders") {
                ReminderListScreen(service = reminderService)
            }
        }
    }
}