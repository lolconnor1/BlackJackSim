package com.example.blackjacksim;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class playPage extends AppCompatActivity {

    Button hit, stand, play;
    TextView dealerHand, playerHand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_page);

        play = (Button) findViewById(R.id.button4);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                play();
            }
        });
    }

    public String getRandomCard(){
        int cardid = getRandomNumber(1, 13);
        String card = "";
        if(cardid == 2 ||cardid == 3 ||cardid == 4 ||cardid == 5 ||cardid == 6 ||cardid == 7 ||cardid == 8 ||cardid == 9 ||cardid == 10){
            card = Integer.toString(cardid);
        }
        else if (cardid == 11){
            card = "J";
        }
        else if (cardid == 12){
            card = "Q";
        }
        else if (cardid == 13){
            card = "K";
        }
        else if (cardid == 1){
            card = "A";
        }
        return card;
    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public void play(){

    }
}