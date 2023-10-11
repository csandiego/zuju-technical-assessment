package dev.csandiego.zuju.technicalassessment

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.navigation.NavHostController
import dev.csandiego.zuju.technicalassessment.data.Team
import dev.csandiego.zuju.technicalassessment.service.TeamService

@Composable
fun TeamListScreen(navController: NavHostController, service: TeamService) {
    val teams by produceState<List<Team>>(initialValue = emptyList()) {
        value = service.getTeams()
    }
    TeamList(teams = teams) {
        navController.navigate("team?id=${it.id}&name=${it.name}&logo=${it.logo}")
    }
}
