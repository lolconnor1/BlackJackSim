package com.example.blackjacksim;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class playPage extends AppCompatActivity {

    Button hit, stand, play;
    TextView dealerHandView, playerHandView, outcome, playerTotal, dealerTotal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_page);

        play = (Button) findViewById(R.id.button4);
        hit = (Button) findViewById(R.id.button2);
        stand = (Button) findViewById(R.id.button3);
        dealerHandView = (TextView) findViewById(R.id.textView4);
        playerHandView = (TextView) findViewById(R.id.textView5);
        outcome = (TextView) findViewById(R.id.textView6);
        playerTotal = (TextView) findViewById(R.id.textView8);
        dealerTotal = (TextView) findViewById(R.id.textView7);

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
        playerTotal.setText("" +handTotal(player));
        dealerTotal.setText("" +handTotal(dealer));
        outcome.setText("GAME IN PROGRESS");


        hit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!done[0]) {
                    Card c = new Card();

                    player.add(c);

                    playerHand[0] += c.getCardNum() + " ";

                    playerHandView.setText(playerHand[0]);

                    playerTotal.setText("" + handTotal(player));

                    if (handTotal(player) > 21) {
                        //lose, bust
                        outcome.setText("LOSE: bust");
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
                            outcome.setText("PUSH");
                        } else {
                            //win
                            outcome.setText("WIN: blackjack");
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
                    dealerTotal.setText("" + handTotal(dealer));

                    if (handTotal(dealer) > 21) {
                        //win, dealer bust
                        outcome.setText("WIN: dealer bust");
                        done[0] = true;
                    } else if (handTotal(player) > handTotal(dealer)) {
                        //win

                        outcome.setText("WIN: better hand");
                    } else if (handTotal(player) == handTotal(dealer)){
                        // push
                        outcome.setText("PUSH");

                    } else {
                        //lose
                        outcome.setText("LOSE: worse hand");

                    }
                    done[0] = true;
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