package dev.csandiego.zuju.technicalassessment.compose

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import dev.csandiego.zuju.technicalassessment.R
import dev.csandiego.zuju.technicalassessment.data.Match
import dev.csandiego.zuju.technicalassessment.ui.theme.TechnicalAssessmentTheme
import org.junit.Rule
import org.junit.Test
import java.util.Calendar

class MatchDetailUiTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun givenWillBeReminded_whenLoaded_thenShowCancelReminder() {
        val match = Match(
            "2024-04-23T18:00:00.000Z",
            "Team Cool Eagles vs. Team Red Dragons",
            "Team Cool Eagles",
            "Team Red Dragons",
            "Team Red Dragons",
            "https://tstzj.s3.amazonaws.com/highlights.mp4"
        )
        with(composeTestRule) {
            setContent {
                TechnicalAssessmentTheme {
                    MatchDetail(
                        match = match,
                        hasReminder = true,
                        allowRemindersAfter = Calendar.Builder().setDate(2023, 1, 1).build().time
                    )
                }
            }
            onNodeWithText(activity.getString(R.string.cancel_reminder).uppercase()).assertExists()
        }
    }

    @Test
    fun givenWillNotBeReminded_whenLoaded_thenDisplayRemindMe() {
        val match = Match(
            "2024-04-23T18:00:00.000Z",
            "Team Cool Eagles vs. Team Red Dragons",
            "Team Cool Eagles",
            "Team Red Dragons",
            "Team Red Dragons",
            "https://tstzj.s3.amazonaws.com/highlights.mp4"
        )
        with(composeTestRule) {
            setContent {
                TechnicalAssessmentTheme {
                    MatchDetail(
                        match = match,
                        hasReminder = false,
                        allowRemindersAfter = Calendar.Builder().setDate(2023, 1, 1).build().time
                    )
                }
            }
            onNodeWithText(activity.getString(R.string.remind_me).uppercase()).assertExists()
        }
    }
}