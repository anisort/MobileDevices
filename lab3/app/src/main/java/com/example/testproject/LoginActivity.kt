package com.example.testproject

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var usernameEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var errorTextView: TextView

    private lateinit var sharedPreferences: SharedPreferences
    private val SETTINGS_REQUEST_CODE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        // Зчитуємо обрану тему з SharedPreferences
        sharedPreferences = getSharedPreferences("AppPreferences", MODE_PRIVATE)
        val theme = PreferenceManager.getTheme(this)

        // Встановлюємо тему перед викликом super.onCreate
        if (theme == "dark") {
            setTheme(R.style.Theme_MyApp_Dark)
        } else {
            setTheme(R.style.Theme_MyApp_Light)
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        usernameEditText = findViewById(R.id.username_login)
        loginButton = findViewById(R.id.button_login)
        errorTextView = findViewById(R.id.error_text)

        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString().trim()
            if (username.isNotEmpty()) {
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("USERNAME", username)
                startActivity(intent)
                finish() // Завершити цю активність
            } else {
                errorTextView.visibility = View.VISIBLE
                errorTextView.text = "Please enter your username."
            }
        }

        findViewById<Button>(R.id.button_settings).setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            intent.putExtra("USERNAME", "") // Передаємо ім'я користувача в налаштування
            startActivityForResult(intent, SETTINGS_REQUEST_CODE) // Запускаємо налаштування
        }
    }
    // Обробка результату з SettingsActivity
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == SETTINGS_REQUEST_CODE) {
            // Оновлюємо тему, якщо вона була змінена
            val theme = PreferenceManager.getTheme(this)
            if (theme == "dark") {
                setTheme(R.style.Theme_MyApp_Dark)
            } else {
                setTheme(R.style.Theme_MyApp_Light)
            }
            recreate() // Перезапускаємо активність, щоб застосувати нову тему
        }
    }
}

