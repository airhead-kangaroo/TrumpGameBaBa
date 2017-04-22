package com.outbackexmo.trumpgame;




import java.util.Random;

/**
 * Created by MyServer_U on 2017/02/25.
 */

public class Dealer {

    private DealerHand hand;

    public Dealer(){
        initGameCards();
    }

    private void initGameCards(){
        hand = new DealerHand();
        for(int i=1;i<5;i++){
            for(int j=1;j<14;j++){
                Card card = new Card(j,i);
                hand.addCard(card);
            }
        }
        Card Jouker = new Card(Card.JOKER,Card.JOKER);
        hand.addCard(Jouker);
        hand.shuffleCards();
    }

    public void distributeCard(Player mainCharacter, Player enemyCharacter){
        int alter = 1;
        while(hand.getCardCount() > 0){
            Card distributedCard = hand.handoverCard(0);
            if(alter % 2 == 1){
                if(mainCharacter.getHand().checkDuplication(distributedCard)){
                    mainCharacter.getHand().addCard(distributedCard);
                }
                alter++;
            }else if(alter % 2 == 0){
                if(enemyCharacter.getHand().checkDuplication(distributedCard)){
                    enemyCharacter.getHand().addCard(distributedCard);
                }
                alter++;
            }
        }
    }

    public int judgePlayFirst(GameViewBuilder builder,String mainCharacter, String enemyCharactor){
        int judgment = new Random().nextInt(2);
        switch (judgment){
            case 0:
                builder.showMainText(mainCharacter + "が先攻");
                return 1;
            default:
                builder.showMainText(enemyCharactor + "が先攻");
                return 2;
        }
    }
}
