package dev.csandiego.zuju.technicalassessment.data

import kotlinx.serialization.Serializable

@Serializable
data class Match(
    val date: String,
    val description: String,
    val home: String,
    val away: String,
    val winner: String? = null,
    val highlights: String? = null
)