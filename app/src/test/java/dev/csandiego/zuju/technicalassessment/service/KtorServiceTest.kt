package dev.csandiego.zuju.technicalassessment.service

import dev.csandiego.zuju.technicalassessment.data.Team
import io.ktor.serialization.JsonConvertException
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertThrows
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class KtorServiceTest {

    private lateinit var service: KtorService

    @Before
    fun setUp() {
        service = KtorService()
    }

    @Test
    fun whenGetTeamsInvoked_thenReturnNonEmptyList() = runBlocking {
        assertTrue(service.getTeams().isNotEmpty())
    }

    @Test
    fun whenGetMatchesInvoked_thenReturnNonEmptyList() = runBlocking {
        val matches = service.getMatches()
        assertTrue(matches.previous.isNotEmpty() && matches.upcoming.isNotEmpty())
    }

    @Test
    fun givenValidTeam_whenGetTeamMatchesInvoked_thenReturnNonEmptyList() = runBlocking {
        val team = Team(
            "767ec50c-7fdb-4c3d-98f9-d6727ef8252b",
            "Team Red Dragons",
            "https://tstzj.s3.amazonaws.com/dragons.png"
        )
        val matches = service.getTeamMatches(team)
        assertTrue(matches.previous.isNotEmpty() && matches.upcoming.isNotEmpty())
    }

    @Test
    fun givenInvalidTeam_whenGetTeamMatchesInvoked_thenThrowException() {
        assertThrows(JsonConvertException::class.java) {
            runBlocking {
                val team = Team(
                    "INVALID_ID",
                    "Team Red Dragons",
                    "https://tstzj.s3.amazonaws.com/dragons.png"
                )
                service.getTeamMatches(team)
            }
        }
    }
}