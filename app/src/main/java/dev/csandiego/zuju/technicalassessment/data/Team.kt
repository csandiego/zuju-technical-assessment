package dev.csandiego.zuju.technicalassessment.data

import kotlinx.serialization.Serializable

@Serializable
data class Team(
    val id: String,
    val name: String,
    val logo: String
)
