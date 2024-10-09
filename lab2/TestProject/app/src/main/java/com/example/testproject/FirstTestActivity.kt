package com.example.testproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class FirstTestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_test)

        val buttonToThird = findViewById<Button>(R.id.buttonToSecond)
        buttonToThird.setOnClickListener {
            val intent = Intent(this, SecondTestActivity::class.java)
            startActivity(intent)
        }
    }
}
