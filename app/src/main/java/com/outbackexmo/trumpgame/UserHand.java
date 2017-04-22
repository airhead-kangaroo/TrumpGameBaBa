package com.outbackexmo.trumpgame;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by MyServer_U on 2017/02/25.
 */

public class UserHand extends Hand {


    public UserHand(){
        super();
    }

    public void addCard(Card card){
        if(checkDuplication(card)){
            cards.add(card);
        }
    }

    public boolean checkDuplication(Card card){
        for(int i=0;i<cards.size();i++){
            if(cards.get(i).getNumver() == card.getNumver() ){
                discardCard(i);
                return false;
            }
        }
        return true;
    }
}
