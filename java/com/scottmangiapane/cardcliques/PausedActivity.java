package com.scottmangiapane.cardcliques;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class PausedActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paused);
        findViewById(R.id.button_resume_game).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.transition.slide_right_1, R.transition.slide_right_2);
            }
        });
    }

    @Override
    public void onBackPressed() {
        return;
    }
}
