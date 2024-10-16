package com.example.testproject

import android.content.Context
import android.content.SharedPreferences

object PreferenceManager {
    private const val PREF_NAME = "AppPreferences"
    private const val THEME_KEY = "theme"

    fun saveTheme(context: Context, theme: String) {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(THEME_KEY, theme)
        editor.apply()
    }

    fun getTheme(context: Context): String {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getString(THEME_KEY, "") ?: "light"
    }
}