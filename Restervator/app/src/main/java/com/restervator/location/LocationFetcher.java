package com.restervator.location;

import android.app.Activity;
import android.content.Context;
import android.location.Location;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.restervator.utils.PermissionUtil;

public class LocationFetcher {

    private static Location lastKnownLocation = null;
    private final FusedLocationProviderClient mFusedLocationClient;
    private final Context context;

    public LocationFetcher(Context context) {
        this.context = context;
        this.mFusedLocationClient = LocationServices.getFusedLocationProviderClient(context);

    }

    public static String getDistanceToLocation(Location location) {
        int oneKm = 1000;
        double distance = lastKnownLocation.distanceTo(location);
        int roundedDistance = (int) Math.round(distance);

        if (roundedDistance <= oneKm) {
            return "" + "0." + Math.round(roundedDistance / 100.0) + " km";
        }

        return "" + roundedDistance / oneKm + " km";

    }

    public void getLastKnownLocation(final LocationResponseListener listener) {

        // guard - check if permission is granted. otherwise abort.
        if (!PermissionUtil.isLocationPermissionGranted(this.context)) {
            return;
        }

        // register callback
        this.mFusedLocationClient.getLastLocation()
                .addOnSuccessListener((Activity) this.context, location -> {
                    // Got last known location. In some rare situations this can be null.
                    // e.g. on an Android emulator
                    if (location == null) {
                        // default USI location
                        location = new Location("Custom Provider");
                        location.setLatitude(46.0107);
                        location.setLongitude(8.9581);
                    }

                    if (listener != null) {
                        lastKnownLocation = location;
                        // route the location response to registered listener.
                        listener.onLocationResponse(location);
                    }
                });
    }
}
