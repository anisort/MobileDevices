package com.example.testproject

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    private val questions = listOf(
        "Is the sky blue?", // true
        "Is fire cold?", // false
        "Is water wet?" // true
    )

    private val answers = listOf(true, false, true)
    private var currentQuestionIndex = 0
    private var score = 0
    private var username: String? = null
    private lateinit var usernameTextView: TextView
    private lateinit var questionTextView: TextView
    private lateinit var answerGroup: RadioGroup
    private lateinit var buttonPrevious: Button
    private lateinit var buttonNext: Button
    private lateinit var buttonFinish: Button

    private val SETTINGS_REQUEST_CODE = 1 // Код запиту для налаштувань

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
        setContentView(R.layout.activity_main)

        // Отримуємо ім'я користувача
        username = intent.getStringExtra("USERNAME")

        // Ініціалізуємо елементи інтерфейсу
        usernameTextView = findViewById(R.id.username_quiz)
        questionTextView = findViewById(R.id.question_text)
        answerGroup = findViewById(R.id.answer_group)
        buttonPrevious = findViewById(R.id.button_previous)
        buttonNext = findViewById(R.id.button_next)
        buttonFinish = findViewById(R.id.button_finish)

        usernameTextView.text = "Logged in as: $username"
        showQuestion()

        buttonNext.setOnClickListener {
            if (validateAnswer()) {
                if (currentQuestionIndex < questions.size - 1) {
                    currentQuestionIndex++
                    showQuestion()
                } else {
                    buttonFinish.visibility = View.VISIBLE
                    buttonNext.visibility = View.GONE
                }
            }
        }

        buttonPrevious.setOnClickListener {
            if (currentQuestionIndex > 0) {
                currentQuestionIndex--
                showQuestion()
            }
        }

        buttonFinish.setOnClickListener {
            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("SCORE", score)
            intent.putExtra("USERNAME", username)
            startActivity(intent)
        }

        findViewById<Button>(R.id.button_settings).setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            intent.putExtra("USERNAME", username) // Передаємо ім'я користувача в налаштування
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

    private fun showQuestion() {
        questionTextView.text = questions[currentQuestionIndex]
        answerGroup.clearCheck()
        buttonFinish.visibility = View.GONE
    }

    private fun validateAnswer(): Boolean {
        val selectedId = answerGroup.checkedRadioButtonId
        if (selectedId == -1) {
            return false
        }

        val selectedAnswer = findViewById<RadioButton>(selectedId)
        if ((selectedAnswer.text == "True" && answers[currentQuestionIndex]) ||
            (selectedAnswer.text == "False" && !answers[currentQuestionIndex])
        ) {
            score++
        }

        return true
    }
}
