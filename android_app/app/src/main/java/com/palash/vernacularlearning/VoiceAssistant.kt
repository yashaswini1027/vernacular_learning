package com.palash.vernacularlearning

import android.app.Activity
import android.content.Intent
import android.speech.RecognizerIntent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts

class VoiceAssistant(
    private val activity: Activity,
    private val onTranscriptReady: (String) -> Unit
) {
    private lateinit var speechLauncher: ActivityResultLauncher<Intent>

    fun bind(launcher: ActivityResultLauncher<Intent>) {
        speechLauncher = launcher
    }

    fun startListening() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(RecognizerIntent.EXTRA_LANGUAGE, "hi-IN")
            putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            putExtra(RecognizerIntent.EXTRA_PROMPT, "Say a Hindi classroom instruction")
        }
        speechLauncher.launch(intent)
    }

    fun handleResult(resultCode: Int, data: Intent?) {
        if (resultCode != Activity.RESULT_OK || data == null) return
        val matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
        val transcript = matches?.firstOrNull() ?: ""
        if (transcript.isNotBlank()) {
            onTranscriptReady(transcript)
        }
    }
}
