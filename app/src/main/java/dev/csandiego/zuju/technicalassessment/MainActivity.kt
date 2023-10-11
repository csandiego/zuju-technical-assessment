package dev.csandiego.zuju.technicalassessment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.csandiego.zuju.technicalassessment.data.Team
import dev.csandiego.zuju.technicalassessment.service.KtorService
import dev.csandiego.zuju.technicalassessment.ui.theme.TechnicalAssessmentTheme

class MainActivity : ComponentActivity() {

    private val service = KtorService()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TechnicalAssessmentTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    Greeting("Android")
//                    TeamListScreen(service = service)
//                    MatchListScreen(service = service)
                    val team = Team(
                        "767ec50c-7fdb-4c3d-98f9-d6727ef8252b",
                        "Team Red Dragons",
                        "https://tstzj.s3.amazonaws.com/dragons.png"
                    )
                    TeamDetailScreen(service = service, team = team)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TechnicalAssessmentTheme {
        Greeting("Android")
    }
}