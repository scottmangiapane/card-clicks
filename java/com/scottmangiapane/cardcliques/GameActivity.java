package com.scottmangiapane.cardcliques;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {
    public static AppCompatActivity activity;
    private boolean gameOngoing;
    private int score;
    private int secondsRemaining;
    private int selectedCard1x;
    private int selectedCard1y;
    private int selectedCard2x;
    private int selectedCard2y;
    private int selectedCard3x;
    private int selectedCard3y;
    private Deck deck;
    private CountDownTimer timer;
    private TextView buttonPauseRestart;
    private TextView buttonStopExit;
    private ImageView[][] gridDraw;
    private TextView scoreView;
    private TextView timeCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        activity = this;
        gameOngoing = true;
        score = 0;
        secondsRemaining = 121000;
        selectedCard1x = -1;
        selectedCard1y = -1;
        selectedCard2x = -1;
        selectedCard2y = -1;
        selectedCard3x = -1;
        selectedCard3y = -1;
        deck = new Deck(4, 3);
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
        scoreView = (TextView) findViewById(R.id.final_score);
        timeCount = (TextView) findViewById(R.id.time_count);
        for (int i1 = 0; i1 < deck.height(); i1++)
            for (int i2 = 0; i2 < deck.width(); i2++) {
                deck.setNewCard(i1, i2);
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
                            if (deck.isSet(selectedCard1x, selectedCard1y,
                                    selectedCard2x, selectedCard2y,
                                    selectedCard3x, selectedCard3y)) {
                                String scoreText = getString(R.string.score) + " " + ++score;
                                scoreView.setText(scoreText);
                                deck.setNewCard(selectedCard1x, selectedCard1y);
                                deck.setNewCard(selectedCard2x, selectedCard2y);
                                deck.setNewCardCheckSetPossible(selectedCard3x, selectedCard3y);
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
                Intent intent = new Intent(GameActivity.this, PausedActivity.class);
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
        deck.refreshAllValues();
        deck.setNewCardCheckSetPossible((int) (Math.random() * deck.height()), (int) (Math.random() * deck.width()));
        refreshDraw();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("deck", deck.saveString());
        savedInstanceState.putInt("time", secondsRemaining);
        savedInstanceState.putInt("score", score);
        savedInstanceState.putBoolean("ongoing", gameOngoing);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState.getBoolean("ongoing")) {
            deck.loadString(savedInstanceState.getString("deck"));
            secondsRemaining = savedInstanceState.getInt("time");
            score = savedInstanceState.getInt("score");
            refreshDraw();
        }
    }

    public void refreshDraw() {
        for (int i1 = 0; i1 < deck.height(); i1++)
            for (int i2 = 0; i2 < deck.width(); i2++) {
                gridDraw[i1][i2].setColorFilter(null);
                if (deck.cards[i1][i2].drawable == 0)
                    gridDraw[i1][i2].setImageResource(R.drawable.card_empty);
                else
                    gridDraw[i1][i2].setImageResource(deck.cards[i1][i2].drawable);
                if ((i1 != selectedCard1x || i2 != selectedCard1y)
                        && (i1 != selectedCard2x || i2 != selectedCard2y)
                        && (i1 != selectedCard3x || i2 != selectedCard3y))
                    if (selectedCard1x + selectedCard1y
                            + selectedCard2x + selectedCard2y
                            + selectedCard3x + selectedCard3y > -6)
                        gridDraw[i1][i2].setColorFilter(Color.argb(150, 0, 0, 0));
            }
        String scoreText = getString(R.string.score) + " " + score;
        scoreView.setText(scoreText);
    }

    @Override
    public void onBackPressed() {
        if (gameOngoing) {
            Intent intent = new Intent(GameActivity.this, PausedActivity.class);
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
                    String timeText = "" + (millisUntilFinished / 60000) + ":"
                            + (((millisUntilFinished / 1000) % 60) / 10)
                            + (((millisUntilFinished / 1000) % 60) % 10);
                    timeCount.setText(timeText);
                }

                public void onFinish() {
                    SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    SharedPreferences.Editor editor = sp.edit();
                    if (score > sp.getInt("score_3", 0)) {
                        if (score > sp.getInt("score_2", 0)) {
                            if (score > sp.getInt("score_1", 0)) {
                                editor.putInt("score_3", sp.getInt("score_2", 0));
                                editor.putInt("score_2", sp.getInt("score_1", 0));
                                editor.putInt("score_1", score);
                            } else {
                                editor.putInt("score_3", sp.getInt("score_2", 0));
                                editor.putInt("score_2", score);
                            }
                        } else {
                            editor.putInt("score_3", score);
                        }
                    }
                    editor.apply();
                    gameOngoing = false;
                    buttonPauseRestart.setBackgroundColor(getResources().getColor(R.color.red));
                    buttonPauseRestart.setText(getString(R.string.restart));
                    buttonPauseRestart.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(GameActivity.this, GameActivity.class);
                            startActivity(intent);
                            activity.finish();
                            overridePendingTransition(R.transition.zoom_1, R.transition.zoom_2);
                        }
                    });
                    buttonStopExit.setText(getString(R.string.exit));
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
                                if (deck.isSet(i1 / 3, i1 % 3, i2 / 3, i2 % 3, i3 / 3, i3 % 3)) {
                                    grid[i1 / 3][i1 % 3] = 1;
                                    grid[i2 / 3][i2 % 3] = 1;
                                    grid[i3 / 3][i3 % 3] = 1;
                                }
                    for (int i1 = 0; i1 < deck.height(); i1++)
                        for (int i2 = 0; i2 < deck.width(); i2++) {
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
                    String scoreText = getString(R.string.score) + " " + score
                            + "     " + getString(R.string.high) + " " + sp.getInt("score_1", 0);
                    scoreView.setText(scoreText);
                    timeCount.setText("0:00");
                }
            }.start();
        }
    }
}
