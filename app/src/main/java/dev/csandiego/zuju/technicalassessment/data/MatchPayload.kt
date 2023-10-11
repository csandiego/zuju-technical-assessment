package dev.csandiego.zuju.technicalassessment.data

import kotlinx.serialization.Serializable

@Serializable
data class MatchPayload(
    val matches: MatchList
)
