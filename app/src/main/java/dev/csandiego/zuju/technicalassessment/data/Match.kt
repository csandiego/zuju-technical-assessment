package dev.csandiego.zuju.technicalassessment.data

import kotlinx.serialization.Serializable
import java.text.SimpleDateFormat
import java.util.Locale

@Serializable
data class Match(
    val date: String,
    val description: String,
    val home: String,
    val away: String,
    val winner: String? = null,
    val highlights: String? = null
) {
    val dateTime
        get() = SimpleDateFormat(
            "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
            Locale.ENGLISH
        ).parse(date)!!
}