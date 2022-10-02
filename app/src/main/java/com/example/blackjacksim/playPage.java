package com.example.blackjacksim;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class playPage extends AppCompatActivity {

    Button hit, stand, play, doubleDown, split;
    TextView dealerHandView, playerHandView, outcome, playerTotal, dealerTotal, playerHandView2, playerTotal2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_page);

        play = (Button) findViewById(R.id.button4);
        hit = (Button) findViewById(R.id.button2);
        stand = (Button) findViewById(R.id.button3);
        doubleDown= (Button) findViewById(R.id.button5);
        split = (Button) findViewById(R.id.button6);
        dealerHandView = (TextView) findViewById(R.id.textView4);
        playerHandView = (TextView) findViewById(R.id.textView5);
        outcome = (TextView) findViewById(R.id.textView6);
        playerTotal = (TextView) findViewById(R.id.textView8);
        dealerTotal = (TextView) findViewById(R.id.textView7);
        playerHandView2 = (TextView) findViewById(R.id.textView9);
        playerTotal2 = (TextView) findViewById(R.id.textView10);

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
        final String[] playerHand2 = {""};


        final boolean[] done = {false};
        final boolean[] isSplit = {false};
        final boolean[] isSplit2 = {false};


        ArrayList<Card> dealer = new ArrayList<Card>();
        ArrayList<Card> player = new ArrayList<Card>();
        ArrayList<Card> player2 = new ArrayList<Card>();

        player.add(new Card());
        player.add(new Card());
        dealer.add(new Card());
        dealer.add(new Card());

        doubleDown.setVisibility(View.INVISIBLE);
        split.setVisibility(View.INVISIBLE);


        dealerHand[0] += "? " +dealer.get(1).getCardNum() + " ";

        for(int i=0;i<player.size();i++){
            playerHand[0] += player.get(i).getCardNum() + " ";
        }

        dealerHandView.setText(dealerHand[0]);
        playerHandView.setText(playerHand[0]);
        playerTotal.setText("" +handTotal(player));
        playerHandView2.setText(playerHand2[0]);
        playerTotal2.setText("");
        dealerTotal.setText("" +(handTotal(dealer) - dealer.get(0).getCardValue()));
        outcome.setText("GAME IN PROGRESS");


        if(dealer.get(1).getCardValue() == 10 || dealer.get(1).getCardValue() == 11){


            if(dealer.get(1).getCardValue() == 11){
                //offer insurance
            }
            if(handTotal(dealer) == 21){
                dealerHand[0] = "";
                for (int i = 0; i < dealer.size(); i++) {
                    dealerHand[0] += dealer.get(i).getCardNum() + " ";
                }
                dealerHandView.setText(dealerHand[0]);
                dealerTotal.setText("" + handTotal(dealer));

                if(handTotal(player) == 21) {
                    outcome.setText("PUSH");
                }
                else{
                    outcome.setText("LOSE: dealer natural");

                }
                done[0] = true;
            }
        }
        if(handTotal(player) == 21 && handTotal(dealer) < 21){

            dealerHand[0] = "";
            for (int i = 0; i < dealer.size(); i++) {
                dealerHand[0] += dealer.get(i).getCardNum() + " ";
            }
            dealerHandView.setText(dealerHand[0]);

            dealerTotal.setText("" +handTotal(dealer));

            outcome.setText("WIN: natural");
            done[0] = true;
        }

        if (handTotal(player) == 9 || handTotal(player) == 10 || handTotal(player) == 11) {
            doubleDown.setVisibility(View.VISIBLE);
            doubleDown.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(!done[0]) {

                        Card c = new Card();

                        player.add(c);

                        playerHand[0] += c.getCardNum() + " ";

                        playerHandView.setText(playerHand[0]);

                        playerTotal.setText("" + handTotal(player));

                        standing(dealer);

                        dealerHand[0] = "";
                        for (int i = 0; i < dealer.size(); i++) {
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
                        } else if (handTotal(player) == handTotal(dealer)) {
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

        if(player.get(0).getCardNum() == player.get(1).getCardNum()){
            split.setVisibility(View.VISIBLE);
            split.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(!done[0] && !isSplit[0]){
                        isSplit[0] = true;
                        player2.add(player.get(0));
                        player.remove(1);

                        playerHand[0] = player.get(0).getCardValue() + " ";
                        playerHandView.setText(playerHand[0]);
                        playerTotal.setText("" + handTotal(player));

                        playerHand2[0] = player2.get(0).getCardValue() + " ";
                        playerHandView2.setText(playerHand2[0]);

                    }
                }
            });
        }




        hit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!done[0]) {
                    if(!isSplit[0] && !isSplit2[0]){
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

                            dealerHand[0] = "";
                            for (int i = 0; i < dealer.size(); i++) {
                                dealerHand[0] += dealer.get(i).getCardNum() + " ";
                            }
                            dealerHandView.setText(dealerHand[0]);
                            dealerTotal.setText("" + handTotal(dealer));

                            if (handTotal(player) == handTotal(dealer)) {
                                //push
                                outcome.setText("PUSH");
                            } else {
                                //win
                                outcome.setText("WIN: blackjack");
                            }
                        }
                    }
                    else if(isSplit[0] && !isSplit2[0]){
                        Card c = new Card();

                        player.add(c);

                        playerHand[0] += c.getCardNum() + " ";

                        playerHandView.setText(playerHand[0]);

                        playerTotal.setText("" + handTotal(player));
                        if (handTotal(player) > 21) {
                            //lose, bust
                            outcome.setText("LOSE: bust");
                            isSplit2[0] = true;

                        }
                        else if (handTotal(player) == 21) {
                            isSplit2[0] = true;

                        }
                    }
                    if(isSplit2[0]){
                        Card c = new Card();

                        player2.add(c);

                        playerHand2[0] += c.getCardNum() + " ";

                        playerHandView2.setText(playerHand2[0]);

                        playerTotal2.setText("" + handTotal(player2));

                        if (handTotal(player2) == 21) {
                            done[0] = true;
                            isSplit[0] = false;
                            standing(dealer);

                            dealerHand[0] = "";
                            for (int i = 0; i < dealer.size(); i++) {
                                dealerHand[0] += dealer.get(i).getCardNum() + " ";
                            }
                            dealerHandView.setText(dealerHand[0]);
                            dealerTotal.setText("" + handTotal(dealer));

                            if (handTotal(player2) == handTotal(dealer)) {
                                //push
                                outcome.setText("PUSH");
                            } else {
                                //win
                                outcome.setText("WIN: blackjack");
                            }

                        }
                        else if (handTotal(player2) > 21) {
                            done[0] = true;
                            isSplit[0] = false;
                            standing(dealer);

                            outcome.setText("LOSE: bust");

                        }

                    }
                }

            }
        });

        stand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!done[0]) {

                    if(isSplit[0]){
                        isSplit2[0] = true;
                        isSplit[0] = false;
                    }
                    else{
                        standing(dealer);
                        dealerHand[0] = "";
                        for (int i = 0; i < dealer.size(); i++) {
                            dealerHand[0] += dealer.get(i).getCardNum() + " ";
                        }
                        dealerHandView.setText(dealerHand[0]);
                        dealerTotal.setText("" + handTotal(dealer));

                        if (handTotal(dealer) > 21) {
                            //win, dealer bust
                            outcome.setText("WIN: dealer bust");
                            done[0] = true;
                        } else if (handTotal(player) > handTotal(dealer) || handTotal(player2) > handTotal(dealer)) {
                            //win

                            outcome.setText("WIN: better hand");
                        } else if (handTotal(player) == handTotal(dealer)||handTotal(player2) == handTotal(dealer)) {
                            // push
                            outcome.setText("PUSH");

                        } else {
                            //lose
                            outcome.setText("LOSE: worse hand");

                        }


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