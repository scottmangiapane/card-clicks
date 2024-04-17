package com.scottmangiapane.cardclicks;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

public class MainActivity extends AppCompatActivity {
    private TextView[] highScores;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        this.highScores = new TextView[] {
                findViewById(R.id.score_1),
                findViewById(R.id.score_2),
                findViewById(R.id.score_3)
        };
        this.sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        findViewById(R.id.button_help).setOnClickListener(v -> launchHelp());
        findViewById(R.id.button_play).setOnClickListener(v -> launchGame());

        renderHighScores();
        showRatingDialog();
    }

    @Override
    public void onResume() {
        super.onResume();
        renderHighScores();
    }

    private void renderHighScores() {
        highScores[0].setText(sp.getInt("score_1", 0) == 1
                ? getString(R.string.set, sp.getInt("score_1", 0))
                : getString(R.string.sets, sp.getInt("score_1", 0)));
        highScores[1].setText(sp.getInt("score_2", 0) == 1
                ? getString(R.string.set, sp.getInt("score_2", 0))
                : getString(R.string.sets, sp.getInt("score_2", 0)));
        highScores[2].setText(sp.getInt("score_3", 0) == 1
                ? getString(R.string.set, sp.getInt("score_3", 0))
                : getString(R.string.sets, sp.getInt("score_3", 0)));
    }

    private void launchHelp() {
        Intent intent = new Intent(MainActivity.this, HelpActivity.class);
        startActivity(intent);
        overridePendingTransition(R.transition.slide_right_1, R.transition.slide_right_2);
    }

    private void launchGame() {
        Intent intent = new Intent(MainActivity.this, GameActivity.class);
        startActivity(intent);
        overridePendingTransition(R.transition.slide_left_1, R.transition.slide_left_2);
    }

    private void showRatingDialog() {
        if (sp.getInt("launch_count", 0) == 10) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.rate_card_clicks)
                    .setMessage(R.string.rate_message)
                    .setPositiveButton(R.string.sure, (dialog, id) -> {
                        try {
                            startActivity(new Intent(
                                    Intent.ACTION_VIEW,
                                    Uri.parse("market://details?id=" + getPackageName())));
                        } catch (ActivityNotFoundException e) {
                            startActivity(new Intent(
                                    Intent.ACTION_VIEW,
                                    Uri.parse("https://play.google.com/store/apps/details?id="
                                            + getPackageName())));
                        }
                    })
                    .setNegativeButton(R.string.no_thanks, (dialog, id) -> {})
                    .create().show();
        }
    }
}