package com.scottmangiapane.cardclicks;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class HelpActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        findViewById(R.id.button_refresh).setOnClickListener(view -> refresh());
        findViewById(R.id.help_card_1).setOnClickListener(view -> refresh());
        findViewById(R.id.help_card_2).setOnClickListener(view -> refresh());
        findViewById(R.id.help_card_3).setOnClickListener(view -> refresh());
        findViewById(R.id.button_return_to_menu).setOnClickListener(v -> {
            finish();
            overridePendingTransition(R.transition.slide_left_1, R.transition.slide_left_2);
        });
        refresh();
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.transition.slide_left_1, R.transition.slide_left_2);
    }

    private void refresh() {
        Deck deck = new Deck(1, 3);
        ImageView[] gridDraw = new ImageView[]{
                findViewById(R.id.help_card_1),
                findViewById(R.id.help_card_2),
                findViewById(R.id.help_card_3)};
        for (int i = 0; i < deck.width(); i++)
            deck.setNewCard(0, i);
        deck.refreshAllValues();
        deck.setNewCardCheckSetPossible(0, (int) (Math.random() * deck.width()));
        for (int i = 0; i < deck.width(); i++)
            if (deck.cards[0][i].drawable == 0)
                gridDraw[i].setImageResource(R.drawable.card_empty);
            else
                gridDraw[i].setImageResource(deck.cards[0][i].drawable);
    }
}
