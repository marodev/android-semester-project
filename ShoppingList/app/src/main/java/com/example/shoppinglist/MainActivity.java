package com.example.shoppinglist;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final int TEXT_REQUEST = 1;
    public TextView itemOne;
    public TextView itemTwo;
    public TextView itemThree;
    public TextView itemFour;
    public TextView itemFive;
    public TextView itemSix;
    public TextView itemSeven;
    public TextView itemEight;
    public TextView itemNine;
    public TextView itemTen;
    ArrayList<TextView> editItems;

    public static final String EXTRA_MESSAGE =
            "com.example.shoppinglist.extra.MESSAGE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editItems = new ArrayList<>();
        itemOne = findViewById(R.id.editTextItemOne);
        itemTwo = findViewById(R.id.editTextItemTwo);
        itemThree = findViewById(R.id.editTextItemThree);
        itemFour = findViewById(R.id.editTextItemFour);
        itemFive = findViewById(R.id.editTextItemFive);
        itemSix = findViewById(R.id.editTextItemSix);
        itemSeven = findViewById(R.id.editTextItemSeven);
        itemEight = findViewById(R.id.editTextItemEight);
        itemNine = findViewById(R.id.editTextItemNine);
        itemTen = findViewById(R.id.editTextItemTen);

        editItems.add(itemOne);
        editItems.add(itemTwo);
        editItems.add(itemThree);
        editItems.add(itemFour);
        editItems.add(itemFive);
        editItems.add(itemSix);
        editItems.add(itemSeven);
        editItems.add(itemEight);
        editItems.add(itemNine);
        editItems.add(itemTen);

    }

    public void addItem(View view) {

        Intent intent = new Intent(this, SecondActivity.class);
        startActivityForResult(intent, TEXT_REQUEST);

    }
    public void onActivityResult(int requestCode,
                                 int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TEXT_REQUEST) {
            if (resultCode == RESULT_OK) {
                String reply =
                        data.getStringExtra(SecondActivity.EXTRA_REPLY);
              //  itemOne.setText(reply);
                for (TextView editItem: editItems){
                    if (editItem.getVisibility() == View.VISIBLE){
                        continue;
                    }else{
                        editItem.setText(reply);
                        editItem.setVisibility(View.VISIBLE);
                        break;
                    }
                }
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }
}
