package com.example.blackjacksim;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class playPage extends AppCompatActivity {

    Button hit, stand, play;
    TextView dealerHandView, playerHandView;

    int dealerHandValue = 0;
    int playerHandValue = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_page);

        play = (Button) findViewById(R.id.button4);
        hit = (Button) findViewById(R.id.button2);
        stand = (Button) findViewById(R.id.button3);
        dealerHandView = (TextView) findViewById(R.id.textView4);
        playerHandView = (TextView) findViewById(R.id.textView5);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                play();
            }
        });
    }



    public void play(){
        final String[] dealerHand = {""};
        final String[] playerHand = {""};
        ArrayList<Card> dealer = new ArrayList<Card>();
        ArrayList<Card> player = new ArrayList<Card>();

        player.add(new Card());
        player.add(new Card());
        dealer.add(new Card());

        setDealerHandValue(0);
        setPlayerHandValue(0);

        for(int i=0;i<dealer.size();i++){
            dealerHand[0] += dealer.get(i).getCardNum() + " ";
            dealerHandValue += dealer.get(i).getCardValue();
        }
        for(int i=0;i<player.size();i++){
            playerHand[0] += player.get(i).getCardNum() + " ";
            playerHandValue += player.get(i).getCardValue();
        }

        dealerHandView.setText(dealerHand[0]);
        playerHandView.setText(playerHand[0]);

        hit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Card c = new Card();
                playerHandValue += c.getCardValue();
                playerHand[0] += c.getCardNum() + " ";

                playerHandView.setText(playerHand[0]);
            }
        });



    }

    public void setDealerHandValue(int n){
        dealerHandValue = n;
    }
    public void setPlayerHandValue(int n){
        playerHandValue = n;
    }
}