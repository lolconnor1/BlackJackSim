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

        final boolean[] done = {false};

        ArrayList<Card> dealer = new ArrayList<Card>();
        ArrayList<Card> player = new ArrayList<Card>();

        player.add(new Card());
        player.add(new Card());
        dealer.add(new Card());

        for(int i=0;i<dealer.size();i++){
            dealerHand[0] += dealer.get(i).getCardNum() + " ";
        }
        for(int i=0;i<player.size();i++){
            playerHand[0] += player.get(i).getCardNum() + " ";
        }

        dealerHandView.setText(dealerHand[0]);
        playerHandView.setText(playerHand[0]);


        hit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!done[0]) {
                    Card c = new Card();

                    playerHand[0] += c.getCardNum() + " ";

                    playerHandView.setText(playerHand[0]);

                    if (handTotal(player) > 21) {
                        //lose, bust
                        done[0] = true;

                    }
                    else if (handTotal(player) == 21) {

                        done[0] = true;
                        standing(dealer);
                        for (int i = 1; i < dealer.size(); i++) {
                            dealerHand[0] += dealer.get(i).getCardNum() + " ";
                        }
                        dealerHandView.setText(dealerHand[0]);

                        if (handTotal(player) == handTotal(dealer)) {
                            //push

                        } else {
                            //win

                        }
                    }
                }

            }
        });

        stand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!done[0]) {
                    standing(dealer);
                    for (int i = 1; i < dealer.size(); i++) {
                        dealerHand[0] += dealer.get(i).getCardNum() + " ";
                    }
                    dealerHandView.setText(dealerHand[0]);

                    if (handTotal(dealer) > 21) {
                        //win, dealer bust
                        done[0] = true;
                    } else if (handTotal(player) > handTotal(dealer)) {
                        //win
                        done[0] = true;
                    } else {
                        //lose
                        done[0] = true;
                    }
                }

            }
        });


    }

    public int handTotal(ArrayList<Card> hand){
        int num = 0;
        int aceCount = 0;
        for(int i = 0; i<hand.size();i++){
            num += hand.get(i).getCardValue();
            if(hand.get(i).getCardValue() == 11){
                aceCount++;
            }
        }
        for(int j = 0; j < aceCount; j++){
            if(num > 21){
                num -= 10;
            }
        }
        return num;
    }


    public void standing(ArrayList<Card> dealerHand){
        while(handTotal(dealerHand) < 17){
            dealerHand.add(new Card());
        }

    }
}