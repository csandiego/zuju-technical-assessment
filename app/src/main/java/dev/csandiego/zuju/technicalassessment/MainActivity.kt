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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.csandiego.zuju.technicalassessment.data.Match
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
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "home") {
                        composable("home") {
                            HomeScreen(
                                navController = navController,
                                teamService = service,
                                matchService = service
                            )
                        }
                        composable("team?id={id}&name={name}&logo={logo}") {
                            val team = Team(
                                it.arguments!!.getString("id")!!,
                                it.arguments!!.getString("name")!!,
                                it.arguments!!.getString("logo")!!
                            )
                            TeamDetailScreen(
                                navController = navController,
                                service = service,
                                team = team
                            )
                        }
                        composable("match?date={date}&description={description}&home={home}&away={away}&winner={winner}&highlights={highlights}") {
                            val match = Match(
                                it.arguments!!.getString("date")!!,
                                it.arguments!!.getString("description")!!,
                                it.arguments!!.getString("home")!!,
                                it.arguments!!.getString("away")!!,
                                it.arguments!!.getString("winner"),
                                it.arguments!!.getString("highlights")
                            )
                            MatchDetailScreen(navController = navController, match = match)
                        }
                    }
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