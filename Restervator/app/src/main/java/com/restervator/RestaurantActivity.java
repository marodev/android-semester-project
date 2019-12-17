package com.restervator;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.restervator.utils.ImageUtil;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.ItemizedOverlay;
import org.osmdroid.views.overlay.OverlayItem;

import java.util.ArrayList;

import static com.restervator.adapter.RestaurantAdapter.EXTRA_REPLY;


public class RestaurantActivity extends AppCompatActivity {

    public static final String RESTAURANT_REPLY =
            "com.restervator.extra.REPLY";

    private MapView map = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // load/initialize the osmdroid configuration for the map from cache
        // setting this before the layout is inflated is a good idea
        // it 'should' ensure that the map has a writable location for the map cache, even without permissions
        // note, the load method also sets the HTTP User Agent to your application's package name, abusing osm's tile servers will get you banned based on this string
        Context ctx = getApplicationContext();
        Configuration.getInstance().setUserAgentValue(BuildConfig.APPLICATION_ID);
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));

        // inflate view
        setContentView(R.layout.activity_restaurant);

        // Fetching restaurant image, name, and Fab controls
        ArrayList<String> restaurantInformation = getIntent().getStringArrayListExtra(EXTRA_REPLY);

        String restaurantName = restaurantInformation.get(0);

        // dynamically change title
        setTitle(restaurantName);

        // put the information from the intent into the views
        TextView restaurantNameView = findViewById(R.id.restaurantName);
        restaurantNameView.setText(restaurantName);

        TextView restaurantAddressView = findViewById(R.id.restaurantAddress);
        restaurantAddressView.setText(restaurantInformation.get(1));

        TextView restaurantNumberView = findViewById(R.id.restaurantNumber);
        restaurantNumberView.setText(restaurantInformation.get(2));

        ImageView restaurantImageView = findViewById(R.id.imageView);

        // http request to fetch image and load into the view
        ImageUtil.loadImage(restaurantInformation.get(3), restaurantImageView);

        // create and inflate map. Zoom to restaurant and add icon
        initializeMap(Double.valueOf(restaurantInformation.get(4)), Double.valueOf(restaurantInformation.get(5)));

        // Fab
        ExtendedFloatingActionButton fab = findViewById(R.id.fab);

        // set onclick listener for booking activity and add information about the restaurant
        final Intent intent = new Intent(this, BookingActivity.class);
        intent.putStringArrayListExtra(RESTAURANT_REPLY, restaurantInformation);
        fab.setOnClickListener(view -> startActivity(intent));

    }

    private void initializeMap(double latitude, double longitude) {
        map = findViewById(R.id.map);

        map.setTileSource(TileSourceFactory.MAPNIK);
        // set source to load from openstreetmap (remote), note: doesn't work on an emulator
//        map.setTileSource(
//                new XYTileSource("HttpMapnik",
//                        0, 19, 256, ".png", new String[]{
//                        "http://a.tile.openstreetmap.org/",
//                        "http://b.tile.openstreetmap.org/",
//                        "http://c.tile.openstreetmap.org/"},
//                        "© OpenStreetMap contributors")
//        );

        map.setMultiTouchControls(true);
        map.setTilesScaledToDpi(true);

        IMapController mapController = map.getController();

        //sets the starting point on the map
        GeoPoint startPoint = new GeoPoint(latitude, longitude);
        mapController.setCenter(startPoint);
        mapController.setZoom(16.0);

        addRestaurantIconToMap(latitude, longitude);
    }

    private void addRestaurantIconToMap(double latitude, double longitude) {

        // list to hold the references to the icons
        final ArrayList<OverlayItem> items = new ArrayList<>();

        // add icon for the restaurant
        items.add(new OverlayItem("Restaurant", "The Restaurant", new GeoPoint(latitude, longitude)));

        // add icon to the map
        ItemizedOverlay<OverlayItem> mMyLocationOverlay = new ItemizedIconOverlay<>(items, new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>() {
            @Override
            public boolean onItemSingleTapUp(final int index, final OverlayItem item) {
                return true; // We 'handled' this event.
            }

            @Override
            public boolean onItemLongPress(final int index, final OverlayItem item) {
                return true; // We 'handled' this event.
            }
        }, getApplicationContext());
        this.map.getOverlays().add(mMyLocationOverlay);
    }


    @Override
    public void onResume() {
        super.onResume();
        //this will refresh the osmdroid configuration on resuming.
        map.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        //this will refresh the osmdroid configuration on resuming.
        map.onPause();
    }


    public void makePhoneCall(View view) {

        ActivityCompat.requestPermissions(RestaurantActivity.this,
                new String[]{Manifest.permission.CALL_PHONE},
                1);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted
                    TextView phoneNumberView = findViewById(R.id.restaurantNumber);
                    String number = phoneNumberView.getText().toString();
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:"+number));
                    startActivity(intent);
                } else {
                    // permission denied,
                    // functionality that depends on this permission.
                    Toast.makeText(RestaurantActivity.this, "Permission denied to call restaurant", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }
}
