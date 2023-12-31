package dev.csandiego.zuju.technicalassessment

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.NotificationManager.IMPORTANCE_HIGH
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.core.app.NotificationManagerCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.csandiego.zuju.technicalassessment.compose.HomeScreen
import dev.csandiego.zuju.technicalassessment.compose.MatchDetailScreen
import dev.csandiego.zuju.technicalassessment.compose.TeamDetailScreen
import dev.csandiego.zuju.technicalassessment.data.Match
import dev.csandiego.zuju.technicalassessment.data.Team
import dev.csandiego.zuju.technicalassessment.ktor.KtorService
import dev.csandiego.zuju.technicalassessment.room.TechnicalAssessmentDatabase
import dev.csandiego.zuju.technicalassessment.service.DefaultReminderService
import dev.csandiego.zuju.technicalassessment.service.ReminderService
import dev.csandiego.zuju.technicalassessment.ui.theme.TechnicalAssessmentTheme

class MainActivity : ComponentActivity() {

    private lateinit var database: TechnicalAssessmentDatabase

    private lateinit var reminderService: ReminderService

    private val ktorService = KtorService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        database = TechnicalAssessmentDatabase.getInstance(applicationContext)
        reminderService = DefaultReminderService(database.reminderDao())
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel("Default", "Default", IMPORTANCE_HIGH).apply {
                description = "Default Channel"
            }.let {
                val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
                manager.createNotificationChannel(it)
            }
        }
        setContent {
            TechnicalAssessmentTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val launcher =
                        rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) {}
                    LaunchedEffect(Unit) {
                        if (!NotificationManagerCompat.from(this@MainActivity)
                                .areNotificationsEnabled() && Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU
                        ) {
                            launcher.launch(Manifest.permission.POST_NOTIFICATIONS)
                        }
                    }
                    NavHost(
                        navController = navController,
                        startDestination = "home",
                        modifier = Modifier.fillMaxSize()
                    ) {
                        composable("home") {
                            HomeScreen(
                                navController = navController,
                                teamService = ktorService,
                                matchService = ktorService
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
                                service = ktorService,
                                team = team
                            )
                        }
                        composable("match?date={date}&description={description}&home={home}&away={away}&winner={winner}&highlights={highlights}") {
                            val match = Match(
                                it.arguments!!.getString("date")!!,
                                it.arguments!!.getString("description")!!,
                                it.arguments!!.getString("home")!!,
                                it.arguments!!.getString("away")!!,
                                it.arguments!!.getString("winner")?.takeIf { it.isNotBlank() },
                                it.arguments!!.getString("highlights")?.takeIf { it.isNotBlank() }
                            )
                            MatchDetailScreen(
                                navController = navController,
                                service = reminderService,
                                match = match
                            )
                        }
                    }
                }
            }
        }
    }
}
