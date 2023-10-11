package dev.csandiego.zuju.technicalassessment.service

import dev.csandiego.zuju.technicalassessment.data.MatchList
import dev.csandiego.zuju.technicalassessment.data.Team

interface MatchService {

    suspend fun getMatches(): MatchList

    suspend fun getTeamMatches(team: Team): MatchList
}