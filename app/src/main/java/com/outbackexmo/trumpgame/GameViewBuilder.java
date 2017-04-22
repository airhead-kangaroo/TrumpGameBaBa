package com.outbackexmo.trumpgame;

import android.app.Activity;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by MyServer_U on 2017/02/25.
 */

public class GameViewBuilder {

    private Activity activity;
    private TrumpCardBuilder builder;
    private TextView mainTextView;
    private ImageView centerImageView;
    private GridLayout gridLayoutTop;
    private GridLayout gridLayoutBottom;

    public GameViewBuilder(Activity activity){
        this.activity = activity;
        builder = new TrumpCardBuilder();
        mainTextView = (TextView)activity.findViewById(R.id.mainText);
        gridLayoutTop = (GridLayout)activity.findViewById(R.id.topGrid);
        gridLayoutBottom = (GridLayout)activity.findViewById(R.id.bottomGrid);

    }

    public void construct(Player mainCharacter, Player enemyCharacter){
        setMainCharacterImageView(mainCharacter);
        setEnemyCharacterImageView(enemyCharacter);
        }

    public void setImageView(Player player){
        switch (player.getSide()){
            case Player.MainCharacter:
                setMainCharacterImageView(player);
                break;
            case Player.EnemyCharacter:
                setEnemyCharacterImageView(player);
                break;
        }
    }



    private void setMainCharacterImageView(Player player){
        gridLayoutBottom.removeAllViews();
        for(int i = 0; i < player.getHand().getCardCount(); i++){
            Card card = player.getHand().getCard(i);
            ImageView iv = prepareImageView();
            iv.setImageResource(builder.build(card));
            gridLayoutBottom.addView(iv);
        }
    }

    private void setEnemyCharacterImageView(Player player){
        gridLayoutTop.removeAllViews();
        for(int i = 0; i < player.getHand().getCardCount(); i++){
//            Card card = player.getHand().getCard(i);
            ImageView iv = prepareImageView();
//            iv.setImageResource(builder.build(card));
            iv.setImageResource(builder.backCardBuild());
            gridLayoutTop.addView(iv);
        }
    }

    public void showPickcard(Card card){
        centerImageView = (ImageView)activity.findViewById(R.id.centerCard);
        centerImageView.setImageResource(builder.build(card));
    }

    public void showMainText(String msg){
        mainTextView.setText(msg);
    }

    public void removeImageView(){
        centerImageView.setImageResource(0);
    }

    private ImageView prepareImageView(){
        ImageView iv = new ImageView(activity);
        iv.setLayoutParams(new ViewGroup.LayoutParams((int)activity.getResources().getDimension(R.dimen.card_width),(int)activity.getResources().getDimension(R.dimen.card_height)));
        return iv;
    }

    public void showTurnMessage(Player player){
        switch (player.getSide()){
            case Player.MainCharacter:
                mainTextView.setText(player.getName() + activity.getString(R.string.myTurnPickCard));
                break;
            case Player.EnemyCharacter:
                mainTextView.setText(activity.getString(R.string.enemyTurnPickCard));
                break;
        }
    }
}
