package com.restervator.utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class PermissionUtil {


    public static void askUserForLocationPermission(Context context, int permissionResult) {
        if (!isLocationPermissionGranted(context)) {
            ActivityCompat.requestPermissions((AppCompatActivity) context,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    permissionResult);
        }
    }

    public static boolean isLocationPermissionGranted(int[] grantResults) {
        return grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED;
    }

    public static boolean isLocationPermissionGranted(Context context) {
        return ActivityCompat.
                checkSelfPermission(context,
                        Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED;
    }
}
