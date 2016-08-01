package com.scottmangiapane.cardcliques;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class HelpActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        findViewById(R.id.button_refresh).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                refresh();
            }
        });
        findViewById(R.id.button_return_to_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.transition.slide_left_1, R.transition.slide_left_2);
            }
        });
        refresh();
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.transition.slide_left_1, R.transition.slide_left_2);
        return;
    }

    private void refresh() {
        Deck deck = new Deck(1, 3);
        ImageView[] gridDraw = new ImageView[]{
                (ImageView) findViewById(R.id.imageView_0),
                (ImageView) findViewById(R.id.imageView_1),
                (ImageView) findViewById(R.id.imageView_2)};
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
