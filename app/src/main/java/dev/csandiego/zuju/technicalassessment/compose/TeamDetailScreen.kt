package dev.csandiego.zuju.technicalassessment.compose

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import dev.csandiego.zuju.technicalassessment.data.MatchList
import dev.csandiego.zuju.technicalassessment.data.Team
import dev.csandiego.zuju.technicalassessment.service.MatchService


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeamDetailScreen(navController: NavHostController, service: MatchService, team: Team) {
    val matches by produceState<MatchList>(initialValue = MatchList(emptyList(), emptyList())) {
        value = service.getTeamMatches(team)
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = team.name) },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Navigate up")
                    }
                }
            )
        },
    ) { padding ->
        TeamDetail(team = team, matches = matches, modifier = Modifier.padding(padding)) {
            navController.navigate("match?date=${it.date}&description=${it.description}&home=${it.home}&away=${it.away}&winner=${it.winner}&highlights=${it.highlights}")
        }
    }
}