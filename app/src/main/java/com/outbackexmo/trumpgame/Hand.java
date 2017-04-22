package com.outbackexmo.trumpgame;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by MyServer_U on 2017/02/25.
 */

public abstract class Hand {

    protected ArrayList<Card> cards;


    public Hand(){
        cards = new ArrayList<>();
    }

    public void addCard(Card card){
            cards.add(card);
        }

    public void discardCard(int index){
        cards.remove(index);
    }

    public Card getCard(int index){
        return cards.get(index);
    }

    public int getCardCount(){
        return cards.size();
    }

    public Card handoverCard(int index){
        return cards.remove(index);
    }
}
