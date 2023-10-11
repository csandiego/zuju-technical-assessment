package dev.csandiego.zuju.technicalassessment

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.navigation.NavHostController
import dev.csandiego.zuju.technicalassessment.data.MatchList
import dev.csandiego.zuju.technicalassessment.service.MatchService

@Composable
fun MatchListScreen(navController: NavHostController, service: MatchService) {
    val list by produceState(initialValue = MatchList(emptyList(), emptyList())) {
        value = service.getMatches()
    }

    MatchListUi(list = list) {
        navController.navigate("match?date=${it.date}&description=${it.description}&home=${it.home}&away=${it.away}&winner=${it.winner}&highlights=${it.highlights}")
    }
}