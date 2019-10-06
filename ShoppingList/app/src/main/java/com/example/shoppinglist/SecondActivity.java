package com.example.shoppinglist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY =
            "com.example.shoppinglist.extra.REPLY";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

    }

    public void addItemOne(View view) {
        Button button;
        switch(view.getId()){
            case R.id.buttonItemOne:
                button = findViewById(R.id.buttonItemOne);
                break;
            case R.id.buttonItemTwo:
                button = findViewById(R.id.buttonItemTwo);
                break;
            case R.id.buttonItemThree:
                button = findViewById(R.id.buttonItemThree);
                break;
            case R.id.buttonItemFour:
                button = findViewById(R.id.buttonItemFour);
                break;
            case R.id.buttonItemFive:
                button = findViewById(R.id.buttonItemFive);
                break;
            case R.id.buttonItemSix:
                button = findViewById(R.id.buttonItemSix);
                break;
            case R.id.buttonItemSeven:
                button = findViewById(R.id.buttonItemSeven);
                break;
            case R.id.buttonItemEight:
                button = findViewById(R.id.buttonItemEight);
                break;
            case R.id.buttonItemNine:
                button = findViewById(R.id.buttonItemNine);
                break;
            case R.id.buttonItemTen:
                button = findViewById(R.id.buttonItemTen);
                break;
             default:
                 button = findViewById(R.id.button_addItem);
        }

        String text = button.getText().toString();

        Intent replyIntent = new Intent(this, MainActivity.class);
        replyIntent.putExtra(EXTRA_REPLY, text);
        setResult(RESULT_OK, replyIntent);
        finish();
    }
}
