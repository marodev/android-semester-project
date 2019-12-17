package com.restervator.utils;

import android.text.TextUtils;
import android.widget.ImageView;

import com.restervator.R;
import com.squareup.picasso.Picasso;

public class ImageUtil {

    public static void loadImage(String uri, ImageView view) {

        boolean hasThumbnail = !TextUtils.isEmpty(uri);

        // only load image if url is present, otherwise load default placeholder
        if (hasThumbnail) {
            Picasso.get().load(uri).into(view);
        } else {
            Picasso.get().load(R.drawable.ic_no_image).into(view);
        }
    }
}
