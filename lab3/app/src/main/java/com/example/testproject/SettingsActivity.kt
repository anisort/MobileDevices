package com.example.testproject

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SettingsActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        sharedPreferences = getSharedPreferences("AppPreferences", MODE_PRIVATE)
        val theme = PreferenceManager.getTheme(this)

        if (theme == "dark") {
            setTheme(R.style.Theme_MyApp_Dark)
        } else {
            setTheme(R.style.Theme_MyApp_Light)
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val username = intent.getStringExtra("USERNAME")
        findViewById<TextView>(R.id.username_settings).text = "Logged in as: $username"

        val themeGroup = findViewById<RadioGroup>(R.id.radio_group_theme)

        when (theme) {
            "dark" -> themeGroup.check(R.id.radio_dark)
            "light" -> themeGroup.check(R.id.radio_light)
        }

        themeGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.radio_light -> {
                    PreferenceManager.saveTheme(this, "light")
                    recreate()
                }
                R.id.radio_dark -> {
                    PreferenceManager.saveTheme(this, "dark")
                    recreate()
                }
            }
        }

        findViewById<Button>(R.id.button_save).setOnClickListener {
            finish()
        }
    }
}
