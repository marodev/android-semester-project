package com.example.whowroteit;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.content.Context;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {

    private EditText mBookInput;
    private TextView mTitleText;
    private TextView mAuthorText;
    private final static int LOADER_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBookInput = findViewById(R.id.bookInput);
        mTitleText = findViewById(R.id.titleText);
        mAuthorText = findViewById(R.id.authorText);

        setupLoader();
    }

    private void setupLoader() {
        if (LoaderManager.getInstance(this).getLoader(LOADER_ID) != null) {
            LoaderManager.getInstance(this).initLoader(LOADER_ID, null, this);
        }
    }

    public void searchBooks(View view) {
        String queryString = mBookInput.getText().toString();

        // Hide the keyboard when the button is pushed.
        hideKeyboard(view);

        if (queryString.isEmpty()) {
            displayInformation("", getString(R.string.no_search_term));
            return;
        }

        if (!NetworkUtils.hasNetworkConnection(this)) {
            displayInformation("", getString(R.string.no_network));
            return;
        }

        Bundle queryBundle = new Bundle();
        queryBundle.putString("queryString", queryString);
        LoaderManager.getInstance(this).restartLoader(LOADER_ID, queryBundle, this);
        displayInformation("", getString(R.string.loading));
    }


    private void displayInformation(String authorText, String titleText) {
        mAuthorText.setText(authorText);
        mTitleText.setText(titleText);
    }

    private void hideKeyboard(View view) {
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);

        if (inputManager != null) {
            inputManager.hideSoftInputFromWindow(view.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        String queryString = "";

        if (args != null) {
            queryString = args.getString("queryString");
        }

        return new BookLoader(this, queryString);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {

        Pair<String, String> authorTitle = parseJsonTitleAuthor(data);
        String authors = authorTitle.first;
        String title = authorTitle.second;

        if (title == null || authors == null) {
            displayInformation("", getString(R.string.no_results));
            return;
        }

        displayInformation(authors, title);
    }

    public Pair<String, String> parseJsonTitleAuthor(String data) {

        String title = null;
        String authors = null;
        try {
            JSONObject jsonObject = new JSONObject(data);
            JSONArray itemsArray = jsonObject.getJSONArray("items");

            int i = 0;
            while (i < itemsArray.length() && (authors == null && title == null)) {
                JSONObject book = itemsArray.getJSONObject(i);
                JSONObject volumeInfo = book.getJSONObject("volumeInfo");

                try {
                    title = volumeInfo.getString("title");
                    authors = volumeInfo.getString("authors");
                } catch (Exception e) {
                    e.printStackTrace();
                }

                i++;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new Pair<>(title, authors);
    }


    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {
        // do nothing
    }
}
