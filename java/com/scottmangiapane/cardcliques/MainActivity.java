package com.scottmangiapane.cardcliques;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private ImageView buttonHelp;
    private ImageView buttonPlay;
    private TextView highScore1;
    private TextView highScore2;
    private TextView highScore3;
    private SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.parseColor("#303030"));
            getWindow().setNavigationBarColor(Color.parseColor("#303030"));
        }
        buttonHelp = (ImageView) findViewById(R.id.button_about);
        buttonPlay = (ImageView) findViewById(R.id.button_play);
        highScore1 = (TextView) findViewById(R.id.score_1);
        highScore2 = (TextView) findViewById(R.id.score_2);
        highScore3 = (TextView) findViewById(R.id.score_3);
        settings = getApplicationContext().getSharedPreferences("SCORE_DATA", Context.MODE_PRIVATE);
        buttonHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HelpButton.class);
                startActivity(intent);
                overridePendingTransition(R.transition.slide_right_1, R.transition.slide_right_2);
            }
        });
        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, StandardGame.class);
                startActivity(intent);
                overridePendingTransition(R.transition.slide_left_1, R.transition.slide_left_2);
            }
        });
        highScore1.setText(settings.getInt("HIGH_SCORE_1", 0) + " cliques");
        highScore2.setText(settings.getInt("HIGH_SCORE_2", 0) + " cliques");
        highScore3.setText(settings.getInt("HIGH_SCORE_3", 0) + " cliques");
    }

    @Override
    public void onResume() {
        super.onResume();
        highScore1.setText(settings.getInt("HIGH_SCORE_1", 0) + " cliques");
        highScore2.setText(settings.getInt("HIGH_SCORE_2", 0) + " cliques");
        highScore3.setText(settings.getInt("HIGH_SCORE_3", 0) + " cliques");
    }
}