package com.outbackexmo.trumpgame;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by MyServer_U on 2017/02/25.
 */

public class DealerHand extends Hand{

    public DealerHand(){
        super();
    }

    public void shuffleCards(){
        Collections.shuffle(cards);
    }

}
