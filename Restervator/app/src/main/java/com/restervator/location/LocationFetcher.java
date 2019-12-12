package com.restervator.location;

import android.app.Activity;
import android.content.Context;

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

    public void getLastKnownLocation(final LocationResponseListener listener) {

        // guard - check if permission is granted. otherwise abort
        if (!PermissionUtil.isLocationPermissionGranted(this.context)) {
            return;
        }

        this.mFusedLocationClient.getLastLocation()
                .addOnSuccessListener((Activity) this.context, location -> {
                    // Got last known location. In some rare situations this can be null.
                    if (location != null && listener != null) {
                        // route the location response to a listener
                        listener.onLocationResponse(location);
                    }
                });
    }
}
