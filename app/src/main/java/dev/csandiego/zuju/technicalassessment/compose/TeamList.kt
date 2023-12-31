package dev.csandiego.zuju.technicalassessment.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.csandiego.zuju.technicalassessment.R
import dev.csandiego.zuju.technicalassessment.data.Team
import dev.csandiego.zuju.technicalassessment.ui.theme.TechnicalAssessmentTheme

@Composable
fun TeamList(teams: List<Team>, onClick: (Team) -> Unit) {
    LazyColumn {
        item {
            Text(
                text = stringResource(id = R.string.team_list_title),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(16.dp, 8.dp)
            )
        }
        items(teams) {
            Text(
                text = it.name,
                fontSize = 18.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clickable { onClick(it) }
            )
            Divider()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TeamListPreview() {
    val teams = listOf(
        Team(
            "767ec50c-7fdb-4c3d-98f9-d6727ef8252b",
            "Team Red Dragons",
            "https://tstzj.s3.amazonaws.com/dragons.png"
        ),
        Team(
            "7b4d8114-742b-4410-971a-500162101158",
            "Team Cool Eagles",
            "https://tstzj.s3.amazonaws.com/eagle.png"
        ),
        Team(
            "efb06ca2-78bc-448e-bda5-a6af9eccdcd0",
            "Team Chill Elephants",
            "https://tstzj.s3.amazonaws.com/elephant.png"
        ),
        Team(
            "01490dfe-0bc7-42ad-b471-2fba2b9b8f5e",
            "Team Win Kings",
            "https://tstzj.s3.amazonaws.com/kings.png"
        ),
        Team(
            "849a0c56-eb8b-11ec-8ea0-0242ac120002",
            "Team Serious Lions",
            "https://tstzj.s3.amazonaws.com/lion.png"
        )
    )
    TechnicalAssessmentTheme {
        TeamList(teams = teams) {
        }
    }
}