package com.outbackexmo.trumpgame;

/**
 * Created by MyServer_U on 2017/02/25.
 */

public class Player {

    private UserHand hand;
    private String name;
    private int side;
    public static final int MainCharacter = 0;
    public static final int EnemyCharacter = 1;

    public Player(String name, int side){
        this.name = name;
        this.side = side;
        hand = new UserHand();
    }

    public void addCard(Card card){
        hand.addCard(card);
    }

    public int getSide(){
        return side;
    }

    public UserHand getHand(){
        return hand;
    }

    public String getName(){
        return this.name;
    }

    public Card handoverCard(){
        return hand.handoverCard(0);
    }
}
