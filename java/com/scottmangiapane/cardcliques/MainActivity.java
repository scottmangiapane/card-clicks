package com.scottmangiapane.cardcliques;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
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
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.parseColor("#303030"));
            getWindow().setNavigationBarColor(Color.parseColor("#303030"));
        }
        TextView buttonHelp = (TextView) findViewById(R.id.button_about);
        TextView buttonPlay = (TextView) findViewById(R.id.button_play);
        highScore1 = (TextView) findViewById(R.id.score_1);
        highScore2 = (TextView) findViewById(R.id.score_2);
        highScore3 = (TextView) findViewById(R.id.score_3);
        sp = getApplicationContext().getSharedPreferences("SCORE_DATA", Context.MODE_PRIVATE);
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
        highScore1.setText(sp.getInt("HIGH_SCORE_1", 0) + " cliques");
        highScore2.setText(sp.getInt("HIGH_SCORE_2", 0) + " cliques");
        highScore3.setText(sp.getInt("HIGH_SCORE_3", 0) + " cliques");
        if (sp.getInt("HIGH_SCORE_1", 0) == 1)
            highScore1.setText(sp.getInt("HIGH_SCORE_1", 0) + " clique");
        if (sp.getInt("HIGH_SCORE_2", 0) == 1)
            highScore2.setText(sp.getInt("HIGH_SCORE_2", 0) + " clique");
        if (sp.getInt("HIGH_SCORE_3", 0) == 1)
            highScore3.setText(sp.getInt("HIGH_SCORE_3", 0) + " clique");
        if (sp.getInt("launch_count", 0) == 5) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Rate Card Cliques")
                    .setMessage("Would you like to rate Card Cliques on the Google Play store?")
                    .setPositiveButton("Sure", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            try {
                                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + getPackageName())));
                            } catch (android.content.ActivityNotFoundException e) {
                                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName())));
                            }
                        }
                    })
                    .setNegativeButton("No thanks", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                        }
                    })
                    .create().show();
        }
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("launch_count", sp.getInt("launch_count", 0) + 1);
        editor.apply();
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