package dev.csandiego.zuju.technicalassessment

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import dev.csandiego.zuju.technicalassessment.data.MatchList
import dev.csandiego.zuju.technicalassessment.service.MatchService

@Composable
fun MatchListScreen(service: MatchService) {
    val list by produceState(initialValue = MatchList(emptyList(), emptyList())) {
        value = service.getMatches()
    }
    MatchListUi(list = list)
}