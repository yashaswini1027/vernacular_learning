package com.palash.vernacularlearning

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PALASHApp()
        }
    }
}

@Composable
fun PALASHApp() {
    MaterialTheme(
        colorScheme = MaterialTheme.colorScheme.copy(
            primary = Color(0xFF0F766E),
            secondary = Color(0xFFF59E0B),
            background = Color(0xFFEEF7F5),
            surface = Color.White
        )
    ) {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            TeacherDashboard()
        }
    }
}

@Composable
fun TeacherDashboard() {
    var selectedTab by remember { mutableStateOf("Translation") }
    var inputText by remember { mutableStateOf("गिनती के साथ बच्चों को 5 तक zählen करें।") }
    var selectedLanguage by remember { mutableStateOf(AppData.languages.first()) }
    var translatedText by remember { mutableStateOf(AppData.languages.first().sampleTranslation) }
    var lessonText by remember {
        mutableStateOf(
            "उद्देश्य: ${AppData.languages.first().lessonPacks.first().objective}\n\n" +
                "गतिविधि: ${AppData.languages.first().lessonPacks.first().activity}\n\n" +
                "मूल्यांकन: ${AppData.languages.first().lessonPacks.first().assessment}"
        )
    }

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = "PALASH",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF0F766E)
                )
                Text(
                    text = "Mother Tongue Learning Assistant",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF18312C)
                )
            }
            Card(
                shape = RoundedCornerShape(999.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFDCFCE7))
            ) {
                Text(
                    text = "Offline ready",
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                    color = Color(0xFF166534),
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            listOf("Translation", "Lessons", "Worksheet").forEach { tab ->
                Button(
                    onClick = { selectedTab = tab },
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (selectedTab == tab) Color(0xFF0F766E) else Color(0xFFEAF3F2)
                    )
                ) {
                    Text(text = tab, color = if (selectedTab == tab) Color.White else Color(0xFF18312C))
                }
            }
        }

        when (selectedTab) {
            "Translation" -> TranslationTab(
                inputText = inputText,
                onInputTextChange = { inputText = it },
                selectedLanguage = selectedLanguage,
                onLanguageChange = { language ->
                    selectedLanguage = language
                    translatedText = language.sampleTranslation
                },
                translatedText = translatedText,
                onTranslate = {
                    translatedText = selectedLanguage.sampleTranslation
                }
            )
            "Lessons" -> LessonTab(
                selectedLanguage = selectedLanguage,
                lessonText = lessonText,
                onGenerate = {
                    val pack = selectedLanguage.lessonPacks.first()
                    lessonText = "Title: ${pack.title}\n\nObjective: ${pack.objective}\n\nActivity: ${pack.activity}\n\nAssessment: ${pack.assessment}"
                }
            )
            else -> WorksheetTab(selectedLanguage = selectedLanguage)
        }
    }
}

@Composable
fun TranslationTab(
    inputText: String,
    onInputTextChange: (String) -> Unit,
    selectedLanguage: LanguageDetail,
    onLanguageChange: (LanguageDetail) -> Unit,
    translatedText: String,
    onTranslate: () -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = RoundedCornerShape(18.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text("Target language", fontWeight = FontWeight.Bold)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    AppData.languages.forEach { language ->
                        Button(
                            onClick = { onLanguageChange(language) },
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(10.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (selectedLanguage.code == language.code) Color(0xFF0F766E) else Color(0xFFEAF3F2)
                            )
                        ) {
                            Text(
                                text = language.label,
                                color = if (selectedLanguage.code == language.code) Color.White else Color(0xFF18312C)
                            )
                        }
                    }
                }
            }
        }

        OutlinedTextField(
            value = inputText,
            onValueChange = onInputTextChange,
            label = { Text("Hindi classroom text") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = onTranslate,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0F766E))
        ) {
            Text("Translate")
        }

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF5FBFA))
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Translated text", color = Color(0xFF5E716D), fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
                Text(translatedText, fontSize = 18.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Latency target: under 3s", color = Color(0xFF0F766E), fontWeight = FontWeight.Medium)
            }
        }
    }
}

@Composable
fun LessonTab(
    selectedLanguage: LanguageDetail,
    lessonText: String,
    onGenerate: () -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Button(
            onClick = onGenerate,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF59E0B))
        ) {
            Text("Generate lesson")
        }

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF8ED))
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("${selectedLanguage.label} lesson pack", fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
                Text(lessonText)
            }
        }
    }
}

@Composable
fun WorksheetTab(selectedLanguage: LanguageDetail) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF7FBFA))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Bilingual worksheet", fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Spacer(modifier = Modifier.height(12.dp))
            selectedLanguage.worksheetTasks.forEachIndexed { index, item ->
                Text("${index + 1}. $item")
                if (index != selectedLanguage.worksheetTasks.lastIndex) {
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}
