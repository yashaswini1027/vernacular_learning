package com.palash.vernacularlearning

object AppData {
    val languages = listOf("Santhali", "Ho", "Mundari")

    val lessonTemplates = mapOf(
        "Santhali" to listOf(
            "आओ, हम गिनते हैं।",
            "चित्र दिखाएँ और पहचानें।",
            "सही उत्तर चुनें।"
        ),
        "Ho" to listOf(
            "Aao, hum ginti karein.",
            "Chitra dekhao aur pehchano.",
            "Sahi uttar chuno."
        ),
        "Mundari" to listOf(
            "Aao, hum ginti kari.",
            "Chitra dekhon aur pehchano.",
            "Sahi uttar chuno."
        )
    )

    val worksheetItems = mapOf(
        "Santhali" to listOf(
            "1 से 5 तक गिनें",
            "चित्र पहचानें",
            "सही उत्तर चुनें",
            "फ्लैशकार्ड बनाएं"
        ),
        "Ho" to listOf(
            "1 se 5 tak ginti karein",
            "Chitra pehchano",
            "Sahi uttar chuno",
            "Flashcard banaiye"
        ),
        "Mundari" to listOf(
            "1 se 5 tak ginti kari",
            "Chitra pehchana",
            "Sahi uttar chuno",
            "Flashcard banaiye"
        )
    )
}
