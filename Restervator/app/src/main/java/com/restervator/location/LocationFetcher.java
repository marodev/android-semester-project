package com.restervator.location;

import android.app.Activity;
import android.content.Context;
import android.location.Location;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.restervator.utils.PermissionUtil;

public class LocationFetcher {

    private final FusedLocationProviderClient mFusedLocationClient;
    private final Context context;

    public LocationFetcher(Context context) {
        this.context = context;
        this.mFusedLocationClient = LocationServices.getFusedLocationProviderClient(context);

    }

    private static Location lastKnownLocation = null;

    public static String getDistanceToLocation(Location location) {
        double distance = lastKnownLocation.distanceTo(location);
        int roundedDistance = (int) Math.round(distance);

        return "" + roundedDistance / 1000 + " km";

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
                    if (location != null && listener != null) {
                        lastKnownLocation = location;
                        // route the location response to registered listener.
                        listener.onLocationResponse(location);
                    }
                });
    }
}
