package dev.csandiego.zuju.technicalassessment

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import dev.csandiego.zuju.technicalassessment.data.MatchList
import dev.csandiego.zuju.technicalassessment.data.Team
import dev.csandiego.zuju.technicalassessment.service.MatchService

@Composable
fun TeamDetailScreen(service: MatchService, team: Team) {
    val matches by produceState<MatchList>(initialValue = MatchList(emptyList(), emptyList())) {
        value = service.getTeamMatches(team)
    }
    TeamDetail(team = team, matches = matches)
}