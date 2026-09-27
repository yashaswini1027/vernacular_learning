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
            TranslationScreen()
        }
    }
}

@Composable
fun TranslationScreen() {
    var inputText by remember { mutableStateOf("गिनती के साथ बच्चों को 5 तक zählen करें।") }
    var targetLanguage by remember { mutableStateOf("Santhali") }
    var translatedText by remember {
        mutableStateOf("ginti के साथ बच्चों को 5 तक zählen करें।")
    }
    var lessonText by remember {
        mutableStateOf(
            "उद्देश्य: बच्चों को 1 से 10 तक संख्याओं को पहचानना सीखें।\n\n" +
                "गतिविधि: वस्तुओं को गिनें, चित्र पहचानें, सही उत्तर चुनें।\n\n" +
                "मूल्यांकन: कितने अंक दिख रहे हैं?"
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
        Text(
            text = "PALASH",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF0F766E)
        )
        Text(
            text = "Vernacular Classroom Assistant",
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF18312C)
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = RoundedCornerShape(18.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text("Select language", fontWeight = FontWeight.Bold)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    AppData.languages.forEach { language ->
                        Button(
                            onClick = {
                                targetLanguage = language
                                translatedText = when (language) {
                                    "Santhali" -> "ginti के साथ बच्चों को 5 तक zählen करें।"
                                    "Ho" -> "ginti te bachon ko 5 tak ganna sikhen."
                                    else -> "ginti re bacha ko 5 tak ganna sikhon."
                                }
                            },
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(10.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0F766E))
                        ) {
                            Text(language)
                        }
                    }
                }
            }
        }

        OutlinedTextField(
            value = inputText,
            onValueChange = { inputText = it },
            label = { Text("Hindi classroom text") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                translatedText = when (targetLanguage) {
                    "Santhali" -> "ginti के साथ बच्चों को 5 तक zählen करें।"
                    "Ho" -> "ginti te bachon ko 5 tak ganna sikhen."
                    else -> "ginti re bacha ko 5 tak ganna sikhon."
                }
            },
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
            }
        }

        Divider()

        Text(
            text = "FLN Lesson Generator",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF18312C)
        )

        Button(
            onClick = {
                val tokens = AppData.lessonTemplates[targetLanguage] ?: listOf("आओ, हम सीखते हैं।")
                lessonText = "उद्देश्य: बच्चों को 1 से 10 तक संख्याओं को पहचानना सीखें.\n\n" +
                    "गतिविधि:\n• ${tokens[0]}\n• ${tokens[1]}\n• ${tokens[2]}\n\n" +
                    "मूल्यांकन: $targetLanguage में कितने अंक दिख रहे हैं?"
            },
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
                Text(lessonText)
            }
        }

        Divider()

        Text(
            text = "Worksheet Output",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF18312C)
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF7FBFA))
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                val items = AppData.worksheetItems[targetLanguage] ?: listOf("Worksheet")
                items.forEachIndexed { index, item ->
                    Text("${index + 1}. $item")
                    if (index != items.lastIndex) Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}
