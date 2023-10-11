package dev.csandiego.zuju.technicalassessment.data

import kotlinx.serialization.Serializable

@Serializable
data class TeamPayload(
    val teams: List<Team>
)
