package com.scottmangiapane.cardcliques;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView highScore1;
    private TextView highScore2;
    private TextView highScore3;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView buttonHelp = (TextView) findViewById(R.id.button_about);
        TextView buttonPlay = (TextView) findViewById(R.id.button_play);
        highScore1 = (TextView) findViewById(R.id.score_1);
        highScore2 = (TextView) findViewById(R.id.score_2);
        highScore3 = (TextView) findViewById(R.id.score_3);
        sp = getApplicationContext().getSharedPreferences("SCORE_DATA", Context.MODE_PRIVATE);
        buttonHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HelpActivity.class);
                startActivity(intent);
                overridePendingTransition(R.transition.slide_right_1, R.transition.slide_right_2);
            }
        });
        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GameActivity.class);
                startActivity(intent);
                overridePendingTransition(R.transition.slide_left_1, R.transition.slide_left_2);
            }
        });
        highScore1.setText(sp.getInt("HIGH_SCORE_1", 0) + " cliques");
        highScore2.setText(sp.getInt("HIGH_SCORE_2", 0) + " cliques");
        highScore3.setText(sp.getInt("HIGH_SCORE_3", 0) + " cliques");
        if (sp.getInt("HIGH_SCORE_1", 0) == 1)
            highScore1.setText(sp.getInt("HIGH_SCORE_1", 0) + " clique");
        if (sp.getInt("HIGH_SCORE_2", 0) == 1)
            highScore2.setText(sp.getInt("HIGH_SCORE_2", 0) + " clique");
        if (sp.getInt("HIGH_SCORE_3", 0) == 1)
            highScore3.setText(sp.getInt("HIGH_SCORE_3", 0) + " clique");
    }

    @Override
    public void onResume() {
        super.onResume();
        highScore1.setText(sp.getInt("HIGH_SCORE_1", 0) + " cliques");
        highScore2.setText(sp.getInt("HIGH_SCORE_2", 0) + " cliques");
        highScore3.setText(sp.getInt("HIGH_SCORE_3", 0) + " cliques");
        if (sp.getInt("HIGH_SCORE_1", 0) == 1)
            highScore1.setText(sp.getInt("HIGH_SCORE_1", 0) + " clique");
        if (sp.getInt("HIGH_SCORE_2", 0) == 1)
            highScore2.setText(sp.getInt("HIGH_SCORE_2", 0) + " clique");
        if (sp.getInt("HIGH_SCORE_3", 0) == 1)
            highScore3.setText(sp.getInt("HIGsH_SCORE_3", 0) + " clique");
    }
}