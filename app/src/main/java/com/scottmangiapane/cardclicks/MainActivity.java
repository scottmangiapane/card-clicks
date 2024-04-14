package com.scottmangiapane.cardclicks;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private TextView highScore1;
    private TextView highScore2;
    private TextView highScore3;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView buttonHelp = findViewById(R.id.button_about);
        TextView buttonPlay = findViewById(R.id.button_play);
        highScore1 = findViewById(R.id.score_1);
        highScore2 = findViewById(R.id.score_2);
        highScore3 = findViewById(R.id.score_3);
        sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        buttonHelp.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, HelpActivity.class);
            startActivity(intent);
            overridePendingTransition(R.transition.slide_right_1, R.transition.slide_right_2);
        });
        buttonPlay.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, GameActivity.class);
            startActivity(intent);
            overridePendingTransition(R.transition.slide_left_1, R.transition.slide_left_2);
        });
        highScore1.setText(sp.getInt("score_1", 0) + " " + getString(R.string.groups));
        highScore2.setText(sp.getInt("score_2", 0) + " " + getString(R.string.groups));
        highScore3.setText(sp.getInt("score_3", 0) + " " + getString(R.string.groups));
        if (sp.getInt("score_1", 0) == 1)
            highScore1.setText(sp.getInt("score_1", 0) + " " + getString(R.string.group));
        if (sp.getInt("score_2", 0) == 1)
            highScore2.setText(sp.getInt("score_2", 0) + " " + getString(R.string.group));
        if (sp.getInt("score_3", 0) == 1)
            highScore3.setText(sp.getInt("score_3", 0) + " " + getString(R.string.group));
        if (sp.getInt("launch_count", 0) == 5) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.rate_card_clicks)
                    .setMessage(R.string.rate_message)
                    .setPositiveButton(R.string.sure, (dialog, id) -> {
                        try {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + getPackageName())));
                        } catch (ActivityNotFoundException e) {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName())));
                        }
                    })
                    .setNegativeButton(R.string.no_thanks, (dialog, id) -> {
                    })
                    .create().show();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        highScore1.setText(sp.getInt("score_1", 0) + " " + getString(R.string.groups));
        highScore2.setText(sp.getInt("score_2", 0) + " " + getString(R.string.groups));
        highScore3.setText(sp.getInt("score_3", 0) + " " + getString(R.string.groups));
        if (sp.getInt("score_1", 0) == 1)
            highScore1.setText(sp.getInt("score_1", 0) + " " + getString(R.string.group));
        if (sp.getInt("score_2", 0) == 1)
            highScore2.setText(sp.getInt("score_2", 0) + " " + getString(R.string.group));
        if (sp.getInt("score_3", 0) == 1)
            highScore3.setText(sp.getInt("score_3", 0) + " " + getString(R.string.group));
    }
}