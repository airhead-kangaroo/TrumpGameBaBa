package com.outbackexmo.trumpgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {

    private Player mainCharacter;
    private Player enemyCharacter;
    private Dealer dealer;
    private int playTurn;
    private GameViewBuilder builder;
    private int gameFlag = 0;
    private Card pickedCard = null;
    private String playerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Intent intent = getIntent();
        playerName = intent.getStringExtra("yourName");
        initGame();
        gameFlag = 1;
        LinearLayout mainView = (LinearLayout)findViewById(R.id.activity_game);
        mainView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (gameFlag){
                    case 1:
                        pickCard();
                        gameFlag = 2;
                        break;
                    case 2:
                        refreshHandDisplay();
                        if(mainCharacter.getHand().getCardCount() <= 0 || enemyCharacter.getHand().getCardCount() <= 0){
                            setEndding();
                        }else{
                            gameFlag =1;
                        }
                        break;
                }
            }
        });
    }

    private void initGame(){
        mainCharacter = new Player(playerName,Player.MainCharacter);
        enemyCharacter = new Player("enemy", Player.EnemyCharacter);
        dealer = new Dealer();
        builder = new GameViewBuilder(this);
        dealer.distributeCard(mainCharacter,enemyCharacter);
        builder.construct(mainCharacter,enemyCharacter);
        judgeFirstPlay();
    }

    private void judgeFirstPlay(){
        playTurn = dealer.judgePlayFirst(builder,mainCharacter.getName(),enemyCharacter.getName());
    }

    private void pickCard(){
       if(playTurn % 2 == 1){
           pickedCard = enemyCharacter.handoverCard();
           builder.setImageView(enemyCharacter);
           builder.showMainText(getString(R.string.ClickToPlay));
       }else{
           pickedCard = mainCharacter.handoverCard();
           builder.setImageView(mainCharacter);
           builder.showMainText(getString(R.string.ClickToPlay));
       }
        builder.showPickcard(pickedCard);
    }

    private void refreshHandDisplay(){
        builder.removeImageView();
        switch (playTurn % 2){
            case 1:
                if(mainCharacter.getHand().checkDuplication(pickedCard)){
                    mainCharacter.getHand().addCard(pickedCard);
                }
                builder.setImageView(mainCharacter);
                builder.showTurnMessage(enemyCharacter);
                break;
            case 0:
                if(enemyCharacter.getHand().checkDuplication(pickedCard)){
                    enemyCharacter.getHand().addCard(pickedCard);
                }
                builder.setImageView(enemyCharacter);
                builder.showTurnMessage(mainCharacter);
                break;
        }
        playTurn++;
    }

    public void setEndding(){
        if(mainCharacter.getHand().getCardCount() == 0){
            builder.showMainText(mainCharacter.getName() + "の勝ちです！");
        }else{
            builder.showMainText(enemyCharacter.getName() + "の勝ちです！");
        }
        gameFlag = 0;
    }
}
