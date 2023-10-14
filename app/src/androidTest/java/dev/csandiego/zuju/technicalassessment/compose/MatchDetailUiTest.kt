package dev.csandiego.zuju.technicalassessment.compose

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import dev.csandiego.zuju.technicalassessment.R
import dev.csandiego.zuju.technicalassessment.data.Match
import dev.csandiego.zuju.technicalassessment.ui.theme.TechnicalAssessmentTheme
import org.junit.Rule
import org.junit.Test

class MatchDetailUiTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun givenWillBeReminded_whenLoaded_thenShowCancelReminder() {
        val match = Match(
            "2022-04-23T18:00:00.000Z",
            "Team Cool Eagles vs. Team Red Dragons",
            "Team Cool Eagles",
            "Team Red Dragons",
            "Team Red Dragons",
            "https://tstzj.s3.amazonaws.com/highlights.mp4"
        )
        with(composeTestRule) {
            setContent {
                TechnicalAssessmentTheme {
                    MatchDetail(match = match, willBeReminded = true)
                }
            }
            onNodeWithText(activity.getString(R.string.cancel_reminder)).assertExists()
        }
    }

    @Test
    fun givenWillNotBeReminded_whenLoaded_thenDisplayRemindMe() {
        val match = Match(
            "2022-04-23T18:00:00.000Z",
            "Team Cool Eagles vs. Team Red Dragons",
            "Team Cool Eagles",
            "Team Red Dragons",
            "Team Red Dragons",
            "https://tstzj.s3.amazonaws.com/highlights.mp4"
        )
        with(composeTestRule) {
            setContent {
                TechnicalAssessmentTheme {
                    MatchDetail(match = match, willBeReminded = false)
                }
            }
            onNodeWithText(activity.getString(R.string.remind_me)).assertExists()
        }
    }
}