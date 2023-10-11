package dev.csandiego.zuju.technicalassessment.service

import dev.csandiego.zuju.technicalassessment.data.MatchList
import dev.csandiego.zuju.technicalassessment.data.MatchPayload
import dev.csandiego.zuju.technicalassessment.data.Team
import dev.csandiego.zuju.technicalassessment.data.TeamPayload
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json

class KtorService : TeamService, MatchService {

    private val client = HttpClient(Android) {
        install(ContentNegotiation) {
            json(contentType = ContentType("application", "json"))
        }
    }

    override suspend fun getTeams(): List<Team> =
        client.get("https://jmde6xvjr4.execute-api.us-east-1.amazonaws.com/teams")
            .body<TeamPayload>()
            .teams

    override suspend fun getMatches(): MatchList =
        client.get("https://jmde6xvjr4.execute-api.us-east-1.amazonaws.com/teams/matches")
            .body<MatchPayload>()
            .matches

    override suspend fun getTeamMatches(team: Team): MatchList =
        client.get("https://jmde6xvjr4.execute-api.us-east-1.amazonaws.com/teams/${team.id}/matches")
            .body<MatchPayload>()
            .matches
}