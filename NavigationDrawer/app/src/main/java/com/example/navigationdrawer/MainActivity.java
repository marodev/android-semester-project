package com.example.navigationdrawer;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "No action yet!!!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        if (drawer != null) {
            drawer.addDrawerListener(toggle);
        }
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(this);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    public void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer != null) {
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                super.onBackPressed();
            }
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        switch (item.getItemId()) {
            case R.id.nav_home:
                drawer.closeDrawer(GravityCompat.START);
                displayToast(getString(R.string.home_message));
                return true;
            case R.id.your_orders:
                drawer.closeDrawer(GravityCompat.START);
                displayToast(getString(R.string.your_orders_message));
                return true;
            case R.id.your_account:
                drawer.closeDrawer(GravityCompat.START);
                displayToast(getString(R.string.your_account_message));
                return true;
            case R.id.your_wish_list:
                drawer.closeDrawer(GravityCompat.START);
                displayToast(getString(R.string.your_wish_list_message));
                return true;
            case R.id.buy_again:
                drawer.closeDrawer(GravityCompat.START);
                displayToast(getString(R.string.buy_again_message));
                return true;
            case R.id.todays_deal:
                drawer.closeDrawer(GravityCompat.START);
                displayToast(getString(R.string.today_s_deals_message));
                return true;
            case R.id.gift_cards:
                drawer.closeDrawer(GravityCompat.START);
                displayToast(getString(R.string.gift_cards_message));
                return true;
            case R.id.shop_family:
                drawer.closeDrawer(GravityCompat.START);
                displayToast(getString(R.string.shop_family_message));
                return true;
            case R.id.customer_service:
                drawer.closeDrawer(GravityCompat.START);
                displayToast(getString(R.string.customer_service_message));
                return true;
            case R.id.notifications:
                drawer.closeDrawer(GravityCompat.START);
                displayToast(getString(R.string.notifications_message));
                return true;
            case R.id.legal:
                drawer.closeDrawer(GravityCompat.START);
                displayToast(getString(R.string.legal_message));
                return true;
            default:
                return false;
        }
    }
}
