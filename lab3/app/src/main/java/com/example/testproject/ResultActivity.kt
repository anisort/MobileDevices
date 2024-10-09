package com.example.testproject

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {

    private val SETTINGS_REQUEST_CODE = 1
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
        setContentView(R.layout.activity_result)

        val score = intent.getIntExtra("SCORE", 0)
        val username = intent.getStringExtra("USERNAME")

        findViewById<TextView>(R.id.username_results).text = "Logged in as: $username"
        findViewById<TextView>(R.id.result_text).text = "Score: $score"

        findViewById<Button>(R.id.button_logout).setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.button_settings).setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            intent.putExtra("USERNAME", username)
            startActivityForResult(intent, SETTINGS_REQUEST_CODE)
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == SETTINGS_REQUEST_CODE) {
            val theme = PreferenceManager.getTheme(this)
            if (theme == "dark") {
                setTheme(R.style.Theme_MyApp_Dark)
            } else {
                setTheme(R.style.Theme_MyApp_Light)
            }
            recreate()
        }
    }
}
