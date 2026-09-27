package com.palash.vernacularlearning

data class LessonPack(
    val title: String,
    val objective: String,
    val activity: String,
    val assessment: String
)

data class LanguageDetail(
    val code: String,
    val label: String,
    val nativeLabel: String,
    val sampleTranslation: String,
    val lessonPacks: List<LessonPack>,
    val worksheetTasks: List<String>
)

object AppData {
    val languages = listOf(
        LanguageDetail(
            code = "santhali",
            label = "Santhali",
            nativeLabel = "ᱥᱚᱱᱛᱟᱞᱤ",
            sampleTranslation = "ginti के साथ बच्चों को 5 तक zählen करें।",
            lessonPacks = listOf(
                LessonPack(
                    title = "Counting and Recognition",
                    objective = "Children learn to identify numbers from 1 to 10 and say them clearly.",
                    activity = "Show objects; ask children to count aloud; match objects to cards.",
                    assessment = "Ask: How many objects are visible?"
                )
            ),
            worksheetTasks = listOf(
                "1 से 5 तक गिनें",
                "चित्र पहचानें",
                "सही उत्तर चुनें",
                "फ्लैशकार्ड बनाएं"
            )
        ),
        LanguageDetail(
            code = "ho",
            label = "Ho",
            nativeLabel = "Ho",
            sampleTranslation = "ginti te bachon ko 5 tak ganna sikhen.",
            lessonPacks = listOf(
                LessonPack(
                    title = "Ginti and Pehchaan",
                    objective = "Children learn to recognise and say numbers from 1 to 10.",
                    activity = "Display objects, count together, and match numbers to visuals.",
                    assessment = "Ask: How many objects are in the group?"
                )
            ),
            worksheetTasks = listOf(
                "1 se 5 tak ginti karein",
                "Chitra pehchano",
                "Sahi uttar chuno",
                "Flashcard banaiye"
            )
        ),
        LanguageDetail(
            code = "mundari",
            label = "Mundari",
            nativeLabel = "Muṇḍāri",
            sampleTranslation = "ginti re bacha ko 5 tak ganna sikhon.",
            lessonPacks = listOf(
                LessonPack(
                    title = "Ginti and Pehchaan",
                    objective = "Children practice counting and identifying numbers from 1 to 10.",
                    activity = "Count objects, identify images, and select correct answers.",
                    assessment = "Ask: How many items do you see?"
                )
            ),
            worksheetTasks = listOf(
                "1 se 5 tak ginti kari",
                "Chitra pehchana",
                "Sahi uttar chuno",
                "Flashcard banaiye"
            )
        )
    )
}
