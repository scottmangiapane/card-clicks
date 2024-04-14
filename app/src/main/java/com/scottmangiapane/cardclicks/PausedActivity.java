package com.scottmangiapane.cardclicks;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class PausedActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paused);
        findViewById(R.id.button_resume_game).setOnClickListener(v -> {
            finish();
            overridePendingTransition(R.transition.slide_right_1, R.transition.slide_right_2);
        });
    }

    @Override
    public void onBackPressed() {}
}
