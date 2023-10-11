package dev.csandiego.zuju.technicalassessment.data

import kotlinx.serialization.Serializable

@Serializable
data class MatchList(
    val previous: List<Match>,
    val upcoming: List<Match>
)
