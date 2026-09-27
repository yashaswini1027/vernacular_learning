package com.palash.vernacularlearning

import android.content.Context
import android.speech.tts.TextToSpeech
import java.util.Locale

class VernacularTts(context: Context) : TextToSpeech.OnInitListener {
    private val textToSpeech = TextToSpeech(context.applicationContext, this)
    private var ready = false

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val locale = Locale("hi", "IN")
            textToSpeech.language = locale
            ready = true
        }
    }

    fun speak(text: String, languageCode: String) {
        if (!ready) return
        val locale = when (languageCode) {
            "santhali", "ho", "mundari" -> Locale("hi", "IN")
            else -> Locale.getDefault()
        }
        textToSpeech.setLanguage(locale)
        textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, "palash-vernacular")
    }

    fun shutdown() {
        textToSpeech.stop()
        textToSpeech.shutdown()
    }
}
