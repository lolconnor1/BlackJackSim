package com.example.blackjacksim;



public class Card{
    private String cardNum;
    private int cardValue;

    public Card(){
        cardNum = getRandomCard();
        cardValue = getCardValue(cardNum);
    }
    public Card(int num, String val){
        cardNum = val;
        cardValue = getCardValue(cardNum);
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

    public int getCardValue(String s){
        int num = 0;
        if(s.equals("2") || s.equals("3") || s.equals("4") || s.equals("5") || s.equals("6") || s.equals("7") || s.equals("8") || s.equals("9") || s.equals("10")){
            return Integer.parseInt(s);
        }
        else if (s.equals("J") || s.equals("Q") ||s.equals("K")){

            return 10;
        }
        else if (s.equals("A")){

            return 11;
        }


        return num;
    }

    public String getCardNum(){
        return cardNum;
    }

    public int getCardValue(){
        return cardValue;
    }

}


