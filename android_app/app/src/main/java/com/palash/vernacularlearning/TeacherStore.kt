package com.palash.vernacularlearning

import android.content.Context

class TeacherStore(context: Context) {
    private val prefs = context.getSharedPreferences("palash_teacher_store", Context.MODE_PRIVATE)

    fun saveLanguage(code: String) {
        prefs.edit().putString("selected_language", code).apply()
    }

    fun loadLanguage(defaultCode: String): String {
        return prefs.getString("selected_language", defaultCode) ?: defaultCode
    }

    fun saveLesson(text: String) {
        prefs.edit().putString("saved_lesson", text).apply()
    }

    fun loadLesson(defaultText: String): String {
        return prefs.getString("saved_lesson", defaultText) ?: defaultText
    }
}
