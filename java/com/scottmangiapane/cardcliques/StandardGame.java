package com.scottmangiapane.cardcliques;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class StandardGame extends AppCompatActivity {
    public static AppCompatActivity gameActivity;
    private boolean backPressable;
    private int score;
    private int secondsRemaining;
    private int selectedCard1x;
    private int selectedCard1y;
    private int selectedCard2x;
    private int selectedCard2y;
    private int selectedCard3x;
    private int selectedCard3y;
    private Card[][] cards;
    private CountDownTimer timer;
    private ImageView buttonPause;
    private ImageView buttonQuit;
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
        backPressable = true;
        score = 0;
        secondsRemaining = 121000;
        selectedCard1x = -1;
        selectedCard1y = -1;
        selectedCard2x = -1;
        selectedCard2y = -1;
        selectedCard3x = -1;
        selectedCard3y = -1;
        cards = new Card[4][3];
        buttonPause = (ImageView) findViewById(R.id.button_pause);
        buttonQuit = (ImageView) findViewById(R.id.button_help);
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
                        (ImageView) findViewById(R.id.imageView_3_2)}
        };
        finalScore = (TextView) findViewById(R.id.final_score);
        timeCount = (TextView) findViewById(R.id.time_count);
        for (int i1 = 0; i1 < cards.length; i1++)
            for (int i2 = 0; i2 < cards[0].length; i2++)
                cards[i1][i2] = new Card();
        for (int i1 = 0; i1 < cards.length; i1++)
            for (int i2 = 0; i2 < cards[0].length; i2++) {
                setNewCard(i1, i2);
                final int ia = i1;
                final int ib = i2;
                gridDraw[i1][i2].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (selectedCard1x == -1) {
                            selectedCard1x = ia;
                            selectedCard1y = ib;
                        } else {
                            if (selectedCard1x == ia && selectedCard1y == ib) {
                                if (selectedCard2x != -1) {
                                    selectedCard1x = selectedCard2x;
                                    selectedCard1y = selectedCard2y;
                                    selectedCard2x = -1;
                                    selectedCard2y = -1;
                                } else {
                                    selectedCard1x = -1;
                                    selectedCard1y = -1;
                                }
                            } else {
                                if (selectedCard2x == -1) {
                                    selectedCard2x = ia;
                                    selectedCard2y = ib;
                                } else {
                                    if (selectedCard1x == ia && selectedCard1y == ib) {
                                        selectedCard1x = -1;
                                        selectedCard1y = -1;
                                    } else {
                                        if (selectedCard2x == ia && selectedCard2y == ib) {
                                            selectedCard2x = -1;
                                            selectedCard2y = -1;
                                        } else {
                                            selectedCard3x = ia;
                                            selectedCard3y = ib;
                                            refreshDraw();
                                            if (isSet(selectedCard1x, selectedCard1y, selectedCard2x, selectedCard2y, selectedCard3x, selectedCard3y)) {
                                                finalScore.setText("Score: " + ++score);
                                                setNewCard(selectedCard1x, selectedCard1y);
                                                setNewCard(selectedCard2x, selectedCard2y);
                                                setNewCardCheckSetPossible(selectedCard3x, selectedCard3y);
                                            }
                                            selectedCard1x = -1;
                                            selectedCard1y = -1;
                                            selectedCard2x = -1;
                                            selectedCard2y = -1;
                                            selectedCard3x = -1;
                                            selectedCard3y = -1;
                                        }
                                    }
                                }
                            }
                        }
                        refreshDraw();
                    }
                });
            }
        buttonPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StandardGame.this, Paused.class);
                startActivity(intent);
                overridePendingTransition(R.transition.slide_left_1, R.transition.slide_left_2);
            }
        });
        buttonQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new QuitDialog();
                newFragment.show(getSupportFragmentManager(), "Quit");
            }
        });
        refreshAllValues();
        setNewCardCheckSetPossible((int) (Math.random() * 3), (int) (Math.random() * 2));
        refreshDraw();
    }

    public boolean isCardDisplayed(int drawable, int d1, int d2) {
        for (int i1 = 0; i1 < cards.length; i1++)
            for (int i2 = 0; i2 < cards[0].length; i2++)
                if ((cards[i1][i2].drawable == drawable) && (i1 != d1 || i2 != d2))
                    return true;
        return false;
    }

    public boolean isSet(int card1x, int card1y, int card2x, int card2y, int card3x, int card3y) {
        if (
                isSetColor(card1x, card1y, card2x, card2y, card3x, card3y)
                        && isSetNumber(card1x, card1y, card2x, card2y, card3x, card3y)
                        && isSetShape(card1x, card1y, card2x, card2y, card3x, card3y)
                        && isSetType(card1x, card1y, card2x, card2y, card3x, card3y)
                )
            return true;
        return false;
    }

    public boolean isSetColor(int card1x, int card1y, int card2x,
                              int card2y, int card3x, int card3y) {
        if (cards[card1x][card1y].color.equals(cards[card2x][card2y].color)
                && cards[card2x][card2y].color.equals(cards[card3x][card3y].color))
            return true;
        if (!cards[card1x][card1y].color.equals(cards[card2x][card2y].color)
                && !cards[card2x][card2y].color.equals(cards[card3x][card3y].color)
                && !cards[card1x][card1y].color.equals(cards[card3x][card3y].color))
            return true;
        return false;
    }

    public boolean isSetNumber(int card1x, int card1y, int card2x,
                               int card2y, int card3x, int card3y) {
        if (cards[card1x][card1y].number == (cards[card2x][card2y].number)
                && cards[card2x][card2y].number == (cards[card3x][card3y].number))
            return true;
        if (cards[card1x][card1y].number != (cards[card2x][card2y].number)
                && cards[card2x][card2y].number != (cards[card3x][card3y].number)
                && cards[card1x][card1y].number != (cards[card3x][card3y].number))
            return true;
        return false;
    }

    public boolean isSetShape(int card1x, int card1y, int card2x, int card2y, int card3x, int card3y) {
        if (cards[card1x][card1y].shape.equals(cards[card2x][card2y].shape)
                && cards[card2x][card2y].shape.equals(cards[card3x][card3y].shape))
            return true;
        if (!cards[card1x][card1y].shape.equals(cards[card2x][card2y].shape)
                && !cards[card2x][card2y].shape.equals(cards[card3x][card3y].shape)
                && !cards[card1x][card1y].shape.equals(cards[card3x][card3y].shape))
            return true;
        return false;
    }

    public boolean isSetType(int card1x, int card1y, int card2x, int card2y, int card3x, int card3y) {
        if (cards[card1x][card1y].type.equals(cards[card2x][card2y].type)
                && cards[card2x][card2y].type.equals(cards[card3x][card3y].type))
            return true;
        if (!cards[card1x][card1y].type.equals(cards[card2x][card2y].type)
                && !cards[card2x][card2y].type.equals(cards[card3x][card3y].type)
                && !cards[card1x][card1y].type.equals(cards[card3x][card3y].type))
            return true;
        return false;
    }

    public int numberOfSetsDisplayed() {
        int count = 0;
        int[] row = {0, 0, 0, 1, 1, 1, 2, 2, 2, 3, 3, 3};
        int[] column = {0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2};
        for (int i1 = 0; i1 < 12; i1++)
            for (int i2 = Math.min(i1 + 1, 12); i2 < 12; i2++)
                for (int i3 = Math.min(i2 + 1, 12); i3 < 12; i3++)
                    if (isSet(row[i1], column[i1], row[i2], column[i2], row[i3], column[i3]))
                        count++;
        return count;
    }

    public void refreshAllValues() {
        for (int i1 = 0; i1 < cards.length; i1++)
            for (int i2 = 0; i2 < cards[0].length; i2++) {
                refreshValue(i1, i2);
            }
    }

    public void refreshDraw() {
        for (int i1 = 0; i1 < cards.length; i1++)
            for (int i2 = 0; i2 < cards[0].length; i2++) {
                gridDraw[i1][i2].setColorFilter(null);
                if (cards[i1][i2].drawable == 0)
                    gridDraw[i1][i2].setImageResource(R.drawable.card_empty);
                else
                    gridDraw[i1][i2].setImageResource(cards[i1][i2].drawable);
                if ((i1 != selectedCard1x || i2 != selectedCard1y)
                        && (i1 != selectedCard2x || i2 != selectedCard2y)
                        && (i1 != selectedCard3x || i2 != selectedCard3y))
                    if (selectedCard1x + selectedCard1y + selectedCard2x + selectedCard2y + selectedCard3x + selectedCard3y > -6)
                        gridDraw[i1][i2].setColorFilter(Color.argb(150, 0, 0, 0));
            }
    }

    public void refreshValue(int i1, int i2) {
        switch (cards[i1][i2].drawable) {
            case 0:
                cards[i1][i2].shape = "";
                cards[i1][i2].color = "";
                cards[i1][i2].type = "";
                cards[i1][i2].number = 0;
                break;
            case R.drawable.deck_diamond_blue_border_1:
                cards[i1][i2].shape = "diamond";
                cards[i1][i2].color = "blue";
                cards[i1][i2].type = "border";
                cards[i1][i2].number = 1;
                break;
            case R.drawable.deck_diamond_blue_border_2:
                cards[i1][i2].shape = "diamond";
                cards[i1][i2].color = "blue";
                cards[i1][i2].type = "border";
                cards[i1][i2].number = 2;
                break;
            case R.drawable.deck_diamond_blue_border_3:
                cards[i1][i2].shape = "diamond";
                cards[i1][i2].color = "blue";
                cards[i1][i2].type = "border";
                cards[i1][i2].number = 3;
                break;
            case R.drawable.deck_diamond_blue_solid_1:
                cards[i1][i2].shape = "diamond";
                cards[i1][i2].color = "blue";
                cards[i1][i2].type = "solid";
                cards[i1][i2].number = 1;
                break;
            case R.drawable.deck_diamond_blue_solid_2:
                cards[i1][i2].shape = "diamond";
                cards[i1][i2].color = "blue";
                cards[i1][i2].type = "solid";
                cards[i1][i2].number = 2;
                break;
            case R.drawable.deck_diamond_blue_solid_3:
                cards[i1][i2].shape = "diamond";
                cards[i1][i2].color = "blue";
                cards[i1][i2].type = "solid";
                cards[i1][i2].number = 3;
                break;
            case R.drawable.deck_diamond_blue_striped_1:
                cards[i1][i2].shape = "diamond";
                cards[i1][i2].color = "blue";
                cards[i1][i2].type = "striped";
                cards[i1][i2].number = 1;
                break;
            case R.drawable.deck_diamond_blue_striped_2:
                cards[i1][i2].shape = "diamond";
                cards[i1][i2].color = "blue";
                cards[i1][i2].type = "striped";
                cards[i1][i2].number = 2;
                break;
            case R.drawable.deck_diamond_blue_striped_3:
                cards[i1][i2].shape = "diamond";
                cards[i1][i2].color = "blue";
                cards[i1][i2].type = "striped";
                cards[i1][i2].number = 3;
                break;
            case R.drawable.deck_diamond_green_border_1:
                cards[i1][i2].shape = "diamond";
                cards[i1][i2].color = "green";
                cards[i1][i2].type = "border";
                cards[i1][i2].number = 1;
                break;
            case R.drawable.deck_diamond_green_border_2:
                cards[i1][i2].shape = "diamond";
                cards[i1][i2].color = "green";
                cards[i1][i2].type = "border";
                cards[i1][i2].number = 2;
                break;
            case R.drawable.deck_diamond_green_border_3:
                cards[i1][i2].shape = "diamond";
                cards[i1][i2].color = "green";
                cards[i1][i2].type = "border";
                cards[i1][i2].number = 3;
                break;
            case R.drawable.deck_diamond_green_solid_1:
                cards[i1][i2].shape = "diamond";
                cards[i1][i2].color = "green";
                cards[i1][i2].type = "solid";
                cards[i1][i2].number = 1;
                break;
            case R.drawable.deck_diamond_green_solid_2:
                cards[i1][i2].shape = "diamond";
                cards[i1][i2].color = "green";
                cards[i1][i2].type = "solid";
                cards[i1][i2].number = 2;
                break;
            case R.drawable.deck_diamond_green_solid_3:
                cards[i1][i2].shape = "diamond";
                cards[i1][i2].color = "green";
                cards[i1][i2].type = "solid";
                cards[i1][i2].number = 3;
                break;
            case R.drawable.deck_diamond_green_striped_1:
                cards[i1][i2].shape = "diamond";
                cards[i1][i2].color = "green";
                cards[i1][i2].type = "striped";
                cards[i1][i2].number = 1;
                break;
            case R.drawable.deck_diamond_green_striped_2:
                cards[i1][i2].shape = "diamond";
                cards[i1][i2].color = "green";
                cards[i1][i2].type = "striped";
                cards[i1][i2].number = 2;
                break;
            case R.drawable.deck_diamond_green_striped_3:
                cards[i1][i2].shape = "diamond";
                cards[i1][i2].color = "green";
                cards[i1][i2].type = "striped";
                cards[i1][i2].number = 3;
                break;
            case R.drawable.deck_diamond_red_border_1:
                cards[i1][i2].shape = "diamond";
                cards[i1][i2].color = "red";
                cards[i1][i2].type = "border";
                cards[i1][i2].number = 1;
                break;
            case R.drawable.deck_diamond_red_border_2:
                cards[i1][i2].shape = "diamond";
                cards[i1][i2].color = "red";
                cards[i1][i2].type = "border";
                cards[i1][i2].number = 2;
                break;
            case R.drawable.deck_diamond_red_border_3:
                cards[i1][i2].shape = "diamond";
                cards[i1][i2].color = "red";
                cards[i1][i2].type = "border";
                cards[i1][i2].number = 3;
                break;
            case R.drawable.deck_diamond_red_solid_1:
                cards[i1][i2].shape = "diamond";
                cards[i1][i2].color = "red";
                cards[i1][i2].type = "solid";
                cards[i1][i2].number = 1;
                break;
            case R.drawable.deck_diamond_red_solid_2:
                cards[i1][i2].shape = "diamond";
                cards[i1][i2].color = "red";
                cards[i1][i2].type = "solid";
                cards[i1][i2].number = 2;
                break;
            case R.drawable.deck_diamond_red_solid_3:
                cards[i1][i2].shape = "diamond";
                cards[i1][i2].color = "red";
                cards[i1][i2].type = "solid";
                cards[i1][i2].number = 3;
                break;
            case R.drawable.deck_diamond_red_striped_1:
                cards[i1][i2].shape = "diamond";
                cards[i1][i2].color = "red";
                cards[i1][i2].type = "striped";
                cards[i1][i2].number = 1;
                break;
            case R.drawable.deck_diamond_red_striped_2:
                cards[i1][i2].shape = "diamond";
                cards[i1][i2].color = "red";
                cards[i1][i2].type = "striped";
                cards[i1][i2].number = 2;
                break;
            case R.drawable.deck_diamond_red_striped_3:
                cards[i1][i2].shape = "diamond";
                cards[i1][i2].color = "red";
                cards[i1][i2].type = "striped";
                cards[i1][i2].number = 3;
                break;
            case R.drawable.deck_oval_blue_border_1:
                cards[i1][i2].shape = "oval";
                cards[i1][i2].color = "blue";
                cards[i1][i2].type = "border";
                cards[i1][i2].number = 1;
                break;
            case R.drawable.deck_oval_blue_border_2:
                cards[i1][i2].shape = "oval";
                cards[i1][i2].color = "blue";
                cards[i1][i2].type = "border";
                cards[i1][i2].number = 2;
                break;
            case R.drawable.deck_oval_blue_border_3:
                cards[i1][i2].shape = "oval";
                cards[i1][i2].color = "blue";
                cards[i1][i2].type = "border";
                cards[i1][i2].number = 3;
                break;
            case R.drawable.deck_oval_blue_solid_1:
                cards[i1][i2].shape = "oval";
                cards[i1][i2].color = "blue";
                cards[i1][i2].type = "solid";
                cards[i1][i2].number = 1;
                break;
            case R.drawable.deck_oval_blue_solid_2:
                cards[i1][i2].shape = "oval";
                cards[i1][i2].color = "blue";
                cards[i1][i2].type = "solid";
                cards[i1][i2].number = 2;
                break;
            case R.drawable.deck_oval_blue_solid_3:
                cards[i1][i2].shape = "oval";
                cards[i1][i2].color = "blue";
                cards[i1][i2].type = "solid";
                cards[i1][i2].number = 3;
                break;
            case R.drawable.deck_oval_blue_striped_1:
                cards[i1][i2].shape = "oval";
                cards[i1][i2].color = "blue";
                cards[i1][i2].type = "striped";
                cards[i1][i2].number = 1;
                break;
            case R.drawable.deck_oval_blue_striped_2:
                cards[i1][i2].shape = "oval";
                cards[i1][i2].color = "blue";
                cards[i1][i2].type = "striped";
                cards[i1][i2].number = 2;
                break;
            case R.drawable.deck_oval_blue_striped_3:
                cards[i1][i2].shape = "oval";
                cards[i1][i2].color = "blue";
                cards[i1][i2].type = "striped";
                cards[i1][i2].number = 3;
                break;
            case R.drawable.deck_oval_green_border_1:
                cards[i1][i2].shape = "oval";
                cards[i1][i2].color = "green";
                cards[i1][i2].type = "border";
                cards[i1][i2].number = 1;
                break;
            case R.drawable.deck_oval_green_border_2:
                cards[i1][i2].shape = "oval";
                cards[i1][i2].color = "green";
                cards[i1][i2].type = "border";
                cards[i1][i2].number = 2;
                break;
            case R.drawable.deck_oval_green_border_3:
                cards[i1][i2].shape = "oval";
                cards[i1][i2].color = "green";
                cards[i1][i2].type = "border";
                cards[i1][i2].number = 3;
                break;
            case R.drawable.deck_oval_green_solid_1:
                cards[i1][i2].shape = "oval";
                cards[i1][i2].color = "green";
                cards[i1][i2].type = "solid";
                cards[i1][i2].number = 1;
                break;
            case R.drawable.deck_oval_green_solid_2:
                cards[i1][i2].shape = "oval";
                cards[i1][i2].color = "green";
                cards[i1][i2].type = "solid";
                cards[i1][i2].number = 2;
                break;
            case R.drawable.deck_oval_green_solid_3:
                cards[i1][i2].shape = "oval";
                cards[i1][i2].color = "green";
                cards[i1][i2].type = "solid";
                cards[i1][i2].number = 3;
                break;
            case R.drawable.deck_oval_green_striped_1:
                cards[i1][i2].shape = "oval";
                cards[i1][i2].color = "green";
                cards[i1][i2].type = "striped";
                cards[i1][i2].number = 1;
                break;
            case R.drawable.deck_oval_green_striped_2:
                cards[i1][i2].shape = "oval";
                cards[i1][i2].color = "green";
                cards[i1][i2].type = "striped";
                cards[i1][i2].number = 2;
                break;
            case R.drawable.deck_oval_green_striped_3:
                cards[i1][i2].shape = "oval";
                cards[i1][i2].color = "green";
                cards[i1][i2].type = "striped";
                cards[i1][i2].number = 3;
                break;
            case R.drawable.deck_oval_red_border_1:
                cards[i1][i2].shape = "oval";
                cards[i1][i2].color = "red";
                cards[i1][i2].type = "border";
                cards[i1][i2].number = 1;
                break;
            case R.drawable.deck_oval_red_border_2:
                cards[i1][i2].shape = "oval";
                cards[i1][i2].color = "red";
                cards[i1][i2].type = "border";
                cards[i1][i2].number = 2;
                break;
            case R.drawable.deck_oval_red_border_3:
                cards[i1][i2].shape = "oval";
                cards[i1][i2].color = "red";
                cards[i1][i2].type = "border";
                cards[i1][i2].number = 3;
                break;
            case R.drawable.deck_oval_red_solid_1:
                cards[i1][i2].shape = "oval";
                cards[i1][i2].color = "red";
                cards[i1][i2].type = "solid";
                cards[i1][i2].number = 1;
                break;
            case R.drawable.deck_oval_red_solid_2:
                cards[i1][i2].shape = "oval";
                cards[i1][i2].color = "red";
                cards[i1][i2].type = "solid";
                cards[i1][i2].number = 2;
                break;
            case R.drawable.deck_oval_red_solid_3:
                cards[i1][i2].shape = "oval";
                cards[i1][i2].color = "red";
                cards[i1][i2].type = "solid";
                cards[i1][i2].number = 3;
                break;
            case R.drawable.deck_oval_red_striped_1:
                cards[i1][i2].shape = "oval";
                cards[i1][i2].color = "red";
                cards[i1][i2].type = "striped";
                cards[i1][i2].number = 1;
                break;
            case R.drawable.deck_oval_red_striped_2:
                cards[i1][i2].shape = "oval";
                cards[i1][i2].color = "red";
                cards[i1][i2].type = "striped";
                cards[i1][i2].number = 2;
                break;
            case R.drawable.deck_oval_red_striped_3:
                cards[i1][i2].shape = "oval";
                cards[i1][i2].color = "red";
                cards[i1][i2].type = "striped";
                cards[i1][i2].number = 3;
                break;
            case R.drawable.deck_squiggle_blue_border_1:
                cards[i1][i2].shape = "squiggle";
                cards[i1][i2].color = "blue";
                cards[i1][i2].type = "border";
                cards[i1][i2].number = 1;
                break;
            case R.drawable.deck_squiggle_blue_border_2:
                cards[i1][i2].shape = "squiggle";
                cards[i1][i2].color = "blue";
                cards[i1][i2].type = "border";
                cards[i1][i2].number = 2;
                break;
            case R.drawable.deck_squiggle_blue_border_3:
                cards[i1][i2].shape = "squiggle";
                cards[i1][i2].color = "blue";
                cards[i1][i2].type = "border";
                cards[i1][i2].number = 3;
                break;
            case R.drawable.deck_squiggle_blue_solid_1:
                cards[i1][i2].shape = "squiggle";
                cards[i1][i2].color = "blue";
                cards[i1][i2].type = "solid";
                cards[i1][i2].number = 1;
                break;
            case R.drawable.deck_squiggle_blue_solid_2:
                cards[i1][i2].shape = "squiggle";
                cards[i1][i2].color = "blue";
                cards[i1][i2].type = "solid";
                cards[i1][i2].number = 2;
                break;
            case R.drawable.deck_squiggle_blue_solid_3:
                cards[i1][i2].shape = "squiggle";
                cards[i1][i2].color = "blue";
                cards[i1][i2].type = "solid";
                cards[i1][i2].number = 3;
                break;
            case R.drawable.deck_squiggle_blue_striped_1:
                cards[i1][i2].shape = "squiggle";
                cards[i1][i2].color = "blue";
                cards[i1][i2].type = "striped";
                cards[i1][i2].number = 1;
                break;
            case R.drawable.deck_squiggle_blue_striped_2:
                cards[i1][i2].shape = "squiggle";
                cards[i1][i2].color = "blue";
                cards[i1][i2].type = "striped";
                cards[i1][i2].number = 2;
                break;
            case R.drawable.deck_squiggle_blue_striped_3:
                cards[i1][i2].shape = "squiggle";
                cards[i1][i2].color = "blue";
                cards[i1][i2].type = "striped";
                cards[i1][i2].number = 3;
                break;
            case R.drawable.deck_squiggle_green_border_1:
                cards[i1][i2].shape = "squiggle";
                cards[i1][i2].color = "green";
                cards[i1][i2].type = "border";
                cards[i1][i2].number = 1;
                break;
            case R.drawable.deck_squiggle_green_border_2:
                cards[i1][i2].shape = "squiggle";
                cards[i1][i2].color = "green";
                cards[i1][i2].type = "border";
                cards[i1][i2].number = 2;
                break;
            case R.drawable.deck_squiggle_green_border_3:
                cards[i1][i2].shape = "squiggle";
                cards[i1][i2].color = "green";
                cards[i1][i2].type = "border";
                cards[i1][i2].number = 3;
                break;
            case R.drawable.deck_squiggle_green_solid_1:
                cards[i1][i2].shape = "squiggle";
                cards[i1][i2].color = "green";
                cards[i1][i2].type = "solid";
                cards[i1][i2].number = 1;
                break;
            case R.drawable.deck_squiggle_green_solid_2:
                cards[i1][i2].shape = "squiggle";
                cards[i1][i2].color = "green";
                cards[i1][i2].type = "solid";
                cards[i1][i2].number = 2;
                break;
            case R.drawable.deck_squiggle_green_solid_3:
                cards[i1][i2].shape = "squiggle";
                cards[i1][i2].color = "green";
                cards[i1][i2].type = "solid";
                cards[i1][i2].number = 3;
                break;
            case R.drawable.deck_squiggle_green_striped_1:
                cards[i1][i2].shape = "squiggle";
                cards[i1][i2].color = "green";
                cards[i1][i2].type = "striped";
                cards[i1][i2].number = 1;
                break;
            case R.drawable.deck_squiggle_green_striped_2:
                cards[i1][i2].shape = "squiggle";
                cards[i1][i2].color = "green";
                cards[i1][i2].type = "striped";
                cards[i1][i2].number = 2;
                break;
            case R.drawable.deck_squiggle_green_striped_3:
                cards[i1][i2].shape = "squiggle";
                cards[i1][i2].color = "green";
                cards[i1][i2].type = "striped";
                cards[i1][i2].number = 3;
                break;
            case R.drawable.deck_squiggle_red_border_1:
                cards[i1][i2].shape = "squiggle";
                cards[i1][i2].color = "red";
                cards[i1][i2].type = "border";
                cards[i1][i2].number = 1;
                break;
            case R.drawable.deck_squiggle_red_border_2:
                cards[i1][i2].shape = "squiggle";
                cards[i1][i2].color = "red";
                cards[i1][i2].type = "border";
                cards[i1][i2].number = 2;
                break;
            case R.drawable.deck_squiggle_red_border_3:
                cards[i1][i2].shape = "squiggle";
                cards[i1][i2].color = "red";
                cards[i1][i2].type = "border";
                cards[i1][i2].number = 3;
                break;
            case R.drawable.deck_squiggle_red_solid_1:
                cards[i1][i2].shape = "squiggle";
                cards[i1][i2].color = "red";
                cards[i1][i2].type = "solid";
                cards[i1][i2].number = 1;
                break;
            case R.drawable.deck_squiggle_red_solid_2:
                cards[i1][i2].shape = "squiggle";
                cards[i1][i2].color = "red";
                cards[i1][i2].type = "solid";
                cards[i1][i2].number = 2;
                break;
            case R.drawable.deck_squiggle_red_solid_3:
                cards[i1][i2].shape = "squiggle";
                cards[i1][i2].color = "red";
                cards[i1][i2].type = "solid";
                cards[i1][i2].number = 3;
                break;
            case R.drawable.deck_squiggle_red_striped_1:
                cards[i1][i2].shape = "squiggle";
                cards[i1][i2].color = "red";
                cards[i1][i2].type = "striped";
                cards[i1][i2].number = 1;
                break;
            case R.drawable.deck_squiggle_red_striped_2:
                cards[i1][i2].shape = "squiggle";
                cards[i1][i2].color = "red";
                cards[i1][i2].type = "striped";
                cards[i1][i2].number = 2;
                break;
            case R.drawable.deck_squiggle_red_striped_3:
                cards[i1][i2].shape = "squiggle";
                cards[i1][i2].color = "red";
                cards[i1][i2].type = "striped";
                cards[i1][i2].number = 3;
                break;
        }
    }

    public void setNewCard(int i1, int i2) {
        do {
            cards[i1][i2].drawable = (int) (Math.random() * (R.drawable.deck_squiggle_red_striped_1
                    - R.drawable.deck_diamond_blue_border_1))
                    + R.drawable.deck_diamond_blue_border_1;
            refreshValue(i1, i2);
        } while (isCardDisplayed(cards[i1][i2].drawable, i1, i2));
    }

    public void setNewCardCheckSetPossible(int i1, int i2) {
        do {
            cards[i1][i2].drawable = (int) (Math.random() * (R.drawable.deck_squiggle_red_striped_1
                    - R.drawable.deck_diamond_blue_border_1))
                    + R.drawable.deck_diamond_blue_border_1;
            refreshValue(i1, i2);
        } while (isCardDisplayed(cards[i1][i2].drawable, i1, i2) || numberOfSetsDisplayed() == 0);
    }

    @Override
    public void onBackPressed() {
        if (backPressable) {
            Intent intent = new Intent(StandardGame.this, Paused.class);
            startActivity(intent);
            overridePendingTransition(R.transition.slide_left_1, R.transition.slide_left_2);
        }
        return;
    }

    @Override
    public void onPause() {
        super.onPause();
        if (backPressable)
            timer.cancel();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (backPressable) {
            timer = new CountDownTimer(secondsRemaining, 1000) {
                public void onTick(long millisUntilFinished) {
                    secondsRemaining = (int) millisUntilFinished;
                    timeCount.setText("0" + (millisUntilFinished / 60000) + ":"
                            + (((millisUntilFinished / 1000) % 60) / 10)
                            + (((millisUntilFinished / 1000) % 60) % 10));
                }

                public void onFinish() {
                    SharedPreferences settings = getApplicationContext().getSharedPreferences("SCORE_DATA", Context.MODE_PRIVATE);
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
                    backPressable = false;
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
                                if (isSet(i1 / 3, i1 % 3, i2 / 3, i2 % 3, i3 / 3, i3 % 3)) {
                                    grid[i1 / 3][i1 % 3] = 1;
                                    grid[i2 / 3][i2 % 3] = 1;
                                    grid[i3 / 3][i3 % 3] = 1;
                                }
                    buttonPause.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                        }
                    });
                    for (int i1 = 0; i1 < cards.length; i1++)
                        for (int i2 = 0; i2 < cards[0].length; i2++) {
                            gridDraw[i1][i2].setColorFilter(null);
                            if (grid[i1][i2] == 0) {
                                gridDraw[i1][i2].setColorFilter(Color.argb(150, 0, 0, 0));
                                Log.w("########", "COLORFILTER");
                            }
                            gridDraw[i1][i2].setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                }
                            });
                        }
                    finalScore.setText("Score: " + score + "     High: " + settings.getInt("HIGH_SCORE_1", 0));
                    timeCount.setText("00:00");
                }
            }.start();
        }
    }
}
