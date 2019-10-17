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

        if (savedInstanceState != null) {
            boolean isVisible =
                    savedInstanceState.getBoolean("reply_visible");

            if (isVisible) {
                itemOne.setText(savedInstanceState
                        .getString("reply_text"));
                itemOne.setVisibility(View.VISIBLE);
            }
            boolean isVisible2 =
                    savedInstanceState.getBoolean("reply_visible2");

            if (isVisible2) {
                itemTwo.setText(savedInstanceState
                        .getString("reply_text2"));
                itemTwo.setVisibility(View.VISIBLE);
            }
            boolean isVisible3 =
                    savedInstanceState.getBoolean("reply_visible3");

            if (isVisible3) {
                itemThree.setText(savedInstanceState
                        .getString("reply_text3"));
                itemThree.setVisibility(View.VISIBLE);
            }
            boolean isVisible4 =
                    savedInstanceState.getBoolean("reply_visible4");

            if (isVisible4) {
                itemFour.setText(savedInstanceState
                        .getString("reply_text4"));
                itemFour.setVisibility(View.VISIBLE);
            }
            boolean isVisible5 =
                    savedInstanceState.getBoolean("reply_visible5");

            if (isVisible5) {
                itemFive.setText(savedInstanceState
                        .getString("reply_text5"));
                itemFive.setVisibility(View.VISIBLE);
            }
            boolean isVisible6 =
                    savedInstanceState.getBoolean("reply_visible6");

            if (isVisible6) {
                itemSix.setText(savedInstanceState
                        .getString("reply_text6"));
                itemSix.setVisibility(View.VISIBLE);
            }
            boolean isVisible7 =
                    savedInstanceState.getBoolean("reply_visible7");

            if (isVisible7) {
                itemSeven.setText(savedInstanceState
                        .getString("reply_text7"));
                itemSeven.setVisibility(View.VISIBLE);
            }
            boolean isVisible8 =
                    savedInstanceState.getBoolean("reply_visible8");

            if (isVisible8) {
                itemEight.setText(savedInstanceState
                        .getString("reply_text8"));
                itemEight.setVisibility(View.VISIBLE);
            }
            boolean isVisible9 =
                    savedInstanceState.getBoolean("reply_visible9");

            if (isVisible9) {
                itemNine.setText(savedInstanceState
                        .getString("reply_text9"));
                itemNine.setVisibility(View.VISIBLE);
            }
            boolean isVisible10 =
                    savedInstanceState.getBoolean("reply_visible10");

            if (isVisible10) {
                itemTen.setText(savedInstanceState
                        .getString("reply_text10"));
                itemTen.setVisibility(View.VISIBLE);
            }
        }

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

        if (itemOne.getVisibility() == View.VISIBLE) {
            outState.putBoolean("reply_visible", true);
            outState.putString("reply_text",
                    itemOne.getText().toString());

        }
        if (itemTwo.getVisibility() == View.VISIBLE) {
            outState.putBoolean("reply_visible2", true);
            outState.putString("reply_text2",
                    itemTwo.getText().toString());

        }
        if (itemThree.getVisibility() == View.VISIBLE) {
            outState.putBoolean("reply_visible3", true);
            outState.putString("reply_text3",
                    itemThree.getText().toString());

        }
        if (itemFour.getVisibility() == View.VISIBLE) {
            outState.putBoolean("reply_visible4", true);
            outState.putString("reply_text4",
                    itemFour.getText().toString());

        }
        if (itemFive.getVisibility() == View.VISIBLE) {
            outState.putBoolean("reply_visible5", true);
            outState.putString("reply_text5",
                    itemFive.getText().toString());

        }
        if (itemSix.getVisibility() == View.VISIBLE) {
            outState.putBoolean("reply_visible6", true);
            outState.putString("reply_text6",
                    itemSix.getText().toString());

        }
        if (itemSeven.getVisibility() == View.VISIBLE) {
            outState.putBoolean("reply_visible7", true);
            outState.putString("reply_text7",
                    itemSeven.getText().toString());

        }
        if (itemEight.getVisibility() == View.VISIBLE) {
            outState.putBoolean("reply_visible8", true);
            outState.putString("reply_text8",
                    itemEight.getText().toString());

        }
        if (itemNine.getVisibility() == View.VISIBLE) {
            outState.putBoolean("reply_visible9", true);
            outState.putString("reply_text9",
                    itemNine.getText().toString());

        }
        if (itemTen.getVisibility() == View.VISIBLE) {
            outState.putBoolean("reply_visible10", true);
            outState.putString("reply_text10",
                    itemTen.getText().toString());

        }
    }
}
