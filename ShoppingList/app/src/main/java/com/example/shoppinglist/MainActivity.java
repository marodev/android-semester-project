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

        editItems = new ArrayList<>(10);
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

        if (savedInstanceState == null) {
            return;
        }

        for (int i = 0; i < editItems.size() - 1; i++) {
            boolean isVisible =
                    savedInstanceState.getBoolean("reply_visible" + i);

            if (!isVisible) {
                break;
            }

            TextView view = editItems.get(i);
            view.setText(savedInstanceState
                    .getString("reply_text" + i));
            view.setVisibility(View.VISIBLE);
        }
    }

    public void addItem(View view) {

        Intent intent = new Intent(this, SecondActivity.class);
        startActivityForResult(intent, TEXT_REQUEST);

    }

    public void onActivityResult(int requestCode,
                                 int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode != TEXT_REQUEST) {
            return;
        }

        if (resultCode != RESULT_OK) {
            return;
        }

        String reply =
                data.getStringExtra(SecondActivity.EXTRA_REPLY);

        for (TextView editItem : editItems) {
            if (editItem.getVisibility() != View.VISIBLE) {
                editItem.setText(reply);
                editItem.setVisibility(View.VISIBLE);
                break;
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        for (int i = 0; i < editItems.size() - 1; i++) {
            TextView view = editItems.get(i);

            if (view.getVisibility() != View.VISIBLE) {
                break;
            }

            outState.putBoolean("reply_visible" + i, true);
            outState.putString("reply_text" + i,
                    view.getText().toString());
        }
    }
}
