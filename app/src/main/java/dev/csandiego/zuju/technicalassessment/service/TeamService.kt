package dev.csandiego.zuju.technicalassessment.service

import dev.csandiego.zuju.technicalassessment.data.Team

interface TeamService {

    suspend fun getTeams(): List<Team>
}