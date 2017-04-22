package com.outbackexmo.trumpgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.EditText;

public class IndexActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

    }

    public void btn(View view){
        EditText et = (EditText)findViewById(R.id.nameInput);
        String yourName = et.getText().toString().trim();
        if(yourName.length() == 0){
            et.setError("名前を入力してください");
        }else{
            Intent intent = new Intent(this,GameActivity.class);
            intent.putExtra("yourName", yourName);
            startActivity(intent);
        }

    }
}
