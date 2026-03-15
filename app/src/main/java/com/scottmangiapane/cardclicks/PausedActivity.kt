package com.scottmangiapane.cardclicks

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class PausedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paused)
        findViewById<View>(R.id.button_resume_game).setOnClickListener { finish() }
    }
}
