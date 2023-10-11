package dev.csandiego.zuju.technicalassessment

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import dev.csandiego.zuju.technicalassessment.data.Match

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MatchDetailScreen(navController: NavHostController, match: Match) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = match.description) },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Navigate up")
                    }
                }
            )
        },
    ) { padding ->
        MatchDetail(match = match, modifier = Modifier.padding(padding))
    }
}