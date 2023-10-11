package dev.csandiego.zuju.technicalassessment

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import dev.csandiego.zuju.technicalassessment.data.Team
import dev.csandiego.zuju.technicalassessment.service.TeamService

@Composable
fun TeamListScreen(service: TeamService) {
    val teams by produceState<List<Team>>(initialValue = emptyList()) {
        value = service.getTeams()
    }
    TeamList(teams)
}
