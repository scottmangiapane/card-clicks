package com.scottmangiapane.cardcliques;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class StandardGame extends AppCompatActivity {
    public static AppCompatActivity gameActivity;
    private boolean gameOngoing;
    private int score;
    private int secondsRemaining;
    private int selectedCard1x;
    private int selectedCard1y;
    private int selectedCard2x;
    private int selectedCard2y;
    private int selectedCard3x;
    private int selectedCard3y;
    private Clique clique;
    private CountDownTimer timer;
    private TextView buttonPauseRestart;
    private TextView buttonStopExit;
    private ImageView[][] gridDraw;
    private TextView finalScore;
    private TextView timeCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_standard);
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.parseColor("#303030"));
            getWindow().setNavigationBarColor(Color.parseColor("#303030"));
        }
        gameActivity = this;
        gameOngoing = true;
        score = 0;
        secondsRemaining = 121000;
        selectedCard1x = -1;
        selectedCard1y = -1;
        selectedCard2x = -1;
        selectedCard2y = -1;
        selectedCard3x = -1;
        selectedCard3y = -1;
        clique = new Clique(4, 3);
        buttonPauseRestart = (TextView) findViewById(R.id.button_pause_restart);
        buttonStopExit = (TextView) findViewById(R.id.button_stop_exit);
        gridDraw = new ImageView[][]{
                {(ImageView) findViewById(R.id.imageView_0_0),
                        (ImageView) findViewById(R.id.imageView_0_1),
                        (ImageView) findViewById(R.id.imageView_0_2)},
                {(ImageView) findViewById(R.id.imageView_1_0),
                        (ImageView) findViewById(R.id.imageView_1_1),
                        (ImageView) findViewById(R.id.imageView_1_2)},
                {(ImageView) findViewById(R.id.imageView_2_0),
                        (ImageView) findViewById(R.id.imageView_2_1),
                        (ImageView) findViewById(R.id.imageView_2_2)},
                {(ImageView) findViewById(R.id.imageView_3_0),
                        (ImageView) findViewById(R.id.imageView_3_1),
                        (ImageView) findViewById(R.id.imageView_3_2)}};
        finalScore = (TextView) findViewById(R.id.final_score);
        timeCount = (TextView) findViewById(R.id.time_count);
        for (int i1 = 0; i1 < clique.height(); i1++)
            for (int i2 = 0; i2 < clique.width(); i2++) {
                clique.setNewCard(i1, i2);
                final int ia = i1;
                final int ib = i2;
                gridDraw[i1][i2].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (selectedCard1x == -1) {
                            selectedCard1x = ia;
                            selectedCard1y = ib;
                        } else if (selectedCard1x == ia && selectedCard1y == ib) {
                            if (selectedCard2x != -1) {
                                selectedCard1x = selectedCard2x;
                                selectedCard1y = selectedCard2y;
                                selectedCard2x = -1;
                                selectedCard2y = -1;
                            } else {
                                selectedCard1x = -1;
                                selectedCard1y = -1;
                            }
                        } else if (selectedCard2x == -1) {
                            selectedCard2x = ia;
                            selectedCard2y = ib;
                        } else if (selectedCard1x == ia && selectedCard1y == ib) {
                            selectedCard1x = -1;
                            selectedCard1y = -1;
                        } else if (selectedCard2x == ia && selectedCard2y == ib) {
                            selectedCard2x = -1;
                            selectedCard2y = -1;
                        } else {
                            selectedCard3x = ia;
                            selectedCard3y = ib;
                            refreshDraw();
                            if (clique.isSet(selectedCard1x, selectedCard1y,
                                    selectedCard2x, selectedCard2y,
                                    selectedCard3x, selectedCard3y)) {
                                finalScore.setText("Score: " + ++score);
                                clique.setNewCard(selectedCard1x, selectedCard1y);
                                clique.setNewCard(selectedCard2x, selectedCard2y);
                                clique.setNewCardCheckSetPossible(selectedCard3x, selectedCard3y);
                            }
                            selectedCard1x = -1;
                            selectedCard1y = -1;
                            selectedCard2x = -1;
                            selectedCard2y = -1;
                            selectedCard3x = -1;
                            selectedCard3y = -1;

                        }
                        refreshDraw();
                    }
                });
            }
        buttonPauseRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StandardGame.this, Paused.class);
                startActivity(intent);
                overridePendingTransition(R.transition.slide_left_1, R.transition.slide_left_2);
            }
        });
        buttonStopExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.cancel();
                timer.onFinish();
            }
        });
        clique.refreshAllValues();
        clique.setNewCardCheckSetPossible((int) (Math.random() * 3), (int) (Math.random() * 2));
        refreshDraw();
    }

    public void refreshDraw() {
        for (int i1 = 0; i1 < clique.height(); i1++)
            for (int i2 = 0; i2 < clique.width(); i2++) {
                gridDraw[i1][i2].setColorFilter(null);
                if (clique.cards[i1][i2].drawable == 0)
                    gridDraw[i1][i2].setImageResource(R.drawable.card_empty);
                else
                    gridDraw[i1][i2].setImageResource(clique.cards[i1][i2].drawable);
                if ((i1 != selectedCard1x || i2 != selectedCard1y)
                        && (i1 != selectedCard2x || i2 != selectedCard2y)
                        && (i1 != selectedCard3x || i2 != selectedCard3y))
                    if (selectedCard1x + selectedCard1y
                            + selectedCard2x + selectedCard2y
                            + selectedCard3x + selectedCard3y > -6)
                        gridDraw[i1][i2].setColorFilter(Color.argb(150, 0, 0, 0));
            }
    }

    @Override
    public void onBackPressed() {
        if (gameOngoing) {
            Intent intent = new Intent(StandardGame.this, Paused.class);
            startActivity(intent);
            overridePendingTransition(R.transition.slide_left_1, R.transition.slide_left_2);
        }
        return;
    }

    @Override
    public void onPause() {
        super.onPause();
        if (gameOngoing)
            timer.cancel();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (gameOngoing) {
            timer = new CountDownTimer(secondsRemaining, 1000) {
                public void onTick(long millisUntilFinished) {
                    secondsRemaining = (int) millisUntilFinished;
                    timeCount.setText("0" + (millisUntilFinished / 60000) + ":"
                            + (((millisUntilFinished / 1000) % 60) / 10)
                            + (((millisUntilFinished / 1000) % 60) % 10));
                }

                public void onFinish() {
                    SharedPreferences settings = getApplicationContext()
                            .getSharedPreferences("SCORE_DATA", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = settings.edit();
                    if (score > settings.getInt("HIGH_SCORE_3", 0)) {
                        if (score > settings.getInt("HIGH_SCORE_2", 0)) {
                            if (score > settings.getInt("HIGH_SCORE_1", 0)) {
                                editor.putInt("HIGH_SCORE_3", settings.getInt("HIGH_SCORE_2", 0));
                                editor.putInt("HIGH_SCORE_2", settings.getInt("HIGH_SCORE_1", 0));
                                editor.putInt("HIGH_SCORE_1", score);
                            } else {
                                editor.putInt("HIGH_SCORE_3", settings.getInt("HIGH_SCORE_2", 0));
                                editor.putInt("HIGH_SCORE_2", score);
                            }
                        } else {
                            editor.putInt("HIGH_SCORE_3", score);
                        }
                    }
                    editor.apply();
                    gameOngoing = false;
                    buttonPauseRestart.setBackgroundColor(getResources().getColor(R.color.red));
                    buttonPauseRestart.setText("RESTART");
                    buttonPauseRestart.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(StandardGame.this, StandardGame.class);
                            startActivity(intent);
                            gameActivity.finish();
                            overridePendingTransition(R.transition.zoom_1, R.transition.zoom_2);
                        }
                    });
                    buttonStopExit.setText("EXIT");
                    buttonStopExit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            finish();
                            overridePendingTransition(R.transition.slide_right_1, R.transition.slide_right_2);
                        }
                    });
                    selectedCard1x = -1;
                    selectedCard1y = -1;
                    selectedCard2x = -1;
                    selectedCard2y = -1;
                    selectedCard3x = -1;
                    selectedCard3y = -1;
                    int[][] grid = new int[4][3];
                    for (int i1 = 0; i1 < 10; i1++)
                        for (int i2 = i1 + 1; i2 < 11; i2++)
                            for (int i3 = i2 + 1; i3 < 12; i3++)
                                if (clique.isSet(i1 / 3, i1 % 3, i2 / 3, i2 % 3, i3 / 3, i3 % 3)) {
                                    grid[i1 / 3][i1 % 3] = 1;
                                    grid[i2 / 3][i2 % 3] = 1;
                                    grid[i3 / 3][i3 % 3] = 1;
                                }
                    for (int i1 = 0; i1 < clique.height(); i1++)
                        for (int i2 = 0; i2 < clique.width(); i2++) {
                            gridDraw[i1][i2].setColorFilter(null);
                            if (grid[i1][i2] == 0) {
                                gridDraw[i1][i2].setColorFilter(Color.argb(150, 0, 0, 0));
                            }
                            gridDraw[i1][i2].setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                }
                            });
                        }
                    finalScore.setText("Score: " + score + "     High: "
                            + settings.getInt("HIGH_SCORE_1", 0));
                    timeCount.setText("00:00");
                }
            }.start();
        }
    }
}
